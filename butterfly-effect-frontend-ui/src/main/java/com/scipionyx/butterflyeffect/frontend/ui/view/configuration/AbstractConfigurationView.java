package com.scipionyx.butterflyeffect.frontend.ui.view.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addons.stackpanel.StackPanel;

import com.scipionyx.butterflyeffect.configuration.model.leftmenu.LeftConfigurationMenuItem;
import com.scipionyx.butterflyeffect.frontend.configuration.services.LeftConfigurationMenuService;
import com.scipionyx.butterflyeffect.frontend.services.UserMenuService;
import com.scipionyx.butterflyeffect.frontend.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.model.ButterflyView;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
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

		leftMenuPanel.setMargin(true);

		List<LeftConfigurationMenuItem> configurations = leftConfigurationMenuService.getConfigurations();

		Map<String, SectionPanel> map = new HashMap<>();

		for (LeftConfigurationMenuItem item : configurations) {

			if (item.getParent() == null) {

				Panel panel = new SectionPanel(item.getLabel());
				panel.setIcon(FontAwesome.ADJUST);

				StackPanel stackPanel = StackPanel.extend(panel);
				stackPanel.setToggleDownIcon(FontAwesome.CARET_SQUARE_O_DOWN);
				stackPanel.setToggleUpIcon(FontAwesome.CARET_SQUARE_O_UP);
				stackPanel.setToggleIconsEnabled(true);

				map.put(item.getId(), (SectionPanel) panel);

			} else if (map.containsKey(item.getParent())) {

				SectionPanel sectionPanel = map.get(item.getParent());

				sectionPanel.addItem(item);

			} else {
				LOGGER.error("Wrong configuration, child left menu not found {}", item.getParent());
			}

		}

		Panel[] panels = new Panel[map.entrySet().size()];
		map.values().toArray(panels);

		leftMenuPanel.addComponent(new VerticalLayout(panels));

	}

	/**
	 * 
	 * @author Renato Mendes - rmendes@bottomline.com /
	 *         renato.mendes.1123@gmail.com
	 *
	 */
	public static class SectionPanel extends Panel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Map<LeftConfigurationMenuItem, Button> buttons;

		/**
		 * 
		 * @param caption
		 */
		SectionPanel(String caption) {
			buttons = new HashMap<>();
			setCaption(caption);
			setContent(new VerticalLayout() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				{
					setSizeFull();
					setMargin(true);
					setSpacing(true);
				}
			});
		}

		void changeToShort() {

		}

		void changeToLong() {

		}

		void changeToMinimal() {

		}

		/**
		 * 
		 * @param item
		 */
		void addItem(LeftConfigurationMenuItem item) {

			Button button = new Button(item.getLabel());

			buttons.put(item, button);
			
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
					Notification.show("Pressed:" + item.getLabel());
					// NavigationCommand command = new
					// NavigationCommand(userMenuService, item.getView());
					// command.navigate();
				}
			});

			addComponent(button);
		}

		/**
		 * 
		 * @param c
		 */
		private void addComponent(Component c) {
			((VerticalLayout) getContent()).addComponent(c);
		}
	}

}
