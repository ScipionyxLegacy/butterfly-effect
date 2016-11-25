package com.scipionyx.butterflyeffect.frontend.core.ui.view.panel.top;

import javax.annotation.PostConstruct;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * @author Renato Mendes
 *
 */
@SpringComponent()
@ViewScope
class TopMenuLogo extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7676337878819401061L;

	// 2 inches
	public static final float WIDTH = 250;

	// @Autowired
	// private SystemConfiguration systemConfiguration;

	/**
	 * 
	 */
	@PostConstruct()
	final void build() {

		// this.setIcon(VaadinIcons.ABACUS);
		this.setStyleName(ValoTheme.MENU_TITLE);
		this.setMargin(false);
		this.setSpacing(false);
		this.setWidth(WIDTH, Unit.PIXELS);

		Label label = new Label("ButterFlyEffect");

		// if (systemConfiguration != null &&
		// systemConfiguration.getDisplayName() != null)
		// label.setValue(systemConfiguration.getDisplayName());
		// else {
		// label.setValue("ButterflyEffect");
		// }

		this.addComponent(label);

	}

}
