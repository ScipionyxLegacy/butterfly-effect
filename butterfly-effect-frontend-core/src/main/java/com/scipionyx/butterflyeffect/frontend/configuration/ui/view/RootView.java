package com.scipionyx.butterflyeffect.frontend.configuration.ui.view;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractCommonView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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
// @DesignRoot()
// @SpringComponent("butterfly-effect-frontend-configuration:root")
// @UIScope()
//
@ViewConfiguration(configurationFile = "ConfigurationRootView.info")
@MenuConfiguration(position = Position.TOP_MAIN, label = "Configuration", group = "")
public class RootView extends AbstractCommonView {

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
