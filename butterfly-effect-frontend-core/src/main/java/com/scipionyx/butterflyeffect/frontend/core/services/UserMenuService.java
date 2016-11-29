package com.scipionyx.butterflyeffect.frontend.core.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractCommonView;
import com.scipionyx.butterflyeffect.frontend.model.Menu;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.vaadin.spring.annotation.SpringComponent;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

/**
 * 
 * This service is responsible for loading the application menus
 * 
 * 
 * @author Renato Mendes
 *
 */
@SpringComponent("userMenuService")
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class UserMenuService implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuService.class);

	private List<Menu> menus;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@PostConstruct
	private void init() {
		List<Class<? extends AbstractCommonView>> views = new ArrayList<>();
		new FastClasspathScanner("com.scipionyx.butterflyeffect.frontend")
				.matchSubclassesOf(AbstractCommonView.class, views::add).scan();
		readMenus(views);
		sortMenus();
		LOGGER.info("# of Views read: [{}], # of menus read: [{}]", views.size(), menus.size());
	}

	/**
	 * 
	 */
	private void sortMenus() {
		menus.sort(new Comparator<Menu>() {

			@Override
			public int compare(Menu o1, Menu o2) {
				return Integer.compare(o1.getOrder(), o2.getOrder());
			}
		});

	}

	/**
	 * Read the menu configuration from the Views.
	 * 
	 * 
	 * @param views
	 */
	private void readMenus(List<Class<? extends AbstractCommonView>> views) {
		menus = new ArrayList<>();
		for (Class<? extends AbstractCommonView> clazz : views) {
			if (clazz.isAnnotationPresent(MenuConfiguration.class)
					&& clazz.isAnnotationPresent(SpringComponent.class)) {
				SpringComponent component = clazz.getAnnotation(SpringComponent.class);
				MenuConfiguration annotation = clazz.getAnnotation(MenuConfiguration.class);
				Menu menu = new Menu();
				menu.setId(component.value());
				menu.setLabel(annotation.label());
				// menu.setTooltip(annotation.toolTip());
				menu.setView(component.value());
				menu.setVisible(true);
				menu.setSeparator(false);
				menu.setOrder(annotation.order());
				menu.setPosition(annotation.position());
				menu.setParent(annotation.parent().equals("") ? null : annotation.parent());
				menus.add(menu);
			} else {
				LOGGER.error(
						"Menu Configuraiton could not be read from the Class[{}] Annotations: @SpringComponent Present[{}], @MenuConfiguration Present[{}]",
						clazz.getName(), clazz.isAnnotationPresent(SpringComponent.class),
						clazz.isAnnotationPresent(MenuConfiguration.class));
			}
		}
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}
