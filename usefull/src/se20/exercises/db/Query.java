package se20.exercises.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import se20.examples.persist.db.DerbyUtils;


/**
 * Demonstration of querying a database using JDBC.
 *
 * @author Nick Efford
 * @author YOUR NAME HERE
 * @version 1.0 (2006-?-?)
 */
public class Query
{
  /**
   * Application entry point.
   *
   * @param args Command-line arguments
   */
  public static void main(String[] args)
  {
    // Parse command line

    boolean serverConnection = false;
    if (args.length > 0 && args[0].equalsIgnoreCase("server"))
      serverConnection = true;

    // Determine driver name and database connection URL

    String driver = null, url = null;

    if (serverConnection) {
      driver = "org.apache.derby.jdbc.ClientDriver";
      url = "jdbc:derby://localhost/data/books";
    }
    else {
      driver = "org.apache.derby.jdbc.EmbeddedDriver";
      url = "jdbc:derby:data/books";
    }

    try {
      // Establish connection

      System.setProperty("jdbc.drivers", driver);
      Connection connection = DriverManager.getConnection(url);
      System.out.println("Connection established.");

      // TODO: Perform query

      // TODO: Display results

      connection.close();
      System.out.println("Connection closed.");
    }
    catch (SQLException error) {
      System.err.println("Connection error!");
      DerbyUtils.traceErrors(error);
    }
    finally {
      if (! serverConnection)
        DerbyUtils.shutdown("data/books");
    }
   }
}
