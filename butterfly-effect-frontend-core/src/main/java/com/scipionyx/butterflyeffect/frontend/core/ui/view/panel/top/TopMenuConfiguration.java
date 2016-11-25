package com.scipionyx.butterflyeffect.frontend.ui.panel.top;

import com.scipionyx.butterflyeffect.frontend.services.UserMenuService;
import com.scipionyx.butterflyeffect.frontend.ui.view.common.NavigationCommand;
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

	private UserMenuService userMenuService;

	/**
	 * 
	 * 
	 * 
	 */
	public void build() {

		this.addStyleName(ValoTheme.MENUBAR_BORDERLESS);

		MenuItem root = this.addItem("", FontAwesome.GEAR, null);

		NavigationCommand command = new NavigationCommand(userMenuService,
				"butterfly-effect-frontend-configuration:root");

		root.setCheckable(true);

		root.setCommand(new Command() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				root.setChecked(true);
				command.navigate();
			}

		});

	}

	public void setUserMenuService(UserMenuService userMenuService) {
		this.userMenuService = userMenuService;
	}

}
