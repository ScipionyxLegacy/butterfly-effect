package com.scipionyx.butterflyeffect.frontend.ui.view.common;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scipionyx.butterflyeffect.frontend.services.UserMenuService;
import com.vaadin.navigator.Navigator;

/**
 * 
 * @author Renato Mendes
 *
 */
public class NavigationCommand implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(NavigationCommand.class);

	private Navigator navigator;

	private String targetView;

	private UserMenuService userMenuService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param rootView
	 * @param targetView
	 */
	public NavigationCommand(UserMenuService userMenuService, String targetView) {
		this.userMenuService = userMenuService;
		this.targetView = targetView;
	}

	/**
	 * 
	 */
	public void navigate() {
		navigator = userMenuService.getNavigator();
		if (navigator != null) {
			navigator.navigateTo(targetView);
		} else {
			LOGGER.warn("Navigation is Null, it is not possible to navigate to: ?", targetView);
		}
	}

}
