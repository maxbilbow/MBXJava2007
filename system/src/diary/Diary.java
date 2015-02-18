package diary;

import java.sql.SQLException;

import main.Browser;
import main.Control;

public class Diary extends Control{

	public Diary(Browser browser) throws SQLException {
		super(browser);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init() {
		gui = new DiaryScreen();
		((DiaryScreen) gui).setLayout(DiaryScreen.YEAR);
	}

	
}
