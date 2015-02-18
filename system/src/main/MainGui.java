package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import se20.coursework.provided.Event;


/**
 * User interface for Z-Club's TicketMaster application.
 *
 * @author Rick Denoff
 * @author SARAHANN HUDGELL
 * @version 0.3 (2006-10-29)
 */
public class MainGui extends Browser
{

  private static final int GUI_WIDTH = 700;
  private static final int GUI_HEIGHT = 600;

  private JTabbedPane screens;
  private LoginScreen loginScreen;
  private FinanceScreen eventScreen;
  private PaymentScreen paymentScreen;
  private int numTickets;
  private double amount;
  public Event selectedEvent;

  
  /**
   * Creates a TicketMasterGui.
   *
   * @param database Connection to Z-Club's database
   * @throws SQLException if a problem with the database connection occurs
   */
  public MainGui(Connection database) throws SQLException
  {
	  super(database);
    // Create screens

    loginScreen = new LoginScreen(this);
    eventScreen = new FinanceScreen(null);
    paymentScreen = new PaymentScreen(this);

     // Add screens as tabs of the main window

    screens = new JTabbedPane();
    screens.addTab("Start Here", loginScreen);
    screens.addTab("Choose Event", eventScreen);
    screens.addTab("Make Payment", paymentScreen);
 
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
	  select("Choose Event");
  }
  
  
  /**
   * Selects this TicketMasterGui's payment screen.
   */
  
  public void selectPaymentScreen()
  {
    
    getNumTickets();
    select("Make Payment");
    System.out.println(getAmountDue());

	  }
/**
 * Stores the chosen event in an Event object
 */
  
  public void setEvent()
  throws IllegalArgumentException
  {
    int chosenRow = eventScreen.finance.getSelectedRow();
        
    Date date = (Date) eventScreen.finance.getValueAt(chosenRow, 0);
    Time time = (Time) eventScreen.finance.getValueAt(chosenRow, 1);
    String performer = (String) eventScreen.finance.getValueAt(chosenRow, 2);
    String name = (String) eventScreen.finance.getValueAt(chosenRow, 3);
    double price =  Double.parseDouble((eventScreen.finance.getValueAt(chosenRow, 4)).toString());
    int tickets_left = Integer.parseInt(eventScreen.finance.getValueAt(chosenRow, 5).toString());
    numTickets = Integer.parseInt(eventScreen.tickets.getSelectedItem().toString());
    selectedEvent = new Event(1, date, time, performer, name, price, tickets_left);
   }
  
  /**
   * 
   * checks an event has been selected
   * @return True if an event is selected, false otherwise 
   */

  public boolean isEventChosen()
  {
    if (getSelectedEvent() != null)
    return true;
    else return false;
  }
  
  /**
   * gets selected event
   *
   * @return selectedEvent the selected event
   */

  public Event getSelectedEvent()
  {
    return selectedEvent; 
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

  /**
   * works out how much is due and formats it into £0.00
   */
  public void setAmountDue()
  {
    getSelectedEvent();
    double ticketPrice = getSelectedEvent().getTicketPrice();
    amount = ticketPrice * getNumTickets();
    paymentScreen.amountDue.setText(String.format("£%.2f", amount));
  }
  
/**
 * 
 * returns amount
 * @return amount amount due from the user 
 */  
  public double getAmountDue()
  {
    return amount;
  }

  /**
   *
   * @return numTickets number of tickets requested by the user
   */
  
public int getNumTickets() 
{
	return numTickets;
}

}
