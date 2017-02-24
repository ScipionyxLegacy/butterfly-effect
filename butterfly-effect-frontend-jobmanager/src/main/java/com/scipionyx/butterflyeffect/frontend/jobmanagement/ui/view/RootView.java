package com.scipionyx.butterflyeffect.frontend.jobmanagement.ui.view;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * 
 * 
 * @version 0.1.0
 * @author Renato Mendes
 *
 */
@SpringComponent(value = RootView.VIEW_NAME)
@SpringView(name = RootView.VIEW_NAME)
@UIScope
//
@ViewConfiguration(title = "Job Management")
@MenuConfiguration(position = Position.TOP_RIGHT, label = "Job Management", group = "", order = 1, parent = com.scipionyx.butterflyeffect.frontend.configuration.ui.view.RootView.VIEW_NAME, icon = VaadinIcons.BRIEFCASE, roles = {
		"ADMIN" })
public class RootView extends AbstractView {

	public static final String VIEW_NAME = "butterfly-effect-frontend-jobmanagement:root";

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

		Label label1 = new Label("Job Queue");
		Label label2 = new Label("Jobs Configuration");
		Label label3 = new Label("Workers Configuration");

		workAreaPanel.addComponents(label1, label2, label3);

	}

}
