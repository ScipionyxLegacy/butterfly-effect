package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.left;

import com.scipionyx.butterflyeffect.frontend.model.ViewConfigurationInformation;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * TODO - Left Panel - Fix the background color <br>
 * TODO - Left Panel - Add right border <br>
 * TODO - Left Panel - Fix the expand button <br>
 * TODO - Left Panel - Create Icon <br>
 * TODO - Left Panel - Create Title <br>
 * TODO - Left Panel - Create SubTitle <br>
 * 
 * @author Renato Mendes
 *
 */
public class LeftPanel extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VerticalLayout internal;

	// private LeftPanelTop leftPanelTop;

	final static float SIZE_EXPANDED = 250;

	final static float SIZE_COLAPSED = 70;

	final static int MARGIN = 20;

	/**
	 * 
	 */
	public void build(ViewConfigurationInformation information) {

		this.setSizeFull();
		this.setSpacing(false);
		this.setMargin(false);
		this.addStyleName(ValoTheme.PANEL_BORDERLESS);

		// Top Margin
		// VerticalLayout topMargin = new VerticalLayout();
		// topMargin.setHeight(MARGIN, Unit.PIXELS);
		// this.addComponent(topMargin);
		// this.setExpandRatio(topMargin, 1);

		// Parent Panel
		// HorizontalLayout parentPanel = new HorizontalLayout();
		// parentPanel.setSpacing(false);
		// parentPanel.setSizeFull();
		// parentPanel.setHeight(100, Unit.PERCENTAGE);
		// parentPanel.setStyleName(ValoTheme.PANEL_WELL);
		// this.addComponent(parentPanel);
		// this.setComponentAlignment(parentPanel, Alignment.TOP_LEFT);

		// Left Margin
		// HorizontalLayout leftMargin = new HorizontalLayout();
		// leftMargin.setCaption("LM");
		// leftMargin.setWidth(100, Unit.PIXELS);
		// leftMargin.setStyleName(ValoTheme.PANEL_WELL);
		// parentPanel.addComponent(leftMargin);

		// Main Panel
		// VerticalLayout mainPanel = new VerticalLayout();
		// mainPanel.setStyleName(ValoTheme.PANEL_WELL);
		// parentPanel.addComponent(mainPanel);

		// Right Margin
		// HorizontalLayout rightMargin = new HorizontalLayout();
		// rightMargin.setWidth(100, Unit.PIXELS);
		// parentPanel.addComponent(rightMargin);

		//

		// >>>>
		// leftPanelTop = new LeftPanelTop();
		// leftPanelTop.build(information);
		// mainPanel.addComponent(leftPanelTop);

		//
		internal = new VerticalLayout();
		internal.setSizeFull();
		this.addComponent(internal);
		this.setComponentAlignment(internal, Alignment.TOP_LEFT);
		this.setExpandRatio(internal, 10);

		LeftPanelBottom bottom = new LeftPanelBottom();
		bottom.setStyleName(ValoTheme.PANEL_WELL);
		// bottom.buid(leftPanelTop);
		this.addComponent(bottom);
		this.setComponentAlignment(bottom, Alignment.BOTTOM_CENTER);

	}

	/**
	 * 
	 * @return
	 */
	public VerticalLayout getInternal() {
		return internal;
	}

}
