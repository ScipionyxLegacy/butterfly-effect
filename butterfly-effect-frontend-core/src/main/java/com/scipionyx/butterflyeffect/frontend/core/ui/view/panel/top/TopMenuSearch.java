package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.top;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * @author Renato Mendes
 *
 */
class TopMenuSearch extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 
	*/
	void build() {

		//
		TextField search = new TextField();
		search.setHeight(25, Unit.PIXELS);
		search.setWidth(160, Unit.PIXELS);

		//
		Button searchButton = new Button();
		searchButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		searchButton.setIcon(VaadinIcons.SEARCH);

		this.addComponent(search);
		this.setComponentAlignment(search, Alignment.MIDDLE_LEFT);
		this.addComponent(searchButton);

	}

}
