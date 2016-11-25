package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.top;

import org.vaadin.teemu.VaadinIcons;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
class TopMenuHelp extends MenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 
	 * 
	 */
	void build() {

		this.addStyleName(ValoTheme.MENUBAR_BORDERLESS);

		this.addItem("", VaadinIcons.QUESTION_CIRCLE_O, new Command() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
			}
		});
	}

}
