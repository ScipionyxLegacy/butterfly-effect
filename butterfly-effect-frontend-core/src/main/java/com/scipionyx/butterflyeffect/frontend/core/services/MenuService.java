package com.scipionyx.butterflyeffect.frontend.core.services;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.vaadin.teemu.VaadinIcons;

import com.scipionyx.butterflyeffect.frontend.model.Menu;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;

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
public class MenuService implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuService.class);

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
		List<Class<? extends View>> views = new ArrayList<>();
		new FastClasspathScanner("com.scipionyx.butterflyeffect.frontend")
				.matchClassesImplementing(View.class, views::add).scan();
		readMenus(views);
		sortMenus();
		LOGGER.info("Number of menus read: [{}]", menus.size());
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
	private void readMenus(List<Class<? extends View>> views) {
		menus = new ArrayList<>();
		for (Class<? extends View> clazz : views) {
			if (!Modifier.isAbstract(clazz.getModifiers())) {

				if (clazz.isAnnotationPresent(MenuConfiguration.class) && (clazz.isAnnotationPresent(SpringView.class)
						|| clazz.isAnnotationPresent(SpringComponent.class))) {

					SpringView springView = clazz.getAnnotation(SpringView.class);
					SpringComponent springComponent = clazz.getAnnotation(SpringComponent.class);
					MenuConfiguration annotation = clazz.getAnnotation(MenuConfiguration.class);

					String beanName = springView != null ? springView.name() : springComponent.value();

					Menu menu = new Menu();
					menu.setId(beanName);
					menu.setLabel(annotation.label());
					// menu.setTooltip(annotation.toolTip());
					menu.setView(beanName);
					menu.setVisible(true);
					menu.setSeparator(false);
					menu.setOrder(annotation.order());
					menu.setPosition(annotation.position());
					menu.setParent(annotation.parent().equals("") ? null : annotation.parent());
					menu.setIcon(annotation.font().equals(FontAwesome.YELP) ? null : annotation.font());
					if (menu.getIcon() == null)
						menu.setIcon(annotation.icon().equals(VaadinIcons.VAADIN_H) ? null : annotation.icon());
					menus.add(menu);

				} else {
					LOGGER.error(
							"Menu configuration could not be read from the class's[{}] annotations: @SpringComponent present[{}], @MenuConfiguration present[{}]",
							clazz.getName(), clazz.isAnnotationPresent(SpringComponent.class),
							clazz.isAnnotationPresent(MenuConfiguration.class));
				}
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
