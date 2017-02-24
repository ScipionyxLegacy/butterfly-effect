package com.scipionyx.butterflyeffect.frontend.security.ui;

import java.util.Random;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI(path = "/login")
@Title("LoginPage")
@Theme("scipionyx")
public class LoginUI extends UI implements ViewDisplay {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {

		FormLayout content = null;

		//
		TextField userName = new TextField("Username");
		userName.setRequiredIndicatorVisible(true);
		PasswordField password = new PasswordField("Password");
		password.setRequiredIndicatorVisible(true);

		Button buttonLogin = new Button("Login", new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				//
				Authentication auth = new UsernamePasswordAuthenticationToken(userName.getValue(), password.getValue());
				SecurityContextHolder.getContext().setAuthentication(auth);

				//
				getPage().setLocation("/main#!butterfly-effect-frontend-core:home");

			}
		});
		buttonLogin.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		buttonLogin.addStyleName(ValoTheme.BUTTON_PRIMARY);
		buttonLogin.setClickShortcut(KeyCode.ENTER);
		//
		Button buttonSignup = new Button("Sign up", new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

			}
		});
		buttonSignup.setEnabled(false);
		HorizontalLayout buttonLayout = new HorizontalLayout(buttonLogin, buttonSignup);
		buttonLayout.setSpacing(true);

		content = new FormLayout(userName, password, buttonLayout);
		content.setMargin(true);
		content.setStyleName(ValoTheme.PANEL_WELL);

		//
		Random random = new Random();
		int nextInt = random.nextInt(4);
		ThemeResource resource = new ThemeResource("images/login_" + nextInt + ".jpg");
		Image image = new Image();
		image.setSource(resource);

		HorizontalLayout bk = new HorizontalLayout(content, image);
		bk.setMargin(true);
		bk.setSizeFull();
		bk.setMargin(false);
		bk.setExpandRatio(content, 1);
		bk.setExpandRatio(image, 3);
		bk.setComponentAlignment(content, Alignment.MIDDLE_CENTER);

		setContent(bk);
		setFocusedComponent(userName);

	}

	@Override
	public void showView(View view) {
		setContent((Component) view);
	}

}
