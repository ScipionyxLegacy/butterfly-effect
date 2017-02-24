package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.left;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
class LeftPanelBottom extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param leftPanelTop
	 * 
	 */
	void buid(final LeftPanelTop leftPanelTop) {
		//
		this.setWidth(100, Unit.PERCENTAGE);
		this.setHeight(200, Unit.PIXELS);

		//
		Button expandButton = new Button();
		expandButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		expandButton.setIcon(VaadinIcons.ARROW_RIGHT);
		leftPanelTop.setCollapsed();
		this.addComponent(expandButton);
		this.setComponentAlignment(expandButton, Alignment.BOTTOM_RIGHT);

		expandButton.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (event.getComponent().getIcon() == VaadinIcons.ARROW_RIGHT) {
					leftPanelTop.getParent().setWidth(LeftPanel.SIZE_EXPANDED, Unit.PIXELS);
					event.getComponent().setIcon(VaadinIcons.ARROW_LEFT);
				} else {
					leftPanelTop.getParent().setWidth(LeftPanel.SIZE_COLAPSED, Unit.PIXELS);
					event.getComponent().setIcon(VaadinIcons.ARROW_RIGHT);
				}
				leftPanelTop.toggle();
			}
		});

	}

}
