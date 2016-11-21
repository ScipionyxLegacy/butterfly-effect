package com.scipionyx.butterflyeffect.frontend.model.ui.view;

import com.scipionyx.butterflyeffect.frontend.ui.view.configuration.ViewConfiguration;
import com.scipionyx.butterflyeffect.model.model.datasource.AbstractDataSource;
import com.scipionyx.butterflyeffect.model.model.datasource.ORMDataSource;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * 
 * 
 * @version 0.1.0
 * @author Renato Mendes
 *
 */
@SpringComponent("dataSourceConfigurationView")
@UIScope()
@ViewConfiguration(configurationFile = "DataSourceView.info")
public class DataSourceConfigurationView extends AbstractDataModelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TabSheet sheet;

	private

<<<<<<< HEAD:butterfly-effect-frontend-model/src/main/java/com/scipionyx/butterflyeffect/frontend/model/ui/view/DataSourceConfigurationView.java
=======
	// @Autowired
	// private DataSourceService datasourceService;

>>>>>>> origin/0.1.0:butterfly-effect-frontend-model/src/main/java/com/scipionyx/butterflyeffect/frontend/model/ui/view/DataSourceView.java
	/**
	 * 
	 */
	public void doBuildWorkArea(VerticalLayout workAreaPanel) {

		// Setting up the title

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

		sheet.addTab(entitiesPanel, "ORM");
		sheet.addTab(eventsPanel, "ElasticSearch");
		sheet.addTab(factsPanel, "HBase");
		sheet.addTab(incidentsPanel, "Hive");

		workAreaPanel.addComponent(sheet);

	}

	/**
	 * 
	 * @param layout
	 */
	private void doBuildTab(VerticalLayout layout) {
		layout.setMargin(new MarginInfo(true, false, false, false));
		Grid grid = new Grid();
		layout.addComponent(grid);
	}

	@Override
	public void doEnter(ViewChangeEvent event) {
	}

	/**
	 * 
	 * @param buttomAreaPanel
	 */
	@Override
	public void doBuildBottomArea(HorizontalLayout buttomAreaPanel) {

		MenuBar menuBar = new MenuBar();
		menuBar.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		buttomAreaPanel.addComponent(menuBar);
		buttomAreaPanel.setComponentAlignment(menuBar, Alignment.MIDDLE_RIGHT);

		MenuItem menuBarItemAddNew = menuBar.addItem("Add new", FontAwesome.PLUS, null);

		menuBarItemAddNew.addItem("ORM", new MenuBar.Command() {

			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				createDialog(new ORMDataSource(), true);
			}
		});

		menuBarItemAddNew.addItem("Elasticsearch", new MenuBar.Command() {

			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				createDialog(new ORMDataSource(), true);
			}
		});

		menuBarItemAddNew.addItem("HBase", new MenuBar.Command() {

			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				createDialog(new ORMDataSource(), true);
			}
		});

		menuBarItemAddNew.addItem("Hive", new MenuBar.Command() {

			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				createDialog(new ORMDataSource(), true);
			}
		});

	}

	/**
	 * 
	 * @param dataSource
	 * @param isNew
	 */
	private void createDialog(AbstractDataSource dataSource, boolean isNew) {
		if (dataSource instanceof ORMDataSource) {
			createORMDialog((ORMDataSource) dataSource, isNew);
		}
	}

	/**
	 * 
	 */
	private void createORMDialog(ORMDataSource dataSource, boolean isNew) {

		String windowCaption = (isNew) ? "Add New Data ORM Source" : "Edit ORM Data Source";

		final Window window = new Window(windowCaption);

		window.setWidth(400, Unit.PIXELS);

		FormLayout formLayout = new FormLayout();
		formLayout.setMargin(true);

		BeanFieldGroup<ORMDataSource> binder = new BeanFieldGroup<>(ORMDataSource.class);
		binder.setItemDataSource(dataSource);
		binder.setBuffered(true);

		TextField nameField = binder.buildAndBind("Name", "name", TextField.class);
		TextArea descriptionField = binder.buildAndBind("Description", "description", TextArea.class);
		TextField hostField = binder.buildAndBind("Host", "host", TextField.class);
		TextField portField = binder.buildAndBind("Port", "port", TextField.class);
		ComboBox databaseTypeField = binder.buildAndBind("Database Type", "database", ComboBox.class);
		TextField databaseNameField = binder.buildAndBind("Database Name", "databaseName", TextField.class);
		TextField userField = binder.buildAndBind("User", "user", TextField.class);
		PasswordField passwordField = binder.buildAndBind("Password", "password", PasswordField.class);
		Field<?> isClusterField = binder.buildAndBind("Is Cluster", "cluster");

		// Name
		nameField.setSizeFull();
		nameField.setReadOnly(!isNew);
		nameField.setNullRepresentation("");

		// Description
		descriptionField.setSizeFull();
		descriptionField.setNullRepresentation("");

		// Host
		hostField.setSizeFull();
		hostField.setNullRepresentation("");

		//
		databaseTypeField.setSizeFull();

		//
		databaseNameField.setSizeFull();
		databaseNameField.setNullRepresentation("");

		//
		userField.setSizeFull();
		userField.setNullRepresentation("");

		//
		passwordField.setSizeFull();
		passwordField.setNullRepresentation("");

		// Port
		portField.setSizeFull();

		//
		isClusterField.setSizeFull();

		Button buttonSave = new Button(FontAwesome.SAVE);
		buttonSave.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.commit();
					// check information
					// datasourceService.doVerify(dataSource);
					// save
					// datasourceService.doWrite(dataSource);
					window.close();
				} catch (CommitException e) {
					Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
				}

			}
		});

		//
		formLayout.addComponent(nameField);
		formLayout.addComponent(descriptionField);
		formLayout.addComponent(hostField);
		formLayout.addComponent(portField);
		formLayout.addComponent(databaseTypeField);
		formLayout.addComponent(databaseNameField);
		formLayout.addComponent(userField);
		formLayout.addComponent(passwordField);
		formLayout.addComponent(isClusterField);
		formLayout.addComponent(buttonSave);

		window.setContent(formLayout);

		window.center();
		UI.getCurrent().addWindow(window);
	}

	@Override
	public void doBuild() {
<<<<<<< HEAD:butterfly-effect-frontend-model/src/main/java/com/scipionyx/butterflyeffect/frontend/model/ui/view/DataSourceConfigurationView.java
=======
		// TODO Auto-generated method stub
>>>>>>> origin/0.1.0:butterfly-effect-frontend-model/src/main/java/com/scipionyx/butterflyeffect/frontend/model/ui/view/DataSourceView.java
		
	}

}
