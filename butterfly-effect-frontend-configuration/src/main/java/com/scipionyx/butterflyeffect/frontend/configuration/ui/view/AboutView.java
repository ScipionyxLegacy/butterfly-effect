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

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * 
 * 
 */
@UIScope()
@SpringComponent(value = AboutView.VIEW_NAME)
@SpringView(name = AboutView.VIEW_NAME)

//
@ViewConfiguration(title = "About")
@MenuConfiguration(position = Position.TOP_RIGHT, label = "About", group = "", order = 99, parent = RootView.VIEW_NAME, roles = {
		"USER", "ADMIN" })
//
@Configurable(value = AboutView.VIEW_NAME)
public class AboutView extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "butterfly-effect-frontend-system:about";

	private Grid<GridProperty<?>> tableFrontEndInformation;

	private GridLayout backendLayout;

	private GridLayout frontendLayout;

	@Autowired
	private transient DiscoveryClient discoveryClient;

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {

		try {
			loadFrontEndInformation(true);
			loadClusterInformation(discoveryClient.getInstances("butterflyeffect-frontend"), frontendLayout, false);
			loadClusterInformation(discoveryClient.getInstances("butterflyeffect-backend"), backendLayout, false);
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("The requested function was not executed correctly - " + e.getMessage()
					+ "\n the informaiton displayed is incomplete.", Type.TRAY_NOTIFICATION);
		}

	}

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception {

		//
		tableFrontEndInformation = new Grid<>("Frontend Information");
		tableFrontEndInformation.addColumn(GridProperty::getName).setCaption("Property");
		tableFrontEndInformation.addColumn(GridProperty::getValue).setCaption("Value");
		tableFrontEndInformation.addColumn(GridProperty::getDescription).setCaption("Description");

		workAreaPanel.addComponent(tableFrontEndInformation);

		//
		backendLayout = createClusterInformation("Backend", workAreaPanel);
		frontendLayout = createClusterInformation("Frontend", workAreaPanel);

		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Refresh", event -> doEnter(null)));

	}

	/**
	 * 
	 * @param justclean
	 * @throws UnknownHostException
	 */
	@SuppressWarnings("deprecation")
	private void loadFrontEndInformation(boolean loadData) throws UnknownHostException {

		// Remove all before adding
		tableFrontEndInformation.setItems(new ArrayList<>());

		if (!loadData)
			return;

		List<GridProperty<?>> items = new ArrayList<>();

		// Server Ip Address
		items.add(
				new GridProperty<String>("Server Ip 4 Host Address", Inet4Address.getLocalHost().getHostAddress(), ""));
		items.add(
				new GridProperty<String>("Server Ip 6 Host Address", Inet6Address.getLocalHost().getHostAddress(), ""));
		// Server Id

		// Server Memory
		items.add(new GridProperty<Long>("Free Memory", Runtime.getRuntime().freeMemory(), ""));
		items.add(new GridProperty<Long>("Max Memory", Runtime.getRuntime().maxMemory(), ""));
		items.add(new GridProperty<Long>("Total Memory", Runtime.getRuntime().totalMemory(), ""));
		// Server
		items.add(new GridProperty<String>("Availabe Processors", Runtime.getRuntime().availableProcessors() + "", ""));

		// Session
		items.add(new GridProperty<String>("Session Id", VaadinSession.getCurrent().getSession().getId(), ""));
		items.add(new GridProperty<String>("Session CreationTime",
				(new Date(VaadinSession.getCurrent().getSession().getCreationTime()).toGMTString()), ""));
		items.add(new GridProperty<String>("Session Last Access Time",
				(new Date(VaadinSession.getCurrent().getSession().getLastAccessedTime()).toGMTString()), ""));

		items.add(
				new GridProperty<String>("User", SecurityContextHolder.getContext().getAuthentication().getName(), ""));
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		String roles = null;
		for (GrantedAuthority grantedAuthority : authorities) {
			if (roles == null)
				roles = grantedAuthority.getAuthority();
			else
				roles = roles + "," + grantedAuthority.getAuthority();
		}
		items.add(new GridProperty<String>("Roles", roles, ""));
		items.add(new GridProperty<String>("Client Ip", Page.getCurrent().getWebBrowser().getAddress(), ""));

		tableFrontEndInformation.setItems(items);

	}

	/**
	 * 
	 * @param workAreaPanel
	 */
	private GridLayout createClusterInformation(String type, VerticalLayout workAreaPanel) {

		GridLayout layout = new GridLayout(4, 2);
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);

		Label label = new Label("Cluster Information - " + type);
		label.setStyleName(ValoTheme.LABEL_H2);

		Panel panelClusterInformation = new Panel(layout);
		panelClusterInformation.setStyleName(ValoTheme.PANEL_WELL);

		workAreaPanel.addComponents(label, panelClusterInformation);

		return layout;

	}

	/**
	 * 
	 * @param instancesBackend
	 * @param justclean
	 */
	private void loadClusterInformation(List<ServiceInstance> instancesBackend, GridLayout layout, boolean justclean) {

		layout.removeAllComponents();

		int i = 0;
		for (ServiceInstance instance : instancesBackend) {

			Grid<GridProperty<?>> tableCluster = new Grid<>("Node [" + i + "]");
			tableCluster.addStyleName(ValoTheme.TABLE_COMPACT);
			tableCluster.setSizeFull();

			tableCluster.addColumn(GridProperty::getName).setCaption("Property");
			tableCluster.addColumn(GridProperty::getValue).setCaption("Value");

			List<GridProperty<?>> list = new ArrayList<>();
			list.add(new GridProperty<>("Host", instance.getHost()));
			list.add(new GridProperty<>("Service Id", instance.getServiceId()));
			list.add(new GridProperty<>("Port", instance.getPort()));
			list.add(new GridProperty<>("Uri", instance.getUri()));

			for (String key : instance.getMetadata().keySet()) {
				list.add(new GridProperty<>("Metadata[" + key + "]", instance.getMetadata().get(key)));
			}
			
			tableCluster.setItems(list);

			layout.addComponent(tableCluster);

			i++;

		}

	}

	/**
	 * 
	 * @author Renato Mendes
	 *
	 */
	public class GridProperty<TYPE extends Object> implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String name;
		private TYPE value;
		private String description;

		public GridProperty() {
			super();
		}

		public GridProperty(String name, TYPE value) {
			super();
			this.name = name;
			this.value = value;
			this.description = "";
		}

		public GridProperty(String name, TYPE value, String description) {
			super();
			this.name = name;
			this.value = value;
			this.description = description;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public TYPE getValue() {
			return value;
		}

		public void setValue(TYPE value) {
			this.value = value;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

}
