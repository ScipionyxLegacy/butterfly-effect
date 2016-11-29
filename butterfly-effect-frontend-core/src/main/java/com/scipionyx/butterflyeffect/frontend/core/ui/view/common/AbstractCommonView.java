package com.scipionyx.butterflyeffect.frontend.core.ui.view.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.VerticalLayout;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public abstract class AbstractCommonView extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractCommonView.class);

	/**
	 * Adds Menu<br>
	 * Adds Navigation
	 */
	@Override
	public void doBuildMenu() {
	}

	/**
	 * 
	 */
	@Override
	public final void doBuildLeftMenu(VerticalLayout leftMenuPanel) {
	}

}
