package com.grupo5.definiciones.usersession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.grupo5.definiciones.model.User;

@Component
@SessionScope
public class UserSessionInfoComponent {

	private User user;
	private List<Tab> openTabs;

	public UserSessionInfoComponent() {
		openTabs = new CopyOnWriteArrayList<>();
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

	public void removeTab(String name) {
		openTabs.remove(new Tab(name, null, false));
		
	}
	
	public void resetTabs() {
		openTabs = new ArrayList<>();
		openTabs.add(new Tab("inicio", "/", false));
	}
	
	public User getLoggedUser() {
		return user;
	}

	public void setLoggedUser(User user) {
		this.user = user;
	}

	public boolean isLoggedUser() {
		return this.user != null;
	}
}
