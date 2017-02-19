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
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Component;
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
 *
 */
@SpringUI(path = "/main")
@Theme("scipionyx")
@SpringViewDisplay
// @PreserveOnRefresh
// @Push
@Title("Butterfly Effect")
public class MainUI extends UI implements ViewDisplay {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private transient final SpringViewProvider viewProvider;

	private Panel viewContainer;

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
		root.setMargin(new MarginInfo(false, false, false, false));
		root.setSpacing(false);

		root.addComponent(topFactory.instance());

		setContent(root);

		viewContainer = new Panel();
		viewContainer.setStyleName(ValoTheme.PANEL_BORDERLESS);
		viewContainer.setSizeFull();

		root.addComponent(viewContainer);
		root.setExpandRatio(viewContainer, 1.0f);

		// Define the access denied view
		viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);

	}

	@Override
	public void showView(View view) {
		if (viewContainer != null) {
			viewContainer.setContent((Component) view);
		} else {
			setContent((Component) view);
		}
	}

}
