package com.scipionyx.butterflyeffect.frontend.core.ui.view.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.scipionyx.butterflyeffect.ui.model.ButterflyView;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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
public abstract class AbstractView extends VerticalLayout implements View, ButterflyView, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//
	private VerticalLayout workArea;

	private HorizontalLayout buttonPanel;

	private boolean built;

	public abstract void doBuildWorkArea(VerticalLayout workArea) throws Exception;

	public abstract void doEnter(ViewChangeEvent event);

	protected String username;

	/**
	 * 
	 */
	@Override
	public final void enter(ViewChangeEvent event) {
		doEnter(event);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@PostConstruct
	private void init() throws Exception {

		if (SecurityContextHolder.getContext().getAuthentication() != null)
			username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

		// After deserialization...
		if (built)
			return;

		built = true;

		this.setMargin(false);
		this.setSizeFull();
		this.setSpacing(false);

		//
		ViewConfiguration viewConfigurationInformation = read();

		//
		buildTitleLayout(viewConfigurationInformation);

		//

		workArea = new VerticalLayout();
		workArea.setMargin(new MarginInfo(false, true, false, true));
		workArea.setSpacing(true);

		Panel panel = new Panel(workArea);
		panel.setStyleName(ValoTheme.PANEL_BORDERLESS);
		panel.setSizeFull();

		this.addComponent(panel);

		this.setExpandRatio(panel, 1);
		doBuildWorkArea(workArea);

	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private ViewConfiguration read() throws IOException {

		// Get the name of the configuration File.
		if (this.getClass().isAnnotationPresent(ViewConfiguration.class)) {
			return this.getClass().getAnnotation(ViewConfiguration.class);
		}

		return null;

	}

	/**
	 * Read a resource from the class path or from within a jar.<br>
	 * TODO - it seems that it is not working when distributing the software as
	 * a jar, most likely it can not find the files within jars.
	 * 
	 * @param name
	 * @param classLoader
	 * @return
	 * @throws IOException
	 */
	protected List<InputStream> loadResources(final String name, final ClassLoader classLoader) throws IOException {
		final List<InputStream> list = new ArrayList<InputStream>();
		final Enumeration<URL> systemResources = (classLoader == null ? ClassLoader.getSystemClassLoader()
				: classLoader).getResources(name);
		while (systemResources.hasMoreElements()) {
			list.add(systemResources.nextElement().openStream());
		}
		return list;
	}

	/**
	 * @param viewConfigurationInformation
	 * 
	 */
	private void buildTitleLayout(ViewConfiguration viewConfigurationInformation) {

		//
		Label titleLabel = new Label(viewConfigurationInformation.title());
		titleLabel.addStyleName(ValoTheme.LABEL_H2);

		buttonPanel = new HorizontalLayout();
		buttonPanel.setSpacing(true);
		buttonPanel.setMargin(false);

		//
		HorizontalLayout titleLayout = new HorizontalLayout(titleLabel, buttonPanel);
		titleLayout.setWidth(100, Unit.PERCENTAGE);
		titleLayout.setMargin(new MarginInfo(false, true, false, true));
		titleLayout.setSpacing(true);
		titleLayout.setComponentAlignment(buttonPanel, Alignment.MIDDLE_RIGHT);

		this.addComponent(titleLayout);

	}

	/**
	 * 
	 * @param buttonFriendly
	 * @param component
	 */
	protected Component addButton(String theme, Component component) {
		component.setStyleName(theme);
		buttonPanel.addComponent(component);
		return component;
	}

}
