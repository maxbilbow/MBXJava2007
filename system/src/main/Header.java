package main;

import java.sql.SQLException;

import Database.ToolbarSettings;

public class Header extends Control{
	
	ToolbarSettings toolbarSettings;
	public Header(Browser browser) throws SQLException {
		super(browser);
		toolbarSettings = new ToolbarSettings(browser.getDatabase());
		// TODO Auto-generated constructor stub
	}

	Boolean[] buttonOn;
	@Override
	public void clearData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init() {
		buttonOn = new Boolean[Settings.MAX_HEADER_BUTTONS];
		gui = new HeaderScreen();
		addListener(Settings.MAIN);
		addListener(Settings.DIARY);
		addListener(Settings.FINANCE);
		addListener(Settings.CONTACTS);
		addListener(Settings.FIVE);
	}
	private void addListener(final int button){
		((HeaderScreen) gui).addButtonListener(button,Notify.headerButton(browser,button));
	}

	public void toggleButton() throws SQLException {
		//toolbarSettings.tempInit();
		toolbarSettings.setButtonUsed(3, !toolbarSettings.isButtonUsed(3));
		((HeaderScreen) gui).enableButtons(toolbarSettings);
	}

}
