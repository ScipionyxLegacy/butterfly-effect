package com.scipionyx.butterflyeffect.frontend.security.ui.view;

import java.util.HashMap;
import java.util.Map;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * 
 * 
 * @version 0.1.0
 * @author Renato Mendes
 *
 */
@SpringComponent(value = ConfigureAuthenticationView.VIEW_NAME)
@SpringView(name = ConfigureAuthenticationView.VIEW_NAME)
@UIScope
//
@ViewConfiguration(title = "Configure Authentication")
@MenuConfiguration(position = Position.TOP_RIGHT, label = "Authentication", group = "", order = 99, icon = FontAwesome.SIGN_OUT, parent = com.scipionyx.butterflyeffect.frontend.configuration.ui.view.RootView.VIEW_NAME)
public class ConfigureAuthenticationView extends AbstractView {

	public static final String VIEW_NAME = "butterfly-effect-frontend-security:configureauthentication";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, VerticalLayout> panels;

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {
	}

	/**
	 * 
	 */
	@Override
	public void doBuild() {
	}

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception {

		panels = new HashMap<>();

		// Git
		// Internal
		// LDAP
		// Active Directory

		Image imageGit = new Image("Git");
		imageGit.setEnabled(false);

		Image imageInternal = new Image("Internal");

		Image imageLDAP = new Image("LDAP");
		imageLDAP.setEnabled(false);

		Image imageAD = new Image("AD");
		imageAD.setEnabled(false);

		HorizontalLayout layout = new HorizontalLayout(imageAD, imageGit, imageInternal, imageLDAP);
		layout.setSpacing(true);
		layout.setMargin(true);

		//
		addInternal(workAreaPanel);

		workAreaPanel.addComponent(layout);

	}

	private void addInternal(VerticalLayout workAreaPanel) {

	}

}
