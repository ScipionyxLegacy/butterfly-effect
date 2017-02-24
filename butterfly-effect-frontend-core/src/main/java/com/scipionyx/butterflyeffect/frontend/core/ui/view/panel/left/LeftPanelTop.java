package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.left;

import com.scipionyx.butterflyeffect.frontend.model.Title;
import com.scipionyx.butterflyeffect.frontend.model.ViewConfigurationInformation;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
class LeftPanelTop extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Label image;

	private Label title;

	private Label subTitle;

	private ViewConfigurationInformation information;

	private Mode current;

	private HorizontalLayout wrapper;

	/**
	 * @param information
	 * 
	 */
	void build(ViewConfigurationInformation information) {

		this.information = information;

		// this.setMargin(new MarginInfo(true, false, false, true));

		// Title Icon
		wrapper = new HorizontalLayout();
		wrapper.setSpacing(true);
		// this.addComponent(wrapper);

		//
		image = new Label();
		// image.setContentMode(ContentMode.HTML);
		if (information.getLeftTitle() != null && information.getLeftTitle().getIcon() != null) {
			image.setValue(((VaadinIcons) information.getLeftTitle().getIcon()).getHtml());
		} else {
			image.setValue(VaadinIcons.QUESTION_CIRCLE.getHtml());
		}
		wrapper.addComponent(image);
		wrapper.setComponentAlignment(image, Alignment.TOP_CENTER);

		//
		VerticalLayout verticalLayout = new VerticalLayout();
		wrapper.addComponent(verticalLayout);

		// Title
		title = new Label();
		title.setStyleName(ValoTheme.LABEL_H3);
		title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		verticalLayout.addComponent(title);

		// Subtitle
		subTitle = new Label();
		subTitle.setStyleName(ValoTheme.LABEL_H4);
		subTitle.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		verticalLayout.addComponent(subTitle);

	}

	/**
	 * 
	 */
	void setExpanded() {

		Title titleInformation = information.getLeftTitle();

		// this.setAComponentAlignment(wrapper, Alignment.TOP_LEFT);

		if (titleInformation != null) {

			if (titleInformation.getIcon() != null) {
				image.setStyleName(ValoTheme.LABEL_H1);
				image.addStyleName(ValoTheme.LABEL_NO_MARGIN);
				// image.setWidth(100, Unit.PIXELS);
				image.setDescription("");
			}

			//
			if (titleInformation.getTitle() != null) {
				title.setValue(titleInformation.getTitle());
				// title.setWidth(190, Unit.PIXELS);
			}

			//
			if (titleInformation.getSubTitle() != null) {
				subTitle.setValue(titleInformation.getSubTitle());
				// subTitle.setWidth(190, Unit.PIXELS);
			}

		}

		current = Mode.EXPANDED;
	}

	/**
	 * 
	 */
	void setCollapsed() {

		Title titleInformation = information.getLeftTitle();

		// this.setComponentAlignment(wrapper, Alignment.TOP_CENTER);

		if (titleInformation != null) {

			if (titleInformation.getIcon() != null) {
				image.setStyleName(ValoTheme.LABEL_H2);
			}

			//
			if (titleInformation.getTitle() != null) {
				// title.setWidth(0, Unit.PIXELS);
			}

			//
			if (titleInformation.getSubTitle() != null) {
				image.setDescription(titleInformation.getSubTitle());
				// subTitle.setWidth(0, Unit.PIXELS);
			}

		}

		title.setValue("");
		subTitle.setValue("");

		current = Mode.COLAPSED;

	}

	/**
	 * 
	 * Toggle
	 * 
	 */
	void toggle() {
		switch (current) {
		case COLAPSED:
			setExpanded();
			break;

		default:
			setCollapsed();
			break;
		}
	}

}
