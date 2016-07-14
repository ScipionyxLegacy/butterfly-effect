package com.scipionyx.butterflyeffect.frontend.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.scipionyx.butterflyeffect.frontend.services.UserMenuService;
import com.scipionyx.butterflyeffect.frontend.ui.view.root.RootView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Theme("butterflyeffect")
@SpringUI(path = "")
@Title("Butterfly Effect")
public class MainUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired(required = true)
	private UserMenuService userMenuService;

	@Autowired(required = true)
	private RootView rootView;

	/**
	 * <p>
	 * After a UI has been created by the application, it is initialized using
	 * {@link #init(VaadinRequest)}. This method is intended to be overridden by
	 * the developer to add components to the user interface and initialize
	 * non-component functionality. The component hierarchy must be initialized
	 * by passing a {@link Component} with the main layout or other content of
	 * the view to {@link #setContent(Component)} or to the constructor of the
	 * UI.
	 * </p>
	 */
	@Override
	protected void init(VaadinRequest request) {

		////////////////////////
		// Initiate Root view //
		////////////////////////

		// Start the navigation
		setNavigator(new Navigator(this, this));

		// Build Views
		try {
			userMenuService.build(getNavigator(), rootView);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}