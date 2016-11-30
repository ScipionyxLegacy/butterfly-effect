package com.scipionyx.butterflyeffect.frontend.configuration.ui.view;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scipionyx.butterflyeffect.configuration.model.leftmenu.LeftConfigurationMenuItem;
import com.scipionyx.butterflyeffect.frontend.core.services.MenuService;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.model.ButterflyView;
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

	@SuppressWarnings("unused")
	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractConfigurationView.class);

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
		SectionPanel(String caption, MenuService userMenuService) {
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
