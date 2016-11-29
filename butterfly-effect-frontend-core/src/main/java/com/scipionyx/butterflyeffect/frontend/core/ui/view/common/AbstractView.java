package com.scipionyx.butterflyeffect.frontend.core.ui.view.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scipionyx.butterflyeffect.frontend.configuration.ui.view.ViewConfigurationInformation;
import com.scipionyx.butterflyeffect.frontend.core.services.UserMenuService;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.left.LeftPanel;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.workarea.WorkAreaPanel;
import com.scipionyx.butterflyeffect.ui.model.ButterflyView;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractSplitPanel.SplitPositionChangeEvent;
import com.vaadin.ui.AbstractSplitPanel.SplitPositionChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public abstract class AbstractView extends VerticalLayout implements View, BeanNameAware, ButterflyView {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractView.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Services
	@Autowired
	protected UserMenuService userMenuService;

	//
	protected WorkAreaPanel workAreaPanel;

	//
	protected boolean built;

	private String beanName;

	private ObjectMapper objectMapper;

	public void doBuildMenu() {
	}

	public abstract void doBuildLeftMenu(VerticalLayout leftMenuPanel);

	public abstract void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception;

	public abstract void doBuildBottomArea(HorizontalLayout buttomAreaPanel);

	public abstract void doBuild();

	public abstract void doEnter(ViewChangeEvent event);

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

		this.setMargin(true);
		this.setSizeFull();
		this.setSpacing(true);

		// Top Menu
		doBuildMenu();

		// Main work Area
		HorizontalSplitPanel mainWorkArea = new HorizontalSplitPanel();
		mainWorkArea.setSizeFull();
		mainWorkArea.setMaxSplitPosition(500, Unit.PIXELS);
		mainWorkArea.setMinSplitPosition(50, Unit.PIXELS);
		mainWorkArea.setSplitPosition(250, Unit.PIXELS);
		this.addComponent(mainWorkArea);
		this.setComponentAlignment(mainWorkArea, Alignment.TOP_CENTER);
		this.setExpandRatio(mainWorkArea, 2);

		ViewConfigurationInformation viewConfigurationInformation = read();

		// Left Menu
		LeftPanel left = new LeftPanel();
		left.build(viewConfigurationInformation);
		doBuildLeftMenu(left.getInternal());
		mainWorkArea.addComponent(left);

		// Work Area
		workAreaPanel = new WorkAreaPanel();
		workAreaPanel.build();
		mainWorkArea.addComponent(workAreaPanel);

		mainWorkArea.addSplitPositionChangeListener(new SplitPositionChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSplitPositionChanged(SplitPositionChangeEvent event) {
				if (event.getSplitPosition() == mainWorkArea.getMinSplitPosition()) {
					Notification.show("Min");
				} else if (event.getSplitPosition() == mainWorkArea.getMaxSplitPosition()) {
					Notification.show("Max");
				}
			}
		});

		// Read Configuration

		if (viewConfigurationInformation != null) {

			doBuildWorkArea(workAreaPanel.getWorkPanel());

			workAreaPanel.getWorkPanel().setCaption("WorkPanel");

			if (viewConfigurationInformation.getTitle() != null) {
				workAreaPanel.setTitle(viewConfigurationInformation.getTitle().getTitle());
				workAreaPanel.setSubTitle(viewConfigurationInformation.getTitle().getSubTitle());
			}

			// workAreaPanel.getBottomPanel().setCaption("Bottom Panel"); ;
			doBuildBottomArea(workAreaPanel.getBottomPanel());
		}

		doBuild();

	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private ViewConfigurationInformation read() throws IOException {

		// Get the name of the configuration File.
		if (this.getClass().isAnnotationPresent(ViewConfiguration.class)) {

			ViewConfiguration viewConfiguration = this.getClass().getAnnotation(ViewConfiguration.class);
			String configurationFile = viewConfiguration.configurationFile();

			if (configurationFile != null) {

				List<InputStream> configurations = loadResources(configurationFile, null);

				if (configurations.size() == 1) {
					objectMapper = new ObjectMapper();
					ViewConfigurationInformation information = objectMapper.readValue(configurations.get(0),
							ViewConfigurationInformation.class);
					return information;
				} else {
					LOGGER.error("None or more than one ({}) configuraiton files named as ({}) found in the classpath ",
							configurations.size(), configurationFile);
				}

			}

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

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

}
