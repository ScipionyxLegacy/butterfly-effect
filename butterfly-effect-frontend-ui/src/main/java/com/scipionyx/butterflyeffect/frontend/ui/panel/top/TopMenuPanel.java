package com.scipionyx.butterflyeffect.frontend.ui.panel.top;

import com.scipionyx.butterflyeffect.frontend.services.UserMenuService;
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
	public void build(UserMenuService userMenuService) {

		// Defines look & feel and layout
		this.setColumns(2);
		this.setRows(1);
		this.setStyleName(ValoTheme.MENU_ROOT);
		this.setSpacing(false);
		this.setWidth(100, Unit.PERCENTAGE);

		// Left Component
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		this.addComponent(layout, 0, 0);
		this.setComponentAlignment(layout, Alignment.MIDDLE_LEFT);

		// Logo
		TopMenuLogo topMenuLogo = new TopMenuLogo();
		topMenuLogo.build();
		layout.addComponent(topMenuLogo);

		TopAlert topAlert = new TopAlert();
		topAlert.build();
		layout.addComponent(topAlert);

		// Menu
		TopMenuBar topMenuBar = new TopMenuBar();
		topMenuBar.build(userMenuService);
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

		// Help
		TopMenuHelp topHelpMenu = new TopMenuHelp();
		topHelpMenu.build();
		topRight.addComponent(topHelpMenu);

		// System Configuration
		TopMenuConfiguration topMenuConfiguration = new TopMenuConfiguration();
		topMenuConfiguration.setUserMenuService(userMenuService);
		topMenuConfiguration.build();
		topRight.addComponent(topMenuConfiguration);

		// User
		TopMenuUser topMenuUser = new TopMenuUser();
		topMenuUser.build();
		topRight.addComponent(topMenuUser);

	}

}
