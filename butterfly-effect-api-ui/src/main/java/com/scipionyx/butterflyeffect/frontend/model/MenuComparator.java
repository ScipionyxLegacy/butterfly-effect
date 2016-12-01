package com.scipionyx.butterflyeffect.frontend.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 
 * @author Renato Mendes
 *
 */
public class MenuComparator implements Comparator<Menu>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Menu o1, Menu o2) {

		if (o1.getParent() == null && o2.getParent() == null)
			return 0;

		if (o1.getParent() == null && o2.getParent() != null)
			return -1;

		if (o1.getParent() != null && o2.getParent() == null)
			return 1;

		if (o1.getParent() != null && o2.getParent() != null) {

		}

		return 0;
	}

}
