package com.scipionyx.be.frontend.menu;

import java.io.IOException;

import org.junit.Test;

import com.scipionyx.butterflyeffect.frontend.services.MenuService;

/**
 * 
 * @author Renato Mendes
 *
 */
public class MenuServiceTest {

	/**
	 * 
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {

		MenuService menuService = new MenuService();
		menuService.init();

		/*
		 * Menu menu1 = new Menu(); menu1.setId("menu1");
		 * 
		 * Menu menu2 = new Menu(); menu2.setId("menu2");
		 * 
		 * List<Menu> list = new ArrayList<>(); list.add(menu1);
		 * list.add(menu2);
		 * 
		 * ObjectMapper mapper = new ObjectMapper();
		 * 
		 * mapper.writeValue(new File("/Users/rmendes/Desktop/menu.conf"),
		 * list);
		 */
	}

}
