package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.top;

import java.util.List;

import com.scipionyx.butterflyeffect.frontend.model.Menu;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * @author Renato Mendes
 *
 */
public class TopMenuRight extends MenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param menu
	 * @param userMenuService
	 * 
	 */
	public void build(Menu menu, List<Menu> list) {
		this.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		@SuppressWarnings("unused")
		MenuItem root = addRoot(menu, list);
		// addChildren(root, menu, list);
	}

	/**
	 * 
	 * @param menu
	 * @param list
	 * @return
	 */
	private MenuItem addRoot(Menu menu, List<Menu> list) {

		Command command = null;

		if (menu.getView() != null && !hasChildren(menu, list)) {
			command = new Command() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void menuSelected(MenuItem selectedItem) {
					getUI().getNavigator().navigateTo(menu.getView());
				}
			};
		}

		MenuItem root = null;
		if (menu.getIcon() == VaadinIcons.YOUTUBE_SQUARE) {
			root = this.addItem(menu.getLabel(), command);
		} else {
			root = this.addItem(menu.getLabel(), menu.getIcon(), command);
		}

		for (Menu menuL2 : list) {
			if (menuL2.getParent() != null && menuL2.getParent().equals(menu.getId())) {
				addChildren(root, menuL2, list);
			}
		}

		return root;

	}

	/**
	 * @param root
	 * 
	 */
	private void addChildren(MenuItem root, Menu menu, List<Menu> list) {

		Command command = new Command() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				getUI().getNavigator().navigateTo(menu.getView());
			}
		};

		MenuItem item = null;

		if (menu.getIcon() == VaadinIcons.YOUTUBE_SQUARE)
			item = root.addItem(menu.getLabel(), command);
		else
			item = root.addItem(menu.getLabel(), menu.getIcon(), command);

		for (Menu menuL2 : list) {
			if (menuL2.getParent() != null && menuL2.getParent().equals(menu.getId())) {
				addChildren(item, menuL2, list);
			}
		}

	}

	/**
	 * 
	 * @param list
	 * @param menu
	 * @return
	 */
	private boolean hasChildren(Menu menu, List<Menu> list) {
		for (Menu menu2 : list) {
			if (menu.getId().equals(menu2.getParent())) {
				return true;
			}
		}
		return false;
	}

}
