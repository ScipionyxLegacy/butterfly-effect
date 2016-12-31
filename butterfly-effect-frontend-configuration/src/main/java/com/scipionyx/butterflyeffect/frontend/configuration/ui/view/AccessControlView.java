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
package com.scipionyx.butterflyeffect.frontend.configuration.ui.view;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.scipionyx.butterflyeffect.frontend.core.services.MenuService;
import com.scipionyx.butterflyeffect.frontend.model.Menu;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * A view that demonstrates how
 * {@link com.vaadin.spring.access.ViewAccessControl}s can be used to control
 * access to views. In this example, the access delegate is the UI scoped view,
 * but you can also use e.g. singleton access delegates.
 *
 * @author Petter Holmström (petter@vaadin.com)
 */
@UIScope
@SpringComponent(value = AccessControlView.VIEW_NAME)
@SpringView(name = AccessControlView.VIEW_NAME)

//
@ViewConfiguration(title = "Access Control")
@MenuConfiguration(position = Position.TOP_RIGHT, label = "Access", group = "", order = 1, parent = RootView.VIEW_NAME, roles = {
		"ADMIN" })
public class AccessControlView extends VerticalLayout implements View, ViewAccessControl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "butterfly-effect-frontend-configuration:access-control";

	private final Set<String> allowedViews = new HashSet<>();
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private MenuService userMenuService;

	@PostConstruct
	private void init() {

		allowedViews.add(VIEW_NAME);

		setMargin(true);
		setSpacing(true);
		addComponent(new Label(
				"Here you can control the access to the different views within this particular UI. Uncheck a few boxes and try to navigate to their corresponding views. "
						+ "In a real application, you would probably base the access decision on the current user's role or something similar."));

		for (Menu menu : userMenuService.getMenus()) {
			allowedViews.add(menu.getView());
			addComponent(createViewCheckbox(menu.getLabel(), menu.getView()));
		}

	}

	/**
	 * 
	 * @param caption
	 * @param viewName
	 * @return
	 */
	private CheckBox createViewCheckbox(String caption, final String viewName) {
		final CheckBox checkBox = new CheckBox(caption, allowedViews.contains(viewName));
		checkBox.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				if (checkBox.getValue()) {
					allowedViews.add(viewName);
				} else {
					allowedViews.remove(viewName);
				}
			}
		});
		return checkBox;
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
	}

	/**
	 * 
	 */
	@Override
	public boolean isAccessGranted(UI ui, String beanName) {
		
		final SpringView annotation = applicationContext.findAnnotationOnBean(beanName, SpringView.class);
		
		if (annotation != null) {
			// return true;
			return allowedViews.contains(annotation.name());
		} else {
			return false;
		}
	}
}
