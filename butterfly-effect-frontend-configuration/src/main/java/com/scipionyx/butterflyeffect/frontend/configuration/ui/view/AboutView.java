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

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
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
import com.vaadin.data.Item;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
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

	private Table tableFrontEndInformation;

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
		tableFrontEndInformation = new Table("Frontend Information");
		tableFrontEndInformation.addContainerProperty("Property", String.class, null);
		tableFrontEndInformation.addContainerProperty("Value", String.class, null);
		tableFrontEndInformation.addContainerProperty("Description", String.class, null);
		tableFrontEndInformation.setColumnAlignment("Value", Align.RIGHT);
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

		tableFrontEndInformation.removeAllItems();

		if (!loadData)
			return;

		// Server Ip Address
		addItem("Server Ip 4 Host Address", Inet4Address.getLocalHost().getHostAddress(), tableFrontEndInformation);
		addItem("Server Ip 6 Host Address", Inet6Address.getLocalHost().getHostAddress(), tableFrontEndInformation);
		// Server Id

		// Server Memory
		addItem("Free Memory", Runtime.getRuntime().freeMemory(), "", tableFrontEndInformation);
		addItem("Max Memory", Runtime.getRuntime().maxMemory(), "", tableFrontEndInformation);
		addItem("Total Memory", Runtime.getRuntime().totalMemory(), "", tableFrontEndInformation);
		// Server
		addItem("Availabe Processors", Runtime.getRuntime().availableProcessors() + "", tableFrontEndInformation);

		// Session
		addItem("Session Id", VaadinSession.getCurrent().getSession().getId(), tableFrontEndInformation);
		addItem("Session CreationTime",
				(new Date(VaadinSession.getCurrent().getSession().getCreationTime()).toGMTString()),
				tableFrontEndInformation);
		addItem("Session Last Access Time",
				(new Date(VaadinSession.getCurrent().getSession().getLastAccessedTime()).toGMTString()),
				tableFrontEndInformation);

		addItem("User", SecurityContextHolder.getContext().getAuthentication().getName(), tableFrontEndInformation);
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		String roles = null;
		for (GrantedAuthority grantedAuthority : authorities) {
			if (roles == null)
				roles = grantedAuthority.getAuthority();
			else
				roles = roles + "," + grantedAuthority.getAuthority();
		}
		addItem("Roles", roles, tableFrontEndInformation);
		addItem("Client Ip", Page.getCurrent().getWebBrowser().getAddress(), tableFrontEndInformation);

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

		if (justclean)
			return;

		int i = 0;
		for (ServiceInstance instance : instancesBackend) {

			Table tableCluster = new Table("Node [" + i + "]");
			tableCluster.addStyleName(ValoTheme.TABLE_COMPACT);
			tableCluster.setSizeFull();

			tableCluster.addContainerProperty("Property", String.class, null);
			tableCluster.addContainerProperty("Value", String.class, null);

			addItem("Host", instance.getHost(), tableCluster);
			addItem("Service Id", instance.getServiceId(), tableCluster);
			addItem("Port", instance.getPort() + "", tableCluster);
			addItem("Uri", instance.getUri().toString(), tableCluster);

			for (String key : instance.getMetadata().keySet()) {
				addItem(key, instance.getMetadata().get(key), tableCluster);
			}

			layout.addComponent(tableCluster);

			i++;

		}

	}

	/**
	 * 
	 * @param property
	 * @param value
	 * @param table
	 */
	@SuppressWarnings("unchecked")
	private void addItem(String property, String value, AbstractSelect table) {
		Object newItemId = table.addItem();
		Item row1 = table.getItem(newItemId);
		row1.getItemProperty("Property").setValue(property);
		row1.getItemProperty("Value").setValue(value);
	}

	/**
	 * 
	 * @param property
	 * @param value
	 * @param description
	 * @param table
	 */
	private void addItem(String property, long value, String description, AbstractSelect table) {
		DecimalFormat format = new DecimalFormat("###,###.###");
		addItem(property, format.format(value), table);
	}

}
