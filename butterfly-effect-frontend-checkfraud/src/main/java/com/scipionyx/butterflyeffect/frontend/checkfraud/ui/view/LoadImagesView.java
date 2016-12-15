package com.scipionyx.butterflyeffect.frontend.checkfraud.ui.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.scipionyx.butterflyeffect.api.checkfraud.model.CheckImage;
import com.scipionyx.butterflyeffect.api.checkfraud.services.CheckFraudServiceFactory;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.data.Item;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.StreamVariable;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Html5File;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * This view allows users to load check images directly from the interface the
 * view will send the images to the backend to be processed and will retrieve
 * the image information.
 * 
 * @version 0.1.0
 * @author Renato Mendes
 *
 */
@SpringComponent(value = LoadImagesView.VIEW_NAME)
@SpringView(name = LoadImagesView.VIEW_NAME)
@UIScope
// @Scope(scopeName = "session")

//
@ViewConfiguration(title = "Check Fraud - Load Images")
@MenuConfiguration(position = Position.TOP_MAIN, label = "Load Check Images", group = "", order = 1, parent = RootView.VIEW_NAME, icon = FontAwesome.CHECK, roles = {
		"CHECK_FRAUD" })
public class LoadImagesView extends AbstractView {

	public static final String VIEW_NAME = "butterfly-effect-frontend-checkfraud:loadImagesView";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ProgressBar progress;

	Table resultTable;

	VerticalLayout workAreaPanel;

	@Autowired
	private CheckFraudServiceFactory checkFraudServiceFactory;

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

	/**
	 * 
	 */
	@Override
	public void doBuildWorkArea(VerticalLayout workAreaPanel) throws Exception {

		this.workAreaPanel = workAreaPanel;

		Label infoLabel = new Label("Drop the Check images Here");
		infoLabel.addStyleName(ValoTheme.LABEL_COLORED);

		VerticalLayout dropPane = new VerticalLayout(infoLabel);
		dropPane.setMargin(true);
		dropPane.setComponentAlignment(infoLabel, Alignment.MIDDLE_CENTER);
		dropPane.setWidth(300f, Unit.PIXELS);
		dropPane.setHeight(100f, Unit.PIXELS);
		dropPane.addStyleName(ValoTheme.PANEL_WELL);
		//
		final ImageDropBox dropBox = new ImageDropBox(dropPane);
		dropBox.setSizeUndefined();
		//
		progress = new ProgressBar();
		progress.setIndeterminate(true);
		progress.setVisible(false);
		dropPane.addComponent(progress);

		workAreaPanel.addComponent(dropBox);

		// Results Table
		resultTable = new Table("Result Table");
		resultTable.setWidth(100, Unit.PERCENTAGE);
		resultTable.setHeightUndefined();

		resultTable.addContainerProperty("Id", String.class, UUID.randomUUID().toString());
		resultTable.addContainerProperty("File Name", String.class, null);
		resultTable.addContainerProperty("File Type", String.class, null);
		resultTable.addContainerProperty("Status", String.class, null);
		resultTable.addContainerProperty("Description", String.class, null);
		resultTable.addContainerProperty("Color Space", String.class, null);
		resultTable.addContainerProperty("File", Embedded.class, null);
		resultTable.addContainerProperty("File Processed", Embedded.class, null);

		Object[] visibleColumns = { "File Name", "File Type", "Status", "Description", "Color Space", "File",
				"File Processed" };
		resultTable.setVisibleColumns(visibleColumns);
		resultTable.setColumnExpandRatio("File", 1f);
		resultTable.setColumnExpandRatio("File Processed", 1f);

		workAreaPanel.addComponent(resultTable);

	}

	/**
	 * 
	 * @author Renato Mendes - rmendes@bottomline.com /
	 *         renato.mendes.1123@gmail.com
	 *
	 */
	private class ImageDropBox extends DragAndDropWrapper implements DropHandler {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static final long FILE_SIZE_LIMIT = 2 * 1024 * 1024; // 2MB

		public ImageDropBox(Component root) {
			super(root);
			setDropHandler(this);
		}

		@Override
		public void drop(final DragAndDropEvent dropEvent) {

			// expecting this to be an html5 drag
			final WrapperTransferable tr = (WrapperTransferable) dropEvent.getTransferable();
			final Html5File[] files = tr.getFiles();

			if (files != null) {

				for (final Html5File html5File : files) {

					final String fileName = html5File.getFileName();

					if (html5File.getFileSize() > FILE_SIZE_LIMIT) {

						Notification.show("File rejected. Max 2Mb files are accepted by Sampler",
								Notification.Type.ERROR_MESSAGE);

					} else {

						final ByteArrayOutputStream bas = new ByteArrayOutputStream();
						final StreamVariable streamVariable = new StreamVariable() {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							@Override
							public OutputStream getOutputStream() {
								return bas;
							}

							@Override
							public boolean listenProgress() {
								return false;
							}

							@Override
							public void onProgress(final StreamingProgressEvent event) {
							}

							@Override
							public void streamingStarted(final StreamingStartEvent event) {
							}

							@Override
							public void streamingFinished(final StreamingEndEvent event) {
								try {
									progress.setVisible(false);
									byte[] bs = bas.toByteArray();
									CheckImage analyze = checkFraudServiceFactory.instance().analyze(fileName, bs);
									showFile(fileName, html5File.getType(), bs, analyze);
								} catch (IOException e) {
									Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
								}
							}

							@Override
							public void streamingFailed(final StreamingErrorEvent event) {
								progress.setVisible(false);
							}

							@Override
							public boolean isInterrupted() {
								return false;
							}
						};

						html5File.setStreamVariable(streamVariable);

						progress.setVisible(true);

					}
				}

			} else {
				final String text = tr.getText();
				if (text != null) {
					showText(text);
				}
			}
		}

		/**
		 * 
		 * @param text
		 */
		private void showText(final String text) {
			Notification.show("Please upload only image files", Notification.Type.ERROR_MESSAGE);
		}

		/**
		 * 
		 * @param name
		 * @param type
		 * @param analyze
		 * @param bas
		 */
		private void showFile(final String name, final String type, final byte[] bs, CheckImage analyze) {
			// resource for serving the file contents
			final StreamSource streamSource = new StreamSource() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public InputStream getStream() {
					if (bs != null) {
						final byte[] byteArray = bs;
						return new ByteArrayInputStream(byteArray);
					}
					return null;
				}
			};
			final StreamResource resource = new StreamResource(streamSource, name);
			final Embedded embedded_original = new Embedded(name, resource);
			embedded_original.setHeight(150, Unit.POINTS);
			//

			List<Embedded> modifiedImages = new ArrayList<>();

			for (byte[] processedImage : analyze.getProcessedImages()) {

				final StreamSource streamSource_modified = new StreamSource() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public InputStream getStream() {
						final byte[] byteArray = processedImage;
						return new ByteArrayInputStream(byteArray);
					}
				};

				String processedImageName = UUID.randomUUID().toString() + "." + analyze.getOriginalFileExtension();

				final Embedded embedded = new Embedded(processedImageName,
						new StreamResource(streamSource_modified, processedImageName));

				modifiedImages.add(embedded);

			}

			showComponent(embedded_original, modifiedImages, name, type, analyze);
		}

		/**
		 * 
		 * @param c
		 * @param modifiedImages
		 * @param name
		 * @param type
		 * @param analyze
		 */
		@SuppressWarnings("unchecked")
		private void showComponent(final Component c, List<Embedded> modifiedImages, final String name, String type,
				CheckImage analyze) {

			if (!type.startsWith("image")) {
				Notification.show("File type [" + type + "] are not permitted.", "Only image files are permitted",
						Notification.Type.ERROR_MESSAGE);
				return;
			}

			Object newItemId = resultTable.addItem();

			Item row1 = resultTable.getItem(newItemId);
			// row1.getItemProperty("Id").setValue();
			row1.getItemProperty("File Name").setValue(name);
			row1.getItemProperty("File Type").setValue(type);
			row1.getItemProperty("Status").setValue("New");
			row1.getItemProperty("Description").setValue("..");
			row1.getItemProperty("Color Space").setValue(analyze.getColourSpace().toString());
			row1.getItemProperty("File").setValue(c);
			row1.getItemProperty("File Processed").setValue(modifiedImages.get(0));

		}

		@Override
		public AcceptCriterion getAcceptCriterion() {
			return AcceptAll.get();
		}
	}

}
