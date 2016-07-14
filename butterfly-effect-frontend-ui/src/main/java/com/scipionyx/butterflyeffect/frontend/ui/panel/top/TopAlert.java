package com.scipionyx.butterflyeffect.frontend.ui.panel.top;

import org.vaadin.teemu.VaadinIcons;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * @author Renato Mendes
 *
 */
public class TopAlert extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	void build() {

		this.setSizeFull();

		Button button = new Button(VaadinIcons.BELL_O);
		button.addStyleName(ValoTheme.BUTTON_BORDERLESS);

		this.addComponent(button);
		this.setComponentAlignment(button, Alignment.MIDDLE_CENTER);

	}

}
