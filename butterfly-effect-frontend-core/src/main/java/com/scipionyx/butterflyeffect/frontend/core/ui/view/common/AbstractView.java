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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scipionyx.butterflyeffect.frontend.model.ViewConfigurationInformation;
import com.scipionyx.butterflyeffect.ui.model.ButterflyView;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
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
public abstract class AbstractView extends VerticalLayout implements View, BeanNameAware, ButterflyView {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractView.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//
	private VerticalLayout workArea;

	private String beanName;

	private ObjectMapper objectMapper;

	public abstract void doBuildWorkArea(VerticalLayout workArea) throws Exception;

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

		this.setMargin(false);
		this.setSizeFull();
		this.setSpacing(true);

		//
		ViewConfigurationInformation viewConfigurationInformation = read();

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

	/**
	 * @param viewConfigurationInformation
	 * 
	 */
	private void buildTitleLayout(ViewConfigurationInformation viewConfigurationInformation) {

		VerticalLayout titleLayout = new VerticalLayout();
		// titleLayout.setStyleName(ValoTheme.PANEL_WELL, true);
		// titleLayout.setStyleName(ValoTheme.PANEL_BORDERLESS, true);
		titleLayout.setMargin(new MarginInfo(false, true, false, true));
		titleLayout.setSpacing(false);

		//
		Label titleLabel = new Label(viewConfigurationInformation.getTitle().getTitle());
		titleLabel.addStyleName(ValoTheme.LABEL_H2);
		// titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		titleLayout.addComponent(titleLabel);

		//
		// Label subTitleLabel = new
		// Label(viewConfigurationInformation.getTitle().getSubTitle());
		// subTitleLabel.addStyleName(ValoTheme.LABEL_TINY);
		// titleLayout.addComponent(subTitleLabel);

		this.addComponent(titleLayout);

	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

}
