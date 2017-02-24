package com.scipionyx.butterflyeffect.frontend.security.ui.view;

import org.springframework.security.core.context.SecurityContextHolder;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Target;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
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
@MenuConfiguration(position = Position.TOP_RIGHT, label = "Logoff", group = "", order = 99, icon = VaadinIcons.SIGN_OUT, parent = RootView.VIEW_NAME, target = Target.DIALOG)
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
	public void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception {
	}

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {
		SecurityContextHolder.clearContext();
		Page.getCurrent().open("", null);
	}

}
