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

import java.text.DecimalFormat;

import javax.annotation.PostConstruct;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.data.Item;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
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
@SpringComponent(value = AboutView.VIEW_NAME)
@SpringView(name = AboutView.VIEW_NAME)

//
@ViewConfiguration(configurationFile = "ConfigurationRootView.info")
@MenuConfiguration(position = Position.TOP_RIGHT, label = "About", group = "", order = 99, parent = RootView.VIEW_NAME)
public class AboutView extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "butterfly-effect-frontend-system:about";

	@PostConstruct
	private void init() {

	}

	@Override
	public void doBuild() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception {

		Table table = new Table("Frontend Information");

		table.addContainerProperty("Property", String.class, null);
		table.addContainerProperty("Value", String.class, null);
		table.addContainerProperty("Description", String.class, null);

		table.setColumnAlignment("Value", Align.RIGHT);

		// Add a row the hard way
		// Server Ip Address
		addItem("Server Ip Address", "T", "description", table);
		// Server Port Number
		addItem("Port Number", "T", "description", table);
		// Server Id

		// Server Memory
		addItem("Free Memory", Runtime.getRuntime().freeMemory(), "description", table);
		addItem("Max Memory", Runtime.getRuntime().maxMemory(), "description", table);
		addItem("Total Memory", Runtime.getRuntime().totalMemory(), "description", table);
		// Server
		addItem("Availabe Processors", Runtime.getRuntime().availableProcessors(), "description", table);

		workAreaPanel.addComponent(table);

	}

	@SuppressWarnings("unchecked")
	private void addItem(String property, String value, String description, AbstractSelect table) {
		Object newItemId = table.addItem();
		Item row1 = table.getItem(newItemId);
		row1.getItemProperty("Property").setValue(property);
		row1.getItemProperty("Value").setValue(value);
		row1.getItemProperty("Description").setValue(description);
	}

	private void addItem(String property, long value, String description, AbstractSelect table) {
		DecimalFormat format = new DecimalFormat("###,###.###");
		addItem(property, format.format(value), description, table);
	}

	@Override
	public void doEnter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
