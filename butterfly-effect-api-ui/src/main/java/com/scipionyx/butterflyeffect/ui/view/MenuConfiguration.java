package com.scipionyx.butterflyeffect.ui.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MenuConfiguration {

	public static final String NULL = null;

	public enum Position {
		TOP_MAIN, TOP_RIGHT, LEFT_MAIN, LEFT_BOTTOM
	}

	/**
	 * 
	 * @return
	 */
	public String label();

	/**
	 * 
	 * @return
	 */
	public String toolTip() default "";

	/**
	 * Defines the position for the menu defined on the View
	 * 
	 * @return
	 */
	public Position position();

	/**
	 * 
	 * @return
	 */
	public String group();

	/**
	 * Defines the priority order for the menu
	 * 
	 * @return
	 */
	public int order() default -1;

	/**
	 * Defines that the children menus are sorted
	 * 
	 * @return
	 */
	public boolean sortChildren() default true;

	/**
	 * 
	 * @return
	 */
	public String parent() default "";

}
