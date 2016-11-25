package com.scipionyx.butterflyeffect.frontend.configuration.ui.view;

import com.scipionyx.butterflyeffect.configuration.model.SystemConfiguration;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * 
 * 
 * @version 0.1.0
 * @author Renato Mendes
 *
 */
@DesignRoot()
@SpringComponent("SystemConfigurationView")
@UIScope()
@ViewConfiguration(configurationFile = "SystemConfigurationView.info")
public class SystemConfigurationView extends AbstractConfigurationView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Autowired(required = true)
	// private SystemConfigurationService systemConfigurationService;

	// @Autowired
	private SystemConfiguration systemConfiguration;

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
	public void doBuild() {
	}

	@Override
	public void doBuildBottomArea(HorizontalLayout buttomAreaPanel) {

	}

	/**
	 * @throws Exception
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workPanel) throws Exception {

		// systemConfiguration = systemConfigurationService.readAll().get(0);

		BeanFieldGroup<SystemConfiguration> binder = new BeanFieldGroup<>(SystemConfiguration.class);
		binder.setItemDataSource(systemConfiguration);

		FormLayout formLayout = new FormLayout();

		if (true)
			return;

		@SuppressWarnings("unused")
		TextField textField = new TextField("System Configuration File", systemConfiguration.getFileName());
		textField.setSizeFull();
		textField.setReadOnly(true);

		formLayout.addComponent(textField);

		Field<?> configFolderTextField = binder.buildAndBind("Configuration Folder", "configurationFolder");
		// configFolderTextField.setWidth(600, Unit.PIXELS);
		configFolderTextField.setSizeFull();
		formLayout.addComponent(configFolderTextField);

		Field<?> tempFolderTextField = binder.buildAndBind("Temp/Work Folder", "tempFolder");
		tempFolderTextField.setSizeFull();
		formLayout.addComponent(tempFolderTextField);

		Field<?> newCertUploadFolder = binder.buildAndBind("New Cert Upload Folder", "newCertUploadFolder");
		newCertUploadFolder.setSizeFull();
		formLayout.addComponent(newCertUploadFolder);

		Field<?> backUpFolderTextField = binder.buildAndBind("Backup Folder", "backupFolder");
		backUpFolderTextField.setSizeFull();
		formLayout.addComponent(backUpFolderTextField);

		Field<?> defaultKeyStorePasswordTextField = binder.buildAndBind("Default KeyStore Password",
				"defaultKeyStorePassword", PasswordField.class);
		formLayout.addComponent(defaultKeyStorePasswordTextField);

		formLayout.addComponent(binder.buildAndBind("Encryption Phrase", "encryptionPhrase", PasswordField.class));

		workPanel.addComponent(formLayout);
		workPanel.setComponentAlignment(formLayout, Alignment.TOP_CENTER);

		// Buffer the form content
		binder.setBuffered(true);

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);

		formLayout.addComponent(horizontalLayout);

		//

		Button saveButton = new Button("", new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.commit();
					// systemConfigurationService.write(systemConfiguration);
					Notification.show("Configuration Saved", Notification.Type.HUMANIZED_MESSAGE);
				} catch (Exception e) {
					Notification.show("Configuration not saved", e.getMessage(), Notification.Type.ERROR_MESSAGE);
				}
			}
		});
		saveButton.setIcon(FontAwesome.SAVE, "Save Server Configuration");

		horizontalLayout.addComponent(saveButton);

		horizontalLayout.addComponent(new Button("Cancel", new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.discard();
					Notification.show("Original Configuration Restored", Notification.Type.HUMANIZED_MESSAGE);
				} catch (Exception e) {
					Notification.show("Configuration not saved", e.getMessage(), Notification.Type.ERROR_MESSAGE);
				}
			}
		}));

	}

}
