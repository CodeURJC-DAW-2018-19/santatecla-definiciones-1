package com.grupo5.definiciones.usersession;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserSessionInfoComponent {

	List<Tab> openTabs;

	public UserSessionInfoComponent() {
		openTabs = new ArrayList<>();
		openTabs.add(new Tab("inicio", "/", false));
	}

	public List<Tab> getOpenTabs() {
		return openTabs;
	}

	public void addTab(Tab tab) {
		openTabs.add(tab);
		setActive(tab.getTabName());
	}

	public void setActive(String tabName) {
		for (Tab t : openTabs) {
			if (t.isActive()) {
				t.setActive(false);
			}
			if (t.getTabName().equals(tabName)) {
				t.setActive(true);
			}
		}
	}

}
