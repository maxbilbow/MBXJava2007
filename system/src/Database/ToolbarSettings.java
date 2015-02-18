package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.Settings;


public class ToolbarSettings {
	
static Boolean[] usedButtons;
Connection database;
private static ResultSet results;
private static final String sql =
	    "SELECT settings.is_on FROM settings";// WHERE type LIKE('button');

public ToolbarSettings(Connection database) throws SQLException{
	this.database = database;
	Statement statement = database.createStatement();
	results = statement.executeQuery(sql);
	init();
}

private void init() throws SQLException {
	usedButtons = new Boolean[Settings.MAX_HEADER_BUTTONS];
	for (int i=0;i<usedButtons.length;++i){
		results.next();
		usedButtons[i] = results.getBoolean("is_on");//TODO make int not char
		System.out.print(results.getBoolean("is_on")+" ");
	}
	System.out.println();
}

public boolean isButtonUsed(int i){ //TODO All methods open a db connection???
	try {
		return results.getBoolean("is_on");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}

public static boolean isButtonUsed(Connection database, int i) throws SQLException{
	return results.getBoolean(i); //TODO FIX IT!
		//return !Boolean.getBoolean(String.valueOf(results.getBoolean("is_on")));

}

public void setButtonUsed(int i, boolean bool) throws SQLException{
	 usedButtons[i] = bool;
	 int b = (bool?1:0);
	 String sql = 
		 "UPDATE settings SET is_on='"+ b 
		 +"' WHERE settings.number LIKE '"+String.valueOf(i)+"'";
	 Statement statement = database.createStatement();
	 statement.executeUpdate(sql);
}

}
