package com.scipionyx.butterflyeffect.frontend.jobmanagement.ui.view;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Job;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * 
 * 
 * @version 0.1.0
 * @author Renato Mendes
 *
 */
@SpringComponent(value = JobMonitorView.VIEW_NAME)
@SpringView(name = JobMonitorView.VIEW_NAME)
@UIScope
//
@ViewConfiguration(title = "Job Monitor")
@MenuConfiguration(position = Position.TOP_RIGHT, label = "Job Monitor", group = "", order = 1, parent = RootView.VIEW_NAME, icon = VaadinIcons.AUTOMATION, roles = {
		"ADMIN" })
public class JobMonitorView extends AbstractView {

	public static final String VIEW_NAME = "butterfly-effect-frontend-jobmanagement:job-monitor";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AvailableJobTree jobs;

	/**
	 * 
	 */
	@Override
	public void doEnter(ViewChangeEvent event) {
	}

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception {

		addButton(ValoTheme.BUTTON_FRIENDLY, new Button("Refresh"));

		jobs = new AvailableJobTree();

		JobExecutionTree activeJobs = new JobExecutionTree();
		JobExecutionTree completeJobs = new JobExecutionTree();
		JobExecutionTree failedJobs = new JobExecutionTree();

		TabSheet tabSheet = new TabSheet();
		tabSheet.addTab(activeJobs, "Active", VaadinIcons.CAR);
		tabSheet.addTab(completeJobs, "Completed", VaadinIcons.BOOK);
		tabSheet.addTab(failedJobs, "Failed", VaadinIcons.AMBULANCE);

		HorizontalLayout horizontalLayout = new HorizontalLayout(jobs, tabSheet);
		horizontalLayout.setSizeFull();
		horizontalLayout.setExpandRatio(jobs, 1);
		horizontalLayout.setExpandRatio(tabSheet, 3);

		workAreaPanel.addComponents(horizontalLayout);

	}

	private class AvailableJobTree extends Panel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AvailableJobTree() {
			setSizeFull();
			setCaption("Available");
		}

	}

	/**
	 * 
	 * @author rmendes
	 *
	 */
	private class JobExecutionTree extends Grid<Job> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		JobExecutionTree() {
			setSizeFull();
		}

	}

}
