package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.scene.control.Tab;

abstract class TabParent{
	protected Tab tab;
	private String tabText;
	protected ResourceBundle myResources;
	
	public TabParent(ResourceBundle myResources, String tabText) {
		this.tabText = tabText;
		this.myResources = myResources;
	}

	public Tab createTab() {
		tab = new Tab(tabText);
		setContent();
		return tab;
	}

	private void updateNode(){
		setContent();
	}
	
	abstract void setContent();

}
