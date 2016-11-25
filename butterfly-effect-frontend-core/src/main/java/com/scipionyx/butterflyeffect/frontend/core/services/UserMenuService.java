package com.scipionyx.butterflyeffect.frontend.core.services;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.scipionyx.butterflyeffect.configuration.model.leftmenu.LeftConfigurationMenuItem;
import com.scipionyx.butterflyeffect.frontend.configuration.services.LeftConfigurationMenuService;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.root.RootView;
import com.scipionyx.butterflyeffect.frontend.model.Navigation;
import com.scipionyx.butterflyeffect.ui.model.ButterflyView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

/**
 * 
 * This service is responsible for creating and rendering the navigation
 * depending on the user permissions
 * 
 * TODO - obtain logged user <br>
 * TODO - identify menus that should not be rendered<br>
 * 
 * 
 * @author Renato Mendes
 *
 */
@SpringComponent("userMenuService")
@UIScope()
public class UserMenuService implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuService.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private MenuService menuService;

	@Autowired
	private NavigationService navigationService;

	@Autowired
	private LeftConfigurationMenuService leftConfigurationMenuService;

	private Navigator navigator;

	private boolean built;

	/**
	 * @param rootView
	 * @throws Exception
	 * 
	 */
	public void build(Navigator navigator, RootView rootView) throws Exception {

		this.navigator = navigator;

		// Build Views
		createNavigationBuildViews(rootView);

		built = true;

	}

	/**
	 * TODO - Handle parent that does no exist<br>
	 * TODO - Handle view that does not exist as a bean
	 * 
	 * @param navigator
	 * @throws Exception
	 */
	private void createNavigationBuildViews(RootView rootView) throws Exception {

		// Add root view to the Navigator.
		navigator.addView("", rootView);
		navigator.addView("butterfly-effect-frontend-core:root", rootView);

		//
//		for (Navigation navigation : navigationService.getConfigurations()) {
//			Object viewObject = applicationContext.getBean(navigation.getView());
//			if (viewObject != null) {
//				navigator.addView(navigation.getId(), (View) viewObject);
//				if (viewObject instanceof ButterflyView) {
//					ButterflyView view = (ButterflyView) viewObject;
//					view.doBuild();
//				}
//			} else {
//				LOGGER.error("Navigation View Bean ({}) not found", navigation.getView());
//			}
//		}
//
//		for (LeftConfigurationMenuItem navigation : leftConfigurationMenuService.getConfigurations()) {
//			if (navigation.getView() == null)
//				continue;
//			if (applicationContext.containsBean(navigation.getView())) {
//				Object viewObject = applicationContext.getBean(navigation.getView());
//				if (viewObject != null) {
//					navigator.addView(navigation.getId(), (View) viewObject);
//					if (viewObject instanceof ButterflyView) {
//						ButterflyView view = (ButterflyView) viewObject;
//						view.doBuild();
//					}
//				} else {
//					LOGGER.error("Navigation View Bean ({}) not found", navigation.getView());
//				}
//			}
//		}

		//

	}

	/**
	 * 
	 * @return
	 */
	public Navigator getNavigator() {
		return navigator;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public boolean isBuilt() {
		return built;
	}

}
