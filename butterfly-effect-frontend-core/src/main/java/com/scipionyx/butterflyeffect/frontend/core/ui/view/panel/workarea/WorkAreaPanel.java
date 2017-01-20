package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.workarea;

import java.io.IOException;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author Renato Mendes
 *
 */
public class WorkAreaPanel extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	private VerticalLayout workPanel;

	/**
	 * @throws IOException
	 * 
	 */
	public final void build() throws IOException {

		// Main layout for the work area
		GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setSizeFull();
		gridLayout.setSpacing(false);
		gridLayout.setColumnExpandRatio(0, 1);
		gridLayout.setColumnExpandRatio(1, 1);
		gridLayout.setRowExpandRatio(0, 1);
		gridLayout.setRowExpandRatio(1, 100);

		// Add WorkArea
		gridLayout.addComponent(buildWorkAreaLayout(), 0, 1, 1, 1);

		//
		this.addComponent(gridLayout);

	}

	/**
	 * 
	 * @return
	 */
	private Component buildWorkAreaLayout() {
		workPanel = new VerticalLayout();
		workPanel.setSpacing(true);
		workPanel.setMargin(true);
		return workPanel;
	}

	public VerticalLayout getWorkPanel() {
		return workPanel;
	}

}
