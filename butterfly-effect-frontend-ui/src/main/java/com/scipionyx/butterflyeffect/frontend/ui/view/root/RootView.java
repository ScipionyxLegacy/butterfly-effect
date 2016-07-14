package com.scipionyx.butterflyeffect.frontend.ui.view.root;

import com.scipionyx.butterflyeffect.frontend.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.frontend.ui.view.configuration.ViewConfiguration;
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
@SpringComponent("rootView")
@UIScope()
@ViewConfiguration(configurationFile = "RootView.info")
public class RootView extends AbstractView {

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

	@Override
	public void doBuild() {
	}

	@Override
	public void doBuildLeftMenu(VerticalLayout leftMenuPanel) {
	}

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workAreaPanel) {
	}

	@Override
	public void doBuildBottomArea(HorizontalLayout buttomAreaPanel) {
	}

}
