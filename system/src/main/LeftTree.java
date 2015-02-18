package main;

import java.awt.Component;
import java.sql.SQLException;

public class LeftTree extends Control{

	public LeftTree(Browser browser) throws SQLException {
		super(browser);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init() {
		gui = new LeftTreeScreen();
	}

}
