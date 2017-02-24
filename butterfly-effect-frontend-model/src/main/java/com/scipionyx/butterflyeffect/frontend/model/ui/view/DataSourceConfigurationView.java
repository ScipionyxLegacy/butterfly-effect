package com.scipionyx.butterflyeffect.frontend.model.ui.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.scipionyx.butterflyeffect.frontend.model.services.EntityClientRESTConfigurationService;
import com.scipionyx.butterflyeffect.model.model.datamodel.Entity;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * 
 * 
 * @version 0.1.0
 * @author Renato Mendes
 *
 */
@SpringComponent(value = DataSourceConfigurationView.VIEW_NAME)
@SpringView(name = DataSourceConfigurationView.VIEW_NAME)
@UIScope
//
@ViewConfiguration(title = "Data Source Configuration")
@MenuConfiguration(group = "DataModelConfiguration", label = "Data Source Configuration", position = Position.TOP_RIGHT, parent = RootView.VIEW_NAME)
//
@SuppressWarnings("unused")
public class DataSourceConfigurationView extends AbstractDataModelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "butterfly-effect-frontend-model:DataSourceConfigurationView";

	private boolean editing;

	// private VerticalLayout workAreaPanel;

	private TabSheet sheet;

	@Autowired
	private transient EntityClientRESTConfigurationService service;

	/**
	 * @throws Exception
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception {
		// this.workAreaPanel = workAreaPanel;

		sheet = new TabSheet();

		// Entities
		VerticalLayout entitiesPanel = new VerticalLayout();
		doBuildTab(entitiesPanel);

		// Events
		VerticalLayout eventsPanel = new VerticalLayout();
		doBuildTab(eventsPanel);

		// Facts
		VerticalLayout factsPanel = new VerticalLayout();
		doBuildTab(factsPanel);

		// Incidents
		VerticalLayout incidentsPanel = new VerticalLayout();
		doBuildTab(incidentsPanel);

		// Reference Tables
		VerticalLayout referenceTablesPanel = new VerticalLayout();
		doBuildTab(referenceTablesPanel);

		List<Entity> dataModel = readDataModel();
		// sheet.addTab(entitiesPanel, "Entities");
		loadEntities(dataModel);
		// sheet.addTab(eventsPanel, "Events");
		loadEvents(dataModel);
		// sheet.addTab(factsPanel, "Facts");
		loadFacts(dataModel);
		// sheet.addTab(incidentsPanel, "Incidents");
		loadIncidetns(dataModel);
		// sheet.addTab(referenceTablesPanel, "Reference Tables");

		// workAreaPanel.addComponent(sheet);

	}

	private void loadIncidetns(List<Entity> dataModel) {
		// TODO Auto-generated method stub

	}

	private void loadFacts(List<Entity> dataModel) {
		// TODO Auto-generated method stub

	}

	private void loadEvents(List<Entity> dataModel) {
		// TODO Auto-generated method stub

	}

	private void loadEntities(List<Entity> dataModel) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<Entity> readDataModel() throws Exception {
		return null;// service.getAll();
	}

	/**
	 * 
	 * @param layout
	 */
	private Grid<?> doBuildTab(VerticalLayout layout) {
		// layout.setMargin(new MarginInfo(true, false, false, false));
		Grid<?> grid = new Grid<>();
		layout.addComponent(grid);
		return grid;
	}

	/**
	 * 
	 */
	public void doLeftPanelBuild(VerticalLayout leftMenuPanel) {
	}

	/**
	 * 
	 * @return
	 */
	private void createWorkingAreaPanel(Entity entity) {

		final Window editEntityDialog = new Window("Edit Data Model");

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		horizontalLayout.setMargin(true);

		if (entity == null) {
			entity = new Entity();
			entity.setName("");
			// entity.setDescription("");
			entity.setLabel("");
		}

		createEntityForm(entity);

		FormLayout formLayout = new FormLayout();
		formLayout.setCaption("Entity Information");

		Binder<Entity> binder = new Binder<>(Entity.class);
		binder.setBean(entity);

		// Name
		// formLayout.addComponent(binder.buildAndBind("Name", "name"));

		// Description
		// layout.addComponent(binder.buildAndBind("Description",
		// "description"));

		// Description
		// formLayout.addComponent(binder.buildAndBind("Description",
		// "description", TextArea.class));

		// Label
		// formLayout.addComponent(binder.buildAndBind("Label", "label"));

		// Class

		// Type
		// formLayout.addComponent(binder.buildAndBind("Type", "type",
		// ComboBox.class));

		// Buffer the form content

		//
		formLayout.addComponent(new Button("Save", new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.validate();
					Entity entity = binder.getBean();
					Notification.show("Entity Saved", Notification.Type.HUMANIZED_MESSAGE);
					editEntityDialog.close();
				} catch (Exception e) {
					if (binder.isValid()) {
						Notification.show(e.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
					} else {
						Notification.show("Please provide required information", Notification.Type.HUMANIZED_MESSAGE);
					}
				}
			}
		}));

		horizontalLayout.addComponent(formLayout);

		//////

		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		horizontalLayout.addComponent(verticalLayout);

		Button addFieldButton = new Button("Add Field");

		Grid<?> grid = new Grid<>();
		grid.setWidthUndefined();
		grid.setCaption("Fields");
		grid.setColumnReorderingAllowed(true);
		grid.setHeightByRows(5);

		// grid.setEditorEnabled(true);
		grid.setSelectionMode(SelectionMode.NONE);

		// grid.addColumn("Name", String.class).setRenderer(new
		// TextRenderer()).setExpandRatio(2);
		// grid.addColumn("Description", String.class).setRenderer(new
		// TextRenderer()).setExpandRatio(2);
		// grid.addColumn("Type", String.class).setRenderer(new
		// TextRenderer()).setExpandRatio(2);
		// grid.addColumn("Size", Integer.class).setRenderer(new
		// NumberRenderer()).setExpandRatio(2);

		addFieldButton.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// grid.addRow("<field name>", "<field description>", "<field
				// type>", 0);
			}
		});

		verticalLayout.addComponent(grid);
		verticalLayout.addComponent(addFieldButton);

		editEntityDialog.setWindowMode(WindowMode.NORMAL);
		editEntityDialog.setWidth(825, Unit.PIXELS);
		editEntityDialog.setHeight(550, Unit.PIXELS);
		editEntityDialog.setContent(horizontalLayout);
		editEntityDialog.center();

		UI.getCurrent().addWindow(editEntityDialog);

	}

	private void createEntityForm(Entity entity) {

	}

	@Override
	public void doEnter(ViewChangeEvent event) {
	}

}
