package main;

import java.sql.SQLException;

public class Main extends Control{

	public Main(Browser browser) throws SQLException {
		super(browser);
		// TODO Auto-generated constructor stub
		gui = new MainScreen();
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
	}
	
	

}
