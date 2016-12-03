package com.scipionyx.butterflyeffect.frontend.checkfraud.ui.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.scipionyx.butterflyeffect.api.checkfraud.model.TrainCheckImage;
import com.scipionyx.butterflyeffect.api.checkfraud.services.CheckFraudServiceFactory;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.common.AbstractView;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;
import com.scipionyx.butterflyeffect.ui.view.ViewConfiguration;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.StreamVariable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.AbstractColorPicker.PopupStyle;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.Component;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Html5File;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
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
@SpringComponent(value = TrainCheckIdentificationView.VIEW_NAME)
@SpringView(name = TrainCheckIdentificationView.VIEW_NAME)
@UIScope

//
@ViewConfiguration(configurationFile = "CheckFraudLoadImagesView.info")
@MenuConfiguration(position = Position.TOP_MAIN, label = "Train Check Identifier", group = "", order = 1, parent = RootView.VIEW_NAME)
public class TrainCheckIdentificationView extends AbstractView {

	public static final String VIEW_NAME = "butterfly-effect-frontend-checkfraud:TrainCheckIdentifierView";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ProgressBar progress;

	@Autowired
	private CheckFraudServiceFactory checkFraudServiceFactory;

	private BeanItem<TrainCheckImage> item;
	private FieldGroup binder;

	private Button btnPreview;
	private Button btnReset;
	private Button btnAccept;

	private byte[] originalCheckImageByteArray;

	private Panel originalCheckImage;

	private Panel analizedCheckImage;

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

		//
		TrainCheckImage bean = new TrainCheckImage();
		item = new BeanItem<TrainCheckImage>(bean);

		//
		FormLayout formSignature = new FormLayout();
		formSignature.setStyleName(ValoTheme.FORMLAYOUT_LIGHT);

		Label section = new Label("Signature");
		section.addStyleName(ValoTheme.LABEL_H2);
		section.addStyleName(ValoTheme.LABEL_COLORED);
		formSignature.addComponent(section);

		TextField signatureX = new TextField("X", item.getItemProperty("signatureX"));
		TextField signatureY = new TextField("Y", item.getItemProperty("signatureY"));
		TextField signatureW = new TextField("Width", item.getItemProperty("signatureW"));
		TextField signatureH = new TextField("Height", item.getItemProperty("signatureH"));
		ColorPicker colorPicker = new ColorPicker("Color", Color.RED);
		colorPicker.setPopupStyle(PopupStyle.POPUP_SIMPLE);
		colorPicker.setCaption("Color");
		colorPicker.setReadOnly(true);

		formSignature.addComponent(signatureX);
		formSignature.addComponent(signatureY);
		formSignature.addComponent(signatureW);
		formSignature.addComponent(signatureH);
		formSignature.addComponent(colorPicker);

		//
		FormLayout formBottomline = new FormLayout();
		formBottomline.setStyleName(ValoTheme.FORMLAYOUT_LIGHT);

		Label captionBottomline = new Label("Bottomline");
		captionBottomline.addStyleName(ValoTheme.LABEL_H2);
		captionBottomline.addStyleName(ValoTheme.LABEL_COLORED);
		formBottomline.addComponent(captionBottomline);

		TextField bottomlineX = new TextField("X", item.getItemProperty("bottomlineX"));
		TextField bottomlineY = new TextField("Y", item.getItemProperty("bottomlineY"));
		TextField bottomlineW = new TextField("Width", item.getItemProperty("bottomlineW"));
		TextField bottomlineH = new TextField("Height", item.getItemProperty("bottomlineH"));
		ColorPicker bottomlineColorPicker = new ColorPicker("Color", Color.BLUE);
		bottomlineColorPicker.setPopupStyle(PopupStyle.POPUP_SIMPLE);
		bottomlineColorPicker.setCaption("Color");
		bottomlineColorPicker.setReadOnly(true);

		formBottomline.addComponent(bottomlineX);
		formBottomline.addComponent(bottomlineY);
		formBottomline.addComponent(bottomlineW);
		formBottomline.addComponent(bottomlineH);
		formBottomline.addComponent(bottomlineColorPicker);

		//
		FormLayout formAccountOwner = new FormLayout();
		formAccountOwner.setStyleName(ValoTheme.FORMLAYOUT_LIGHT);

		Label captionAccountOwner = new Label("Account Owner");
		captionAccountOwner.addStyleName(ValoTheme.LABEL_H2);
		captionAccountOwner.addStyleName(ValoTheme.LABEL_COLORED);

		TextField accountOwnerX = new TextField("X", item.getItemProperty("accountOwnerX"));
		TextField accountOwnerY = new TextField("Y", item.getItemProperty("accountOwnerY"));
		TextField accountOwnerW = new TextField("Width", item.getItemProperty("accountOwnerW"));
		TextField accountOwnerH = new TextField("Height", item.getItemProperty("accountOwnerH"));
		ColorPicker accountOwnerColorPicker = new ColorPicker("Color", Color.GREEN);
		accountOwnerColorPicker.setPopupStyle(PopupStyle.POPUP_SIMPLE);
		accountOwnerColorPicker.setCaption("Color");
		accountOwnerColorPicker.setReadOnly(true);

		formAccountOwner.addComponent(captionAccountOwner);
		formAccountOwner.addComponent(accountOwnerX);
		formAccountOwner.addComponent(accountOwnerY);
		formAccountOwner.addComponent(accountOwnerW);
		formAccountOwner.addComponent(accountOwnerH);
		formAccountOwner.addComponent(accountOwnerColorPicker);

		//
		binder = new FieldGroup(item);
		binder.setBuffered(true);

		//
		GridLayout formLayouts = new GridLayout(2, 2, formSignature, formBottomline, formAccountOwner);
		formLayouts.setSpacing(true);
		formLayouts.setWidth(100, Unit.PERCENTAGE);

		//
		Label infoLabel = new Label("Drop the Check images Here");
		infoLabel.addStyleName(ValoTheme.LABEL_COLORED);

		VerticalLayout dropPane = new VerticalLayout(infoLabel);
		dropPane.setMargin(true);
		dropPane.setComponentAlignment(infoLabel, Alignment.MIDDLE_CENTER);
		dropPane.setWidth(450f, Unit.PIXELS);
		dropPane.setHeight(100f, Unit.PIXELS);
		dropPane.addStyleName(ValoTheme.PANEL_WELL);

		final ImageDropBox dropBox = new ImageDropBox(dropPane);
		dropBox.setSizeUndefined();

		progress = new ProgressBar();
		progress.setIndeterminate(true);
		progress.setVisible(false);
		dropPane.addComponent(progress);

		//
		originalCheckImage = new Panel("Original Check Image");
		originalCheckImage.setWidth(450, Unit.PIXELS);
		originalCheckImage.setVisible(false);

		analizedCheckImage = new Panel("Analized Check Image");
		analizedCheckImage.setWidth(450, Unit.PIXELS);
		analizedCheckImage.setVisible(false);

		//
		VerticalLayout second = new VerticalLayout(dropBox, originalCheckImage, analizedCheckImage);
		second.setComponentAlignment(dropBox, Alignment.MIDDLE_CENTER);
		second.setComponentAlignment(originalCheckImage, Alignment.MIDDLE_CENTER);
		second.setComponentAlignment(analizedCheckImage, Alignment.MIDDLE_CENTER);
		second.setSpacing(true);

		HorizontalLayout horizontalLayout = new HorizontalLayout(formLayouts, second);
		horizontalLayout.setSpacing(true);
		horizontalLayout.setMargin(new MarginInfo(false, true, false, true));
		horizontalLayout.setSizeFull();

		workAreaPanel.addComponent(horizontalLayout);

		final Button acceptButton = new Button("Accept", new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("Ops");
			}
		});
		acceptButton.setEnabled(false);

		Button previewButton = new Button("Preview", new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					binder.commit();
					TrainCheckImage analyzed = checkFraudServiceFactory.instance()
							.trainPreview(item.getBean().getFileName(), originalCheckImageByteArray, item.getBean());
					showImage(null, FilenameUtils.getExtension(analyzed.getFileName()), analyzed.getAnalyzed(),
							analizedCheckImage);
				} catch (CommitException | IOException e) {
					Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
					e.printStackTrace();
				}

			}
		});

		previewButton.setEnabled(false);

		final Button resetButton = new Button("Reset", new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				binder.clear();
				binder.discard();
				originalCheckImage.setContent(null);
				originalCheckImage.setVisible(false);
				acceptButton.setEnabled(false);
				previewButton.setEnabled(false);
			}
		});
		resetButton.setEnabled(false);

		//
		addButton(ValoTheme.BUTTON_FRIENDLY, resetButton);
		addButton(ValoTheme.BUTTON_FRIENDLY, previewButton);
		addButton(ValoTheme.BUTTON_PRIMARY, acceptButton);

		btnPreview = previewButton;
		btnAccept = acceptButton;
		btnReset = resetButton;

	}

	/**
	 * 
	 * @param name
	 * @param type
	 * @param bas
	 */
	protected void showImage(String name, final String type, final byte[] bs, Panel panel) {
		if (StringUtils.isBlank(name)) {
			name = UUID.randomUUID().toString() + "." + type;
		}
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
		final Embedded embedded = new Embedded(name, resource);
		embedded.setHeight(150, Unit.POINTS);
		//
		panel.setContent(embedded);
		panel.setVisible(true);

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
		private static final long FILE_SIZE_LIMIT = 2 * 1024 * 1024;

		public ImageDropBox(Component root) {
			super(root);
			setDropHandler(this);
		}

		/**
		 * 
		 */
		@Override
		public void drop(final DragAndDropEvent dropEvent) {

			final WrapperTransferable tr = (WrapperTransferable) dropEvent.getTransferable();
			final Html5File[] files = tr.getFiles();

			if (files != null) {

				if (files.length != 1) {
					Notification.show("Please, only one check image", Type.WARNING_MESSAGE);
					return;
				}

				Html5File html5File = files[0];
				final String fileName = html5File.getFileName();

				if (html5File.getFileSize() > FILE_SIZE_LIMIT) {
					Notification.show("File rejected. Max 2Mb files are accepted by Sampler",
							Notification.Type.ERROR_MESSAGE);
					return;
				}

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
					public void streamingStarted(final StreamingStartEvent event) {
					}

					@Override
					public void streamingFinished(final StreamingEndEvent event) {
						try {

							item.getBean().setFileName(fileName);

							progress.setVisible(false);
							originalCheckImageByteArray = bas.toByteArray();
							showImage(fileName, html5File.getType(), originalCheckImageByteArray, originalCheckImage);

							// Arrange the buttons
							btnPreview.setEnabled(true);
							btnAccept.setEnabled(true);
							btnReset.setEnabled(true);

							if (binder.isValid()) {

								TrainCheckImage analyzed = checkFraudServiceFactory.instance().trainPreview(
										item.getBean().getFileName(), originalCheckImageByteArray, item.getBean());
								showImage(null, FilenameUtils.getExtension(analyzed.getFileName()),
										analyzed.getAnalyzed(), analizedCheckImage);

							}

						} catch (IOException e) {
							e.printStackTrace();
							Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
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

					@Override
					public void onProgress(StreamingProgressEvent event) {
					}
				};

				html5File.setStreamVariable(streamVariable);
				progress.setVisible(true);

			}

		}

		@Override
		public AcceptCriterion getAcceptCriterion() {
			return AcceptAll.get();
		}
	}

}
