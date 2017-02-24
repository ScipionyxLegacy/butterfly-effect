package com.scipionyx.butterflyeffect.frontend.security.ui.view;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * 
 * 
 * @version 0.1.0
 * @author Renato Mendes
 *
 */
@SpringComponent(value = ConfigurationRootView.VIEW_NAME)
@SpringView(name = ConfigurationRootView.VIEW_NAME)
@UIScope
//
@ViewConfiguration(title = "Security Configuration")
@MenuConfiguration(position = Position.TOP_RIGHT, label = "Security", group = "", order = 10, icon = VaadinIcons.LOCK, parent = com.scipionyx.butterflyeffect.frontend.configuration.ui.view.RootView.VIEW_NAME)
public class ConfigurationRootView extends AbstractView {

	public static final String VIEW_NAME = "butterfly-effect-frontend-security:configuration_root";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {
	}

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception {

	}

}
