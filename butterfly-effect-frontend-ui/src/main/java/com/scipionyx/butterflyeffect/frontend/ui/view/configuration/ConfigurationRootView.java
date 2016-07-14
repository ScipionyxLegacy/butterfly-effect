package com.scipionyx.butterflyeffect.frontend.ui.view.configuration;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * 
 * 
 * @version 0.1.0
 * @author Renato Mendes
 *
 */
@DesignRoot()
@SpringComponent("butterfly-effect-frontend-configuration:root")
@UIScope()
@ViewConfiguration(configurationFile = "ConfigurationRootView.info")
public class ConfigurationRootView extends AbstractConfigurationView {

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
	public void doBuild() {
	}

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception {
	}

	@Override
	public void doBuildBottomArea(HorizontalLayout buttomAreaPanel) {
	}

}
