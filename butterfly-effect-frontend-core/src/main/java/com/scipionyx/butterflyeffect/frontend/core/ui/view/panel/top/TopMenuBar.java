package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.top;

import java.util.List;

import com.scipionyx.butterflyeffect.frontend.model.Menu;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.vaadin.icons.VaadinIcons;
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
	void build(List<Menu> list) {
		this.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		this.setEnabled(true);
		buildMainMenuBar(list);
	}

	/**
	 * 
	 * @param list
	 * @param menuBar
	 */
	private void buildMainMenuBar(List<Menu> list) {

		for (Menu menu : list) {

			if (menu.getPosition() == Position.TOP_MAIN && menu.getParent() == null) {

				boolean hasChildren = hasChildren(menu, list);

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

				MenuItem root = null;

				if (hasChildren) {
					root = this.addItem(menu.getLabel(), menu.getIcon(), null);
					root.addItem("Overview", VaadinIcons.ARROWS, command);
					addChildren(root, menu, list);
				} else {
					root = this.addItem(menu.getLabel(), menu.getIcon(), command);
				}

			}
		}

	}

	/**
	 * @param root
	 * 
	 */
	private void addChildren(MenuItem root, Menu menu, List<Menu> list) {

		for (Menu menuL2 : list) {

			// Skip the current menu & roots
			if (menuL2.equals(menu) || menuL2.getParent() == null) {
				continue;
			}

			// find the children
			if (menuL2.getParent().equals(menu.getId())) {
				MenuItem newItem = createItem(menuL2, root);
				if (hasChildren(menuL2, list)) {
					addChildren(newItem, menuL2, list);
				}
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

	/**
	 * @param root
	 * @return
	 * 
	 */
	private MenuItem createItem(Menu menu, MenuItem root) {
		String label = (menu.getLabel() != null) ? menu.getLabel() : "NO-LABEL";
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
		return root.addItem(label, menu.getIcon(), command);
	}

}
