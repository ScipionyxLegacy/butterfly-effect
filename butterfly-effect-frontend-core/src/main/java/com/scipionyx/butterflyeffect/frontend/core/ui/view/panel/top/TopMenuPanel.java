package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.top;

import com.scipionyx.butterflyeffect.frontend.core.services.MenuService;
import com.scipionyx.butterflyeffect.frontend.model.Menu;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * @author Renato Mendes
 *
 */
class TopMenuPanel extends GridLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2233004354001028468L;

	/**
	 * @param userMenuService
	 * 
	 */
	public void build(MenuService userMenuService) {

		// Defines look & feel and layout
		this.setColumns(2);
		this.setRows(1);
		this.setStyleName(ValoTheme.MENU_ROOT);
		this.setSpacing(false);
		this.setWidth(100, Unit.PERCENTAGE);

		// Left Component
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(false);
		this.addComponent(layout, 0, 0);
		this.setComponentAlignment(layout, Alignment.MIDDLE_LEFT);

		// Logo
		TopMenuLogo topMenuLogo = new TopMenuLogo();
		topMenuLogo.build();
		layout.addComponent(topMenuLogo);

		// Menu
		TopMenuBar topMenuBar = new TopMenuBar();
		topMenuBar.build(userMenuService.getMenus());
		layout.addComponent(topMenuBar);
		layout.setComponentAlignment(topMenuBar, Alignment.MIDDLE_LEFT);

		// Top Right
		HorizontalLayout topRight = new HorizontalLayout();
		topRight.setSpacing(true);
		topRight.setMargin(new MarginInfo(false, true, false, false));
		this.addComponent(topRight, 1, 0);
		this.setComponentAlignment(topRight, Alignment.MIDDLE_RIGHT);

		// Search
		TopMenuSearch topMenuSearch = new TopMenuSearch();
		topMenuSearch.build();
		topRight.addComponent(topMenuSearch);

		// Adding Dynamic Menus on the right panel
		for (Menu menu : userMenuService.getMenus()) {
			if (menu.getPosition() == Position.TOP_RIGHT && menu.getParent() == null) {
				TopMenuRight panel = new TopMenuRight();
				panel.build(menu, userMenuService.getMenus());
				topRight.addComponent(panel);
			}
		}

	}

}
