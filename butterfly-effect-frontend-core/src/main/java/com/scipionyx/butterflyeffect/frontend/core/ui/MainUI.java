package com.scipionyx.butterflyeffect.frontend.core.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.scipionyx.butterflyeffect.frontend.core.services.UserMenuService;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.root.RootView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

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

		final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        //navigationBar.addComponent(createNavigationButton("Prototype Scoped View", PrototypeScopedView.VIEW_NAME));
        //navigationBar.addComponent(createNavigationButton("UI Scoped View", UIScopedView.VIEW_NAME));
        //navigationBar.addComponent(createNavigationButton("View Scoped View", ViewScopedView.VIEW_NAME));
        //navigationBar.addComponent(createNavigationButton("Access Control", AccessControlView.VIEW_NAME));
        root.addComponent(navigationBar);

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);

        viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.setErrorView(new ErrorView()); // You can still create the error view yourself if you want to.
        navigator.addProvider(viewProvider);
		
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

	/**
	 * 
	 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
	 *
	 */
	private class ErrorView extends VerticalLayout implements View {

		private Label message;

		ErrorView() {
			setMargin(true);
			message = new Label("Please click one of the buttons at the top of the screen.");
			addComponent(message);
			message.addStyleName(ValoTheme.LABEL_COLORED);
		}

		@Override
		public void enter(ViewChangeListener.ViewChangeEvent event) {
		}
	}

}