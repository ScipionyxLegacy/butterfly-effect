package com.scipionyx.butterflyeffect.ui.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vaadin.icons.VaadinIcons;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface MenuConfiguration {

	public static final String NULL = null;

	public enum Position {
		IGNORE, TOP_MAIN, TOP_RIGHT, LEFT_MAIN, LEFT_BOTTOM
	}

	public enum Target {
		MAIN, DIALOG, DIALOG_EXCLUSIVE
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
	public String tooltip() default "";

	/**
	 * Defines the position for the menu defined on the View
	 * 
	 * @return
	 */
	public Position position() default Position.IGNORE;

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

	/**
	 * 
	 * @return
	 */
	public VaadinIcons icon() default VaadinIcons.YOUTUBE_SQUARE;

	/**
	 * 
	 * @return
	 */
	public boolean visible() default true;

	/**
	 * 
	 * 
	 * @return
	 */
	public String[] roles() default { "ALL" };

	/**
	 * 
	 * @return
	 */
	public Target target() default Target.MAIN;

}
