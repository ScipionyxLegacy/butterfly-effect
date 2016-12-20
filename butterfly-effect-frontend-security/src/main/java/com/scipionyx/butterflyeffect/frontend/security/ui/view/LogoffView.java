package com.scipionyx.butterflyeffect.frontend.security.ui.view;

import org.springframework.security.core.context.SecurityContextHolder;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Target;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * 
 * 
 * @version 0.1.0
 * @author Renato Mendes
 *
 */
@SpringComponent(value = LogoffView.VIEW_NAME)
@SpringView(name = LogoffView.VIEW_NAME)
@UIScope
//
@ViewConfiguration(title = "Logoff")
@MenuConfiguration(position = Position.TOP_RIGHT, label = "Logoff", group = "", order = 99, icon = FontAwesome.SIGN_OUT, parent = RootView.VIEW_NAME, target = Target.DIALOG)
public class LogoffView extends AbstractView {

	public static final String VIEW_NAME = "butterfly-effect-frontend-security:logoff";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

		Button button = new Button("Logoff");

		button.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				SecurityContextHolder.clearContext();
				Page.getCurrent().open("", null);

			}
		});

		workAreaPanel.addComponent(button);

	}

}
