package com.scipionyx.butterflyeffect.frontend.security.ui.view;

import javax.annotation.PostConstruct;

import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@SpringComponent(value = LoginView.VIEW_NAME)
@SpringView(name = LoginView.VIEW_NAME)
@UIScope

//
@MenuConfiguration(group = "", label = "Login")
public class LoginView extends VerticalLayout implements View {

	public final static String VIEW_NAME = "butterfly-effect-frontend-security:login";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @throws Exception
	 */
	@PostConstruct
	public void init() throws Exception {

		FormLayout content = new FormLayout();
		TextField username = new TextField("Username");
		content.addComponent(username);
		PasswordField password = new PasswordField("Password");
		content.addComponent(password);

		this.addComponents(content);

	}

	/**
	 * 
	 */
	@Override
	public void enter(ViewChangeEvent event) {
	}

}
