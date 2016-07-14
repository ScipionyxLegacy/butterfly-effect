package com.scipionyx.butterflyeffect.frontend.ui.panel.top;

import java.util.HashMap;
import java.util.Map;

import com.scipionyx.butterflyeffect.frontend.model.Menu;
import com.scipionyx.butterflyeffect.frontend.services.UserMenuService;
import com.scipionyx.butterflyeffect.frontend.ui.view.common.NavigationCommand;
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

	/**
	 * 
	 */
	void build(UserMenuService userMenuService) {
		this.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		buildMainMenuBar(userMenuService);
	}

	/**
	 * 
	 * @param menuBar
	 */
	@SuppressWarnings("unused")
	private void buildMainMenuBar(UserMenuService userMenuService) {
		//

		Map<String, NavigationCommand> menus = new HashMap<>();

		for (Menu menu : userMenuService.getMenuService().getConfigurations()) {

			String id = (menu.getId() != null) ? menu.getId() : "NO-LABEL";
			String label = (menu.getLabel() != null) ? menu.getLabel() : "NO-LABEL";

			NavigationCommand command = (menu.getView() != null)
					? new NavigationCommand(userMenuService, menu.getView()) : null;

			if (menu.getParent() == null) {

				this.addItem(label, new Command() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void menuSelected(MenuItem selectedItem) {
						//
						selectedItem.setChecked(true);
						command.navigate();
					}
				});

			}
		}

	}

}
