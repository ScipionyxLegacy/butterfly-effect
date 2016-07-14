package com.scipionyx.butterflyeffect.frontend.ui.view.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.scipionyx.butterflyeffect.configuration.model.leftmenu.LeftConfigurationMenuItem;
import com.scipionyx.butterflyeffect.frontend.configuration.services.LeftConfigurationMenuService;
import com.scipionyx.butterflyeffect.frontend.services.UserMenuService;
import com.scipionyx.butterflyeffect.frontend.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.frontend.ui.view.common.NavigationCommand;
import com.scipionyx.butterflyeffect.ui.model.ButterflyView;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
public abstract class AbstractConfigurationView extends AbstractView implements ButterflyView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private LeftConfigurationMenuService leftConfigurationMenuService;

	@Autowired
	private UserMenuService userMenuService;

	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractConfigurationView.class);

	/**
	 * 
	 */
	@Override
	public final void doBuildLeftMenu(VerticalLayout leftMenuPanel) {

		List<LeftConfigurationMenuItem> configurations = leftConfigurationMenuService.getConfigurations();

		Accordion accordion = new Accordion();

		Map<String, VerticalLayout> map = new HashMap<>();

		for (LeftConfigurationMenuItem item : configurations) {

			if (item.getParent() == null) {

				VerticalLayout layout = new VerticalLayout();

				accordion.addTab(layout, item.getLabel());
				map.put(item.getId(), layout);

			} else if (map.containsKey(item.getParent())) {

				VerticalLayout verticalLayout = map.get(item.getParent());

				Button button = new Button(item.getLabel());
				button.setStyleName(ValoTheme.BUTTON_LINK);
				button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
				button.addStyleName(ValoTheme.BUTTON_SMALL);
				button.addClickListener(new Button.ClickListener() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						LOGGER.debug("Navigating to {}", item.getView());
						NavigationCommand command = new NavigationCommand(userMenuService, item.getView());
						command.navigate();
					}
				});

				verticalLayout.addComponent(button);

			} else {
				LOGGER.error("Wrong configuration, child left menu not found {}", item.getParent());
			}

		}

		leftMenuPanel.addComponent(accordion);

	}

}
