package se20.coursework;


import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFrame;



/**
 * A ticket-printing application for Z-Club events.
 *
 * @author Rick Denoff
 * @version 1.0 (2006-10-29)
 */
public class TicketMaster
{

  private static final String CONFIG_FILE = "config";


  /**
   * Application entry point.
   *
   * @param args Command-line arguments (unused)
   */
  public static void main(String[] args)
  {
    try {
      // Load configuration from a file

      FileInputStream input = new FileInputStream(CONFIG_FILE);
      Properties props = new Properties();
      props.load(input);
      input.close();

      // Connect to database

      String driver = props.getProperty("driver");
      final String databaseUrl = props.getProperty("url");
      System.setProperty("jdbc.drivers", driver);
      Connection database = DriverManager.getConnection(databaseUrl);

      // Create GUI and set up event handling for the window

      JFrame gui = new TicketMasterGui(database);
      gui.addWindowListener(
        new WindowAdapter()
        {
          @Override public void windowClosing(WindowEvent windowEvent)
          {
            // Ensure that database shuts down cleanly when window closes

            Window window = windowEvent.getWindow();
            window.setVisible(false);
            window.dispose();
            shutdown(databaseUrl);
            System.exit(0);
          }
        }
      );
    }
    catch (Exception error) {
      error.printStackTrace();
      System.exit(1);
    }
  }


  /**
   * Shuts down the Derby database with the given connection URL
   *
   * @param url Connection URL of database to be shut down
   */
  public static void shutdown(String url)
  {
    try {
      DriverManager.getConnection(String.format("%s;shutdown=true", url));
      System.err.println("Problem with shutdown!");
    }
    catch (SQLException error) {
      // Successful shutdown always results in an SQLException
      System.out.println("Shutdown completed successfully.");
    }
  }
}
