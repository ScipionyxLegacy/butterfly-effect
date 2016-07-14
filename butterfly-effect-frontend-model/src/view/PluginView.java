package com.scipionyx.butterflyeffect.plugin.ui.view;

import com.scipionyx.butterflyeffect.frontend.model.Title;
import com.scipionyx.butterflyeffect.frontend.ui.view.common.AbstractView;
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
@SpringComponent("pluginView")
@UIScope()
public class PluginView extends AbstractView {

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
		// getTitlePanel().setTitle("Welcome - Plugin View - doBuild");
	}

	@Override
	public void doBuildLeftMenu(VerticalLayout leftMenuPanel) {
	}

	@Override
	public void doBuildWorkArea(VerticalLayout workAreaPanel, Title tile) {
	}

	@Override
	public void doBuildBottomArea(HorizontalLayout buttomAreaPanel) {
	}

}
