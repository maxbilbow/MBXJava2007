package Database;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	private static final String CONFIG_FILE = "config";
	
	public static final String DROP_ALL = "DROP TABLE *";
	
	private static final String[][] CREATE_ALL = 
	{
		{	//Create Member Table
			"create table Member (" +
				"id char(7) not null," +
				"pin char(5) not null," +
				"lastname varchar(30) not null," +
				"firstname varchar(20) not null," +
				"primary key (id)" +
			")",
			//Insert into member
			"insert into Member values" +
				"( 'admin', 'admin', 'Max',	'Bilbow' )" 
		},
		{	//create finance table
			"create table Finance ("+
				"id integer not null generated always as identity,"+
				"event_id integer not null,"+
				"date date not null,"+
				"in_out int not null,"+
				"details varchar(40),"+
				"amount double not null,"+
				"primary key (id)"+
			")",
			//Insert into finance table
			"insert into Finance(event_id, date, in_out, amount) values"+
				"( 1,	'2006-11-12',	1,	1250.00 ),"+
				"( 1,	'2006-11-12',	0,	500.00 ),"+
				"( 2,	'2006-11-12',	1,	500.00 )"
		},
		{	//Create settings table
			"create table Settings ("+
				"number char(2) not null,"+
				"type varchar(10) not null,"+
				"is_on boolean,"+
				"primary key (number,type)"+
			")",
			//insert into settings table
			"insert into Settings values"+
				"( '0', 'button', 1 ),"+
				"( '1', 'button', 1 ),"+
				"( '2', 'button', 1 ),"+
				"( '3', 'button', 0 ),"+
				"( '4', 'button', 1 )"
		}
	};
	
	private static String databaseUrl;
	private static Connection database;
	
	public static boolean open(){
		try {
		      // Load configuration from a file

		      FileInputStream input = new FileInputStream(CONFIG_FILE);
		      Properties props = new Properties();
		      props.load(input);
		      input.close();

		      // Connect to database

		      String driver = props.getProperty("driver");
		      databaseUrl = props.getProperty("url");
		      System.setProperty("jdbc.drivers", driver);
		      //Connection database = DriverManager.getConnection(databaseUrl);
		      database = DriverManager.getConnection(databaseUrl);
		      // Create GUI and set up event handling for the window

		      //JFrame gui = new MainGui(database);
		      //return databaseUrl;
		      return true;
		    }
		    catch (Exception error) {
		      error.printStackTrace();
		      //System.exit(1);
		    }
		    return false;
	}

	public static void close(){
		try {
		      DriverManager.getConnection(String.format("%s;shutdown=true", databaseUrl));
		      System.err.println("Problem with shutdown!");
		    }
		    catch (SQLException error) {
		      // Successful shutdown always results in an SQLException
		      System.out.println("Shutdown completed successfully.");
		    }
	}
	
	public static void clearData() {
		try {
			open();
			database.createStatement().execute(DROP_ALL);
			close();
		}
		 catch (Exception error) {
		      error.printStackTrace();
		      System.exit(1);
		    }
	 }
	
	public static void createAll() {
		try{
			open();
			for (int i=0;i<CREATE_ALL.length;i++)
				for (int j=0;j<CREATE_ALL[i].length;i++)
					database.createStatement().execute(CREATE_ALL[i][j]);
			close();
		}catch (Exception error) {
		      error.printStackTrace();
		      System.exit(1);
		    }
	}
	
	public static ResultSet query(String sql){
		//boolean open = open();
		ResultSet results = null;
		try {
			results = database.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//if (open)close();	
		return results;
		
	}


		
}
