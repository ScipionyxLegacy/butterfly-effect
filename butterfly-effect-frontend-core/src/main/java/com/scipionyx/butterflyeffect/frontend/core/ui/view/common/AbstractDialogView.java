package com.scipionyx.butterflyeffect.frontend.core.ui.view.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.scipionyx.butterflyeffect.frontend.core.services.MenuService;
import com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.bottom.BottomPanel;
import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public abstract class AbstractDialogView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Services
	@Autowired
	protected transient MenuService userMenuService;

	protected BottomPanel bottomPanel;

	// TODO Is this needed ?

	protected boolean built;

	/**
	 * 
	 * @throws Exception
	 */
	public void build() throws Exception {

		if (built) {
			return;
		}

		built = true;

		//
		//

		doBuild();

	}

	//
	public abstract void doBuild();

	public MenuService getUserMenuService() {
		return userMenuService;
	}

	public void setUserMenuService(MenuService userMenuService) {
		this.userMenuService = userMenuService;
	}

}
