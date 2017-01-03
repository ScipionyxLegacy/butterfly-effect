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
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
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
@UIScope
@SpringComponent(value = AboutView.VIEW_NAME)
@SpringView(name = AboutView.VIEW_NAME)

//
@ViewConfiguration(title = "About")
@MenuConfiguration(position = Position.TOP_RIGHT, label = "About", group = "", order = 99, parent = RootView.VIEW_NAME, roles = {
		"USER", "ADMIN" })
public class AboutView extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "butterfly-effect-frontend-system:about";

	@Autowired
	private DiscoveryClient discoveryClient;

	@PostConstruct
	private void init() {

	}

	@SuppressWarnings("deprecation")
	@Override
	public void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception {

		Table table = new Table("Frontend Information");

		table.addContainerProperty("Property", String.class, null);
		table.addContainerProperty("Value", String.class, null);
		table.addContainerProperty("Description", String.class, null);

		table.setColumnAlignment("Value", Align.RIGHT);

		// Add a row the hard way
		// Server Ip Address
		addItem("Server Ip 4 Host Address", Inet4Address.getLocalHost().getHostAddress(), table);
		addItem("Server Ip 6 Host Address", Inet6Address.getLocalHost().getHostAddress(), table);
		// Server Id

		// Server Memory
		addItem("Free Memory", Runtime.getRuntime().freeMemory(), "", table);
		addItem("Max Memory", Runtime.getRuntime().maxMemory(), "", table);
		addItem("Total Memory", Runtime.getRuntime().totalMemory(), "", table);
		// Server
		addItem("Availabe Processors", Runtime.getRuntime().availableProcessors() + "", table);

		// Session
		addItem("Session Id", VaadinSession.getCurrent().getSession().getId(), table);
		addItem("Session CreationTime",
				(new Date(VaadinSession.getCurrent().getSession().getCreationTime()).toGMTString()), table);
		addItem("Session Last Access Time",
				(new Date(VaadinSession.getCurrent().getSession().getLastAccessedTime()).toGMTString()), table);

		// All Session
		///addItem("Session Id", VaadinSession.getAllSessions().getSession().getId(), table);
		
		//
		try {
			addItem("User", SecurityContextHolder.getContext().getAuthentication().getName(), table);
			Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
					.getAuthorities();
			String roles = null;
			for (GrantedAuthority grantedAuthority : authorities) {
				if (roles == null)
					roles = grantedAuthority.getAuthority();
				else
					roles = roles + "," + grantedAuthority.getAuthority();
			}
			addItem("Roles", roles, table);
			addItem("Client Ip", Page.getCurrent().getWebBrowser().getAddress(), table);
		} catch (Exception e) {

		}

		workAreaPanel.addComponent(table);

		createClusterInformation("Backend", workAreaPanel, discoveryClient.getInstances("butterflyeffect-backend"));

		createClusterInformation("Frontend", workAreaPanel, discoveryClient.getInstances("butterflyeffect-frontend"));

	}

	/**
	 * 
	 * @param workAreaPanel
	 */
	private void createClusterInformation(String type, VerticalLayout workAreaPanel,
			List<ServiceInstance> instancesBackend) {

		GridLayout layout = new GridLayout(4, 2);
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);

		Label label = new Label("Cluster Information - " + type);
		label.setStyleName(ValoTheme.LABEL_H2);

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

		Panel panelClusterInformation = new Panel(layout);
		panelClusterInformation.setStyleName(ValoTheme.PANEL_WELL);

		workAreaPanel.addComponents(label, panelClusterInformation);

	}

	@SuppressWarnings("unchecked")
	private void addItem(String property, String value, AbstractSelect table) {
		Object newItemId = table.addItem();
		Item row1 = table.getItem(newItemId);
		row1.getItemProperty("Property").setValue(property);
		row1.getItemProperty("Value").setValue(value);
	}

	private void addItem(String property, long value, String description, AbstractSelect table) {
		DecimalFormat format = new DecimalFormat("###,###.###");
		addItem(property, format.format(value), table);
	}

	@Override
	public void doEnter(ViewChangeEvent event) {
		//
	}

}
