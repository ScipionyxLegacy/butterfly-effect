package com.scipionyx.butterflyeffect.frontend.ui.view.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.scipionyx.butterflyeffect.frontend.services.UserMenuService;
import com.scipionyx.butterflyeffect.frontend.ui.panel.bottom.BottomPanel;
import com.vaadin.navigator.Navigator;
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
	protected UserMenuService userMenuService;

	protected Navigator navigator;

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

	public UserMenuService getUserMenuService() {
		return userMenuService;
	}

	public void setUserMenuService(UserMenuService userMenuService) {
		this.userMenuService = userMenuService;
	}

}
