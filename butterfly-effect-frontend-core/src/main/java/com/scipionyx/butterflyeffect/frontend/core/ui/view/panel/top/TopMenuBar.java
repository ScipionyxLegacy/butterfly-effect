package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.top;

import com.scipionyx.butterflyeffect.frontend.core.services.UserMenuService;
import com.scipionyx.butterflyeffect.frontend.model.Menu;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
class TopMenuBar extends MenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2071225172270929556L;

	private UserMenuService userMenuService;

	/**
	 * 
	 */
	void build(UserMenuService userMenuService) {
		this.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		this.userMenuService = userMenuService;
		this.setEnabled(true);
		buildMainMenuBar();
	}

	/**
	 * 
	 * @param menuBar
	 */
	private void buildMainMenuBar() {

		for (Menu menu : userMenuService.getMenus()) {

			if (menu.getPosition() == Position.TOP_MAIN) {

				String label = (menu.getLabel() != null) ? menu.getLabel() : "NO-LABEL";

				if (menu.getParent() == null) {

					this.addItem(label, new Command() {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void menuSelected(MenuItem selectedItem) {
							getUI().getNavigator().navigateTo(menu.getView());
						}
					});

				}

			}
		}

	}

}
