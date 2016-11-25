package com.scipionyx.butterflyeffect.frontend.ui.panel.top;

import org.vaadin.teemu.VaadinIcons;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * @author Renato Mendes
 *
 */
class TopMenuUser extends MenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public void build() {

		this.addStyleName(ValoTheme.MENUBAR_BORDERLESS);

		MenuItem root = this.addItem("", VaadinIcons.USER, null);

		root.addItem("User Profile", new Command() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub

			}
		});

		root.addItem("Change Password", new Command() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub

			}
		});

		root.addSeparator();

		root.addItem("Logout", new Command() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub

			}
		});

	}

}
