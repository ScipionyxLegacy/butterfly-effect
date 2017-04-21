package com.scipionyx.butterflyeffect.frontend.core.ui.view.common;

import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public abstract class ScipionyxPanel extends CssLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final HorizontalLayout caption = new HorizontalLayout();
	private final HorizontalLayout buttonLayout = new HorizontalLayout();
	private final Label captionText = new Label("");
	private final VerticalLayout content = new VerticalLayout();
	private Image iconContainer;

	public abstract void doBuild() throws Exception;

	/**
	 * @throws Exception 
	 * 
	 */
	public ScipionyxPanel build() throws Exception {

		setSizeFull();
		addStyleName(ValoTheme.LAYOUT_CARD);

		// Caption
		caption.addStyleName("v-panel-caption");
		caption.setWidth("100%");
		caption.setSpacing(true);
		addComponent(caption);

		captionText.setWidth("100%");
		caption.addComponent(this.captionText);
		caption.setExpandRatio(this.captionText, 1);

		// Button Layout
		caption.addComponent(buttonLayout);
		buttonLayout.setSpacing(true);
		buttonLayout.setMargin(false);

		addComponent(content);
		content.setSizeFull();
		content.setMargin(true);

		doBuild();

		return this;
	}

	@Override
	public void setIcon(final Resource icon) {
		if (this.iconContainer != null) {
			this.caption.removeComponent(this.iconContainer);
		}
		this.iconContainer = new Image(null, icon);
		this.caption.addComponent(this.iconContainer, 0);
		this.caption.setComponentAlignment(this.iconContainer, Alignment.MIDDLE_LEFT);
		this.caption.setExpandRatio(this.iconContainer, 0);
	}

	@Override
	public void setCaption(final String caption) {
		this.captionText.setValue(caption);
	}

	/**
	 * 
	 * @param component
	 */
	public void addBodyComponent(Component component) {
		this.content.addComponent(component);
	}

	/**
	 * 
	 * @param component
	 */
	public void clearBodyComponents() {
		this.content.removeAllComponents();
	}

	/**
	 * 
	 * @param icon
	 * @param button
	 */
	public void addButton(VaadinIcons icon, Button button) {
		buttonLayout.addComponentAsFirst(button);
		button.setIcon(icon);
		button.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		button.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
	}

	/**
	 * 
	 * @param icon
	 * @param button
	 */
	public void addMenuBar(MenuBar menuBar) {
		buttonLayout.addComponentAsFirst(menuBar);
		
		menuBar.getItems().forEach(i -> i.setCheckable(true));
		
		menuBar.setStyleName(ValoTheme.MENUBAR_SMALL);
		menuBar.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		
	}

	/**
	 * 
	 * @param icon
	 * @param slider
	 */
	public void addSlider(VaadinIcons icon, Slider slider, ValueChangeListener<Double> changeListener) {
		buttonLayout.addComponentAsFirst(slider);
		if (icon != null) {
			slider.setIcon(icon);
		}
		slider.addValueChangeListener(changeListener);
	}

}
