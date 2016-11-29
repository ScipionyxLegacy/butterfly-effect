package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.workarea;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * @author Renato Mendes
 *
 */
public class WorkAreaPanel extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	private VerticalLayout workPanel;

	private Label titleLabel;

	private Label subTitleLabel;

	private HorizontalLayout buttomPanel;

	/**
	 * @throws IOException
	 * 
	 */
	public final void build() throws IOException {

		//
		this.setSizeFull();

		// Main layout for the work area
		GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setSizeFull();
		gridLayout.setSpacing(false);
		gridLayout.setMargin(new MarginInfo(false, true, false, true));
		gridLayout.setColumnExpandRatio(0, 1);
		gridLayout.setColumnExpandRatio(1, 1);
		gridLayout.setRowExpandRatio(0, 1);
		gridLayout.setRowExpandRatio(1, 100);

		// Add Title
		gridLayout.addComponent(buildTitleLayout(), 0, 0);

		// Add Button
		gridLayout.addComponent(buildButtonLayout(), 1, 0);

		// Add WorkArea
		gridLayout.addComponent(buildWorkAreaLayout(), 0, 1, 1, 1);

		//
		this.addComponent(gridLayout);

	}

	/**
	 * 
	 * @return
	 */
	private Component buildWorkAreaLayout() {
		workPanel = new VerticalLayout();
		workPanel.setMargin(true);
		workPanel.setSizeFull();
		return workPanel;
	}

	/**
	 * 
	 * @return
	 */
	private Component buildButtonLayout() {
		buttomPanel = new HorizontalLayout();
		buttomPanel.setSizeFull();
		return buttomPanel;
	}

	/**
	 * 
	 */
	private AbstractLayout buildTitleLayout() {

		VerticalLayout titleLayout = new VerticalLayout();
		titleLayout.setMargin(new MarginInfo(true, false, true, false));
		titleLayout.setSpacing(false);

		//
		titleLabel = new Label();
		titleLabel.addStyleName(ValoTheme.LABEL_H1);
		titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		titleLayout.addComponent(titleLabel);

		//
		subTitleLabel = new Label();
		subTitleLabel.addStyleName(ValoTheme.LABEL_TINY);
		titleLayout.addComponent(subTitleLabel);

		return titleLayout;

	}

	public VerticalLayout getWorkPanel() {
		return workPanel;
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		if (!StringUtils.isEmpty(title)) {
			titleLabel.setValue(title);
		} else {
		}
	}

	/**
	 * 
	 * @param subTitle
	 */
	public void setSubTitle(String subTitle) {
		if (!StringUtils.isEmpty(subTitle)) {
			subTitleLabel.setValue(subTitle);
		} else {
		}

	}

	public HorizontalLayout getBottomPanel() {
		return buttomPanel;
	}

}
