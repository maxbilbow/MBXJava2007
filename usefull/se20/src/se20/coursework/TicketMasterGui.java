package se20.coursework;


import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;



/**
 * User interface for Z-Club's TicketMaster application.
 *
 * @author Rick Denoff
 * @author YOUR NAME HERE
 * @version 0.3 (2006-10-29)
 */
public class TicketMasterGui extends JFrame
{

  private static final int GUI_WIDTH = 580;
  private static final int GUI_HEIGHT = 460;

  private JTabbedPane screens;
  private LoginScreen loginScreen;

  // TODO: Add instance variable to represent events screen
  // TODO: Add instance variable to represent payment screen

  // TODO: Add instance variable to represent chosen event
  // TODO: Add instance variable to represent number of tickets required

  /**
   * Creates a TicketMasterGui.
   *
   * @param database Connection to Z-Club's database
   * @throws SQLException if a problem with the database connection occurs
   */
  public TicketMasterGui(Connection database) throws SQLException
  {
    super("Z-Club TicketMaster");

    // Create screens

    loginScreen = new LoginScreen(this, database);

    // TODO: Create objects representing events screen and payment screen

    // Add screens as tabs of the main window

    screens = new JTabbedPane();
    screens.addTab("Start Here", loginScreen);
    // TODO: Add tab for events screen, labelled "Choose Event"
    // TODO: Add tab for payment screen, labelled "Make Payment"
    add(screens);

    // Make GUI visible with login screen active

    selectLoginScreen();
    setSize(GUI_WIDTH, GUI_HEIGHT);
    setVisible(true);
  }


  /**
   * Selects this TicketMasterGui's login screen.
   */
  public void selectLoginScreen()
  {
    select("Start Here");
  }


  /**
   * Selects this TicketMasterGui's event choosing screen.
   */
  public void selectEventScreen()
  {
    // TODO: Add code to handle transition from login screen to event screen
  }


  /**
   * Selects this TicketMasterGui's payment screen.
   */
  public void selectPaymentScreen()
  {
    // TODO: Add code to handle transition from event screen to payment screen
  }


  /**
   * Selects one screen by name and disables the others.
   *
   * @param screenName Name of screen to be selected
   */
  private void select(String screenName)
  {
    // Identify and select desired screen

    int index = screens.indexOfTab(screenName);
    screens.setSelectedIndex(index);
    screens.setEnabledAt(index, true);

    // Disable all other screens

    for (int i = 0; i < screens.getTabCount(); ++i) {
      if (i != index)
        screens.setEnabledAt(i, false);
    }
  }


  // TODO: Add accessor method for 'chosen event' instance variable
  // TODO: Add accessor method for 'number of tickets' instance variable
  // TODO: Add method that sets chosen event and number of tickets

}
