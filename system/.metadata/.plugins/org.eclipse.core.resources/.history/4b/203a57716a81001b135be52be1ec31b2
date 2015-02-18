package se20.exercises.db;


import se20.examples.persist.db.DerbyUtils;


/**
 * Demonstration of connecting to a database using JDBC.
 *
 * @author Nick Efford
 * @author YOUR NAME HERE
 * @version 1.0 (2006-?-?)
 */
public class Connect
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

    System.out.printf("Using driver %s%n", driver);
    System.out.printf("Connection URL is %s%n", url);

    try {
      // Uncomment line below to see exactly what is happening
      //DriverManager.setLogWriter(new java.io.PrintWriter(System.out));

      // TODO: Load driver

      // TODO: Connect to database
 
      // TODO: Close connection
    }
    //catch (ClassNotFoundException error) {
    //  System.err.println("Failed to load JDBC driver!");
    //  error.printStackTrace();
    //}
    //catch (SQLException error) {
    //  System.err.println("Connection error!");
    //  DerbyUtils.traceErrors(error);
    //}
    finally {
      /* Perform a complete shutdown of Derby only in cases where
       * it is being used as an embedded database, not when we have
       * connected to a Derby DB server.
       */
      if (! serverConnection)
        DerbyUtils.shutdown("data/books");
    }
  }
}
