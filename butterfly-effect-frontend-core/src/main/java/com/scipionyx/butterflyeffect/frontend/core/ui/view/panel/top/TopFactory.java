package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.top;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.scipionyx.butterflyeffect.frontend.core.services.MenuService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

/**
 *
 * It has to be a factory, dont try to change it, Vaadin can not reuse
 * components between views.
 * 
 * 
 * @author Renato Mendes
 *
 */
@SpringComponent
@UIScope
public class TopFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MenuService userMenuService;

	/**
	 * 
	 * @return
	 */
	public Component instance() {
		TopMenuPanel topMenuPanel = new TopMenuPanel();
		topMenuPanel.build(userMenuService);
		return topMenuPanel;
	}

}
