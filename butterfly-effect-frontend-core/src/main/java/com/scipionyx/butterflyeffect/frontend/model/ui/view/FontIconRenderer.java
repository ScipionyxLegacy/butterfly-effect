package com.scipionyx.butterflyeffect.frontend.model.ui.view;

import com.vaadin.ui.renderers.ClickableRenderer;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public class FontIconRenderer extends ClickableRenderer<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected FontIconRenderer() {
		super(String.class);
	}

	public FontIconRenderer(RendererClickListener listener) {
		this();
		addClickListener(listener);
	}

}
