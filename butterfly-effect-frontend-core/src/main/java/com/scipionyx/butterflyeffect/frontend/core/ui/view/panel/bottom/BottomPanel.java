package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.bottom;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * 
 * @author Renato Mendes
 *
 */
public class BottomPanel extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5264502784084569574L;

	/**
	 * 
	 */
	public void build() {

		//
		this.setSizeFull();
		this.setMargin(true);

		Label title = new Label();
		title.setCaption("Copyright ® 2015 - Scipionyx Software");

		this.addComponent(title);
		this.setComponentAlignment(title, Alignment.MIDDLE_CENTER);

	}

}
