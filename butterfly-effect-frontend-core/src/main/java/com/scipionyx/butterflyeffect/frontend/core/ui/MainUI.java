/*
 * Copyright 2015 The original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scipionyx.butterflyeffect.frontend.core.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.top.TopFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * Main UI of the navigation sample UI. The UI contains three different views
 * with different scopes. The user can navigate between the views by clicking on
 * buttons on a navigation bar at the top of the window.
 *
 */
@SpringUI
@Theme(ValoTheme.THEME_NAME)
// @Push(transport = Transport.WEBSOCKET_XHR)
@SpringViewDisplay
public class MainUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final SpringViewProvider viewProvider;

	// Main Panels
	@Autowired
	protected TopFactory topFactory;

	@Autowired
	public MainUI(SpringViewProvider viewProvider) {
		this.viewProvider = viewProvider;
	}

	/**
	 * 
	 */
	@Override
	protected void init(VaadinRequest request) {

		// Top Menu

		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		root.setMargin(new MarginInfo(false, true, true, true));
		root.setSpacing(true);

		root.addComponent(topFactory.instance());

		setContent(root);

		final Panel viewContainer = new Panel();
		viewContainer.setSizeFull();

		root.addComponent(viewContainer);
		root.setExpandRatio(viewContainer, 1.0f);

		// Define the access denied view
		viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);

		// Defining Navigation Component
		Navigator navigator = new Navigator(this, viewContainer);
		navigator.setErrorView(new ErrorView());
		navigator.addProvider(viewProvider);

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * @author Renato Mendes - rmendes@bottomline.com /
	 *         renato.mendes.1123@gmail.com
	 *
	 */
	private class ErrorView extends VerticalLayout implements View {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Label message;

		ErrorView() {
			setMargin(true);
			message = new Label("ErrorView");
			message.addStyleName(ValoTheme.LABEL_COLORED);
			addComponent(message);
		}

		@Override
		public void enter(ViewChangeListener.ViewChangeEvent event) {
			message.setCaption("Error:" + event.getViewName());

		}
	}

}
