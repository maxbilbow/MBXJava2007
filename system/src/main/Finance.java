package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Finance extends Control{
	 private ResultSet results;
	 private static final String sql =
		    "SELECT finance.date, finance.in_out, finance.details, finance.amount FROM finance";

	public Finance(Browser browser) throws SQLException {
		super(browser);
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init() throws SQLException {
		// TODO Auto-generated method stub
		Statement statement = browser.getDatabase().createStatement();
	      results = statement.executeQuery(sql);
	      Vector data = new Vector();
	      while (results.next()) {
	        Vector row = new Vector();
	        row.add(results.getDate("date"));
	        row.add(results.getString("details"));
	        row.add(results.getString("in_out"));
	        row.add(results.getInt("amount"));
	        
	        data.add(row);
	      }
	      gui = new FinanceScreen(data);
	}

}
