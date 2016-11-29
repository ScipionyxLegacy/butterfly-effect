package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.top.old;

import com.scipionyx.butterflyeffect.ui.model.ITopMenuComponent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
class TopMenuConfiguration extends MenuBar implements ITopMenuComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 
	 * 
	 */
	public void build() {

		this.addStyleName(ValoTheme.MENUBAR_BORDERLESS);

		MenuItem root = this.addItem("", FontAwesome.GEAR, null);

		root.setCheckable(true);

		root.setCommand(new Command() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				root.setChecked(true);

			}

		});

	}

}
