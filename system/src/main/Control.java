package main;

import java.sql.SQLException;

public abstract class Control {
	protected Screen gui;
	protected Browser browser;
	
	public Control(Browser browser) throws SQLException{
		this.browser = browser;
		//System.out.println("Control init: " + this.getClass());
		init();
	}
	public Screen getGui(){
		return gui;
	}
	
	public abstract void clearData();

	protected abstract void init() throws SQLException;
}
