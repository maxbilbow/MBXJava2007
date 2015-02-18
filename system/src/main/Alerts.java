package main;

import java.sql.SQLException;

public class Alerts extends Control {

	public Alerts(Browser browser) throws SQLException {
		super(browser);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init() {
		gui = new AlertScreen();
	}

	


}
