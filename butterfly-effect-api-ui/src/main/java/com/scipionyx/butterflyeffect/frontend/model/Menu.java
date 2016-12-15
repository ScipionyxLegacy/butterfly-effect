package com.scipionyx.butterflyeffect.frontend.model;

import java.io.Serializable;

import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.vaadin.server.Resource;

/**
 * 
 * @author Renato Mendes
 *
 */
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String label;

	private String tooltip;

	private String parent;

	private String view;

	private boolean separator;

	private boolean visible;

	private int order;

	private MenuConfiguration.Position position;

	private Resource icon;

	private String[] roles;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public boolean isSeparator() {
		return separator;
	}

	public void setSeparator(boolean separator) {
		this.separator = separator;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public MenuConfiguration.Position getPosition() {
		return position;
	}

	public void setPosition(MenuConfiguration.Position position) {
		this.position = position;
	}

	public Resource getIcon() {
		return icon;
	}

	public void setIcon(Resource icon) {
		this.icon = icon;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

}
