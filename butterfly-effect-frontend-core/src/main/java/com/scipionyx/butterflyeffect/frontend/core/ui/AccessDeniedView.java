package com.scipionyx.butterflyeffect.frontend.core.ui;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
//import org.vaadin.spring.annotation.PrototypeScope;

/**
 * A view shown when access to another view is denied.
 *
 * @author Petter Holmström (petter@vaadin.com)
 */
@SpringComponent
// @PrototypeScope
public class AccessDeniedView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439791676012756526L;
	private Label message;

	@PostConstruct
	void init() {
		setMargin(true);
		message = new Label();
		addComponent(message);
		message.addStyleName(ValoTheme.LABEL_FAILURE);
		message.setContentMode(ContentMode.HTML);
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		message.setValue(String.format("Sorry, but you don't have access to the view <b>%s</b>.", event.getViewName()));
	}
}
