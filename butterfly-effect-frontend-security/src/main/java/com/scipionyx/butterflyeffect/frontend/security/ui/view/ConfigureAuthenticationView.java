package com.scipionyx.butterflyeffect.frontend.security.ui.view;

import java.util.HashMap;
import java.util.Map;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

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
@MenuConfiguration(position = Position.TOP_RIGHT, label = "Authentication", group = "", order = 99, icon = VaadinIcons.USERS, parent = ConfigurationRootView.VIEW_NAME)
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
	public void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception {

		panels = new HashMap<>();

		// Git
		// Internal
		// LDAP
		// Active Directory

		Button imageGit = new Button("Git");
		imageGit.setEnabled(false);
		imageGit.setStyleName(ValoTheme.BUTTON_HUGE);
		imageGit.addClickListener(new Command(imageGit, "git"));

		Button imageInternal = new Button("Internal");
		imageInternal.setEnabled(true);
		imageInternal.setStyleName(ValoTheme.BUTTON_HUGE);
		imageInternal.addClickListener(new Command(imageInternal, "internal"));

		Button imageLDAP = new Button("ldap");
		imageLDAP.setEnabled(true);
		imageLDAP.setStyleName(ValoTheme.BUTTON_HUGE);
		imageLDAP.addClickListener(new Command(imageLDAP, "ldap"));

		Button imageAD = new Button("AD");
		imageAD.setEnabled(false);
		imageAD.setStyleName(ValoTheme.BUTTON_HUGE);
		imageAD.addClickListener(new Command(imageAD, "AD"));

		HorizontalLayout layout = new HorizontalLayout(imageAD, imageGit, imageInternal, imageLDAP);
		layout.setSpacing(true);
		layout.setMargin(true);

		workAreaPanel.addComponent(layout);
		workAreaPanel.setComponentAlignment(layout, Alignment.TOP_CENTER);

		//
		addInternal(workAreaPanel);
		addLdap(workAreaPanel);

		showPanel("internal");

	}

	/**
	 * 
	 * @author Renato Mendes - rmendes@bottomline.com /
	 *         renato.mendes.1123@gmail.com
	 *
	 */
	private class Command implements Button.ClickListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String name;

		@SuppressWarnings("unused")
		private Button imageGit;

		public Command(Button imageGit, String name) {
			this.name = name;
			this.imageGit = imageGit;
		}

		@Override
		public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
			showPanel(name);

		}

	}

	/**
	 * 
	 * @param name
	 */
	private void showPanel(String name) {
		for (String key : panels.keySet()) {
			if (key.equals(name)) {
				panels.get(key).setVisible(true);
			} else
				panels.get(key).setVisible(false);
		}
	}

	/**
	 * 
	 * @param workAreaPanel
	 */
	private void addInternal(VerticalLayout workAreaPanel) {

		final Label label = new Label("Internal user databse.");

		final Button button = new Button("Activate");
		button.setStyleName(ValoTheme.BUTTON_FRIENDLY);

		VerticalLayout layout = new VerticalLayout(label, button);
		panels.put("internal", layout);

		workAreaPanel.addComponent(layout);
	}

	/**
	 * 
	 * @param workAreaPanel
	 */
	private void addLdap(VerticalLayout workAreaPanel) {

		Label label = new Label("LDAP user database.");

		Button button = new Button("Activate");
		button.setStyleName(ValoTheme.BUTTON_FRIENDLY);

		VerticalLayout layout = new VerticalLayout(label, button);
		panels.put("ldap", layout);

		workAreaPanel.addComponent(layout);

	}

}
