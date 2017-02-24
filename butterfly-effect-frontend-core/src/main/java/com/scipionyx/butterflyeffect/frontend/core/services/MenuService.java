package com.scipionyx.butterflyeffect.frontend.core.services;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.scipionyx.butterflyeffect.frontend.model.Menu;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;

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
@UIScope
public class MenuService implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuService.class);

	private List<Menu> menus;

	private List<String> roles;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@PostConstruct
	private void init() {

		// load users roles
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		roles = new ArrayList<>(authorities.size());
		for (GrantedAuthority grantedAuthority : authorities) {
			roles.add(grantedAuthority.getAuthority());
		}

		List<Class<? extends View>> views = new ArrayList<>();

		new FastClasspathScanner("com.scipionyx.butterflyeffect.frontend")
				.matchClassesImplementing(View.class, views::add).scan();
		readMenus(views);
		sortMenus();

		LOGGER.info("Number of menus read: [{}]", menus.size());
		if (LOGGER.isDebugEnabled()) {
			for (Menu menu : menus) {
				LOGGER.debug("menu {}", menu.getLabel());
			}
		}

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

				Set<SpringView> springViews = AnnotationUtils.getDeclaredRepeatableAnnotations(clazz, SpringView.class);
				SpringView springView = (springViews.iterator().hasNext()) ? springViews.iterator().next() : null;
				// LOGGER.info(
				// "SpringView, isNull: {}, # of Annotations: {}, isAopProxy:
				// {}, isCglibProxy: {}, isJdkDynamicProxy: {} ",
				// springView == null, springViews.size(),
				// AopUtils.isAopProxy(springView),
				// AopUtils.isCglibProxy(springView),
				// AopUtils.isJdkDynamicProxy(springView));

				Set<SpringComponent> springComponents = AnnotationUtils.getDeclaredRepeatableAnnotations(clazz,
						SpringComponent.class);
				SpringComponent springComponent = (springComponents.iterator().hasNext())
						? springComponents.iterator().next() : null;
				// LOGGER.info("SpringComponent, Name: {}",
				// springComponent.value());

				Set<MenuConfiguration> menuConfigurations = AnnotationUtils.getDeclaredRepeatableAnnotations(clazz,
						MenuConfiguration.class);
				MenuConfiguration menuConfiguration = (menuConfigurations.iterator().hasNext())
						? menuConfigurations.iterator().next() : null;
				// LOGGER.info("MenuConfiguration, Parent: {}",
				// menuConfiguration.parent());

				if (menuConfiguration == null) {
					continue;
				}

				// any of the menu roles is present on the user roles
				boolean skip = true;
				for (String menuRole : menuConfiguration.roles()) {
					String role = "ROLE_" + menuRole;
					if (roles.contains(role) || menuRole.equals("ALL")) {
						skip = false;
						break;
					}
				}
				if (skip) {
					continue;
				}

				String beanName = springView != null ? springView.name() : springComponent.value();

				Menu menu = new Menu();
				menu.setId(beanName);
				menu.setLabel(menuConfiguration.label());
				menu.setTooltip(menuConfiguration.tooltip());
				menu.setView(beanName);
				menu.setVisible(true);
				menu.setSeparator(false);
				menu.setOrder(menuConfiguration.order());
				menu.setPosition(menuConfiguration.position());
				menu.setParent(menuConfiguration.parent().equals("") ? null : menuConfiguration.parent());
				menu.setIcon(
						menuConfiguration.icon().equals(VaadinIcons.YOUTUBE_SQUARE) ? null : menuConfiguration.icon());
				menu.setRoles(menuConfiguration.roles());
				menus.add(menu);

			} else {

				LOGGER.error(
						"Menu configuration could not be read from the class's[{}] annotations: @SpringView present[{}], @MenuConfiguration present[{}], @SpringComponent[{}]",
						clazz.getName(), AnnotationUtils.getAnnotation(clazz, SpringView.class),
						AnnotationUtils.getAnnotation(clazz, MenuConfiguration.class),
						AnnotationUtils.getAnnotation(clazz, SpringComponent.class));

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
