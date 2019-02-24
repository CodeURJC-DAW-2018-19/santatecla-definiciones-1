package com.group5.definitions.usersession;

public class Tab {
	private String tabName, tabURL;
	private boolean closable, active;
	
	public Tab(String tabName, String tabURL, boolean closable) {
		super();
		this.tabName = tabName;
		this.tabURL = tabURL;
		this.closable = closable;
		this.active = true;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getTabURL() {
		return tabURL;
	}

	public void setTabURL(String tabURL) {
		this.tabURL = tabURL;
	}

	public boolean isClosable() {
		return closable;
	}

	public void setClosable(boolean closable) {
		this.closable = closable;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Tab [tabName=" + tabName + ", tabURL=" + tabURL + ", closable=" + closable + ", active=" + active + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tab) {
			return ((Tab) obj).tabName.equals(this.tabName);
		} else return false;
	}
	
}
