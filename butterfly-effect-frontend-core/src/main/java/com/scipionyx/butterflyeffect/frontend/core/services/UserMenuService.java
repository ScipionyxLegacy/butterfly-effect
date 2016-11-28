package com.scipionyx.butterflyeffect.frontend.core.services;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scipionyx.butterflyeffect.frontend.model.Menu;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

/**
 * 
 * This service is responsible for loading the application menus
 * 
 * 
 * @author Renato Mendes
 *
 */
@SpringComponent("userMenuService")
@UIScope()
public class UserMenuService implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuService.class);

	private List<Menu> menus;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@PostConstruct
	private void init() {
		new FastClasspathScanner("com.scipionyx.butterflyeffect")
				.matchAllAnnotationClasses(ClassMatchProcessor)Classes(MenuConfiguration.class, c -> process(c)).scan();
	}

	/**
	 * 
	 * @param c
	 */
	private void process(Class<? extends MenuConfiguration> c) {
		LOGGER.info("" + c.getName());
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}
