package main;

import info.clearthought.layout.TableLayout;
import se20.coursework.provided.ServiceUnavailableException;

import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RepaintManager;

import se20.coursework.provided.Card;
import se20.coursework.provided.PaymentService;
import se20.coursework.provided.TicketPrinter;

public class PaymentScreen extends Screen {

  private static final String EVENT_HELP =
    "Select payment method and enter card details followed by expiry date";
  
  private TicketPrinter ticket;
  private MainGui gui;
  private JComboBox cardType;
  private PaymentService pay;
  public JLabel amountDue;
  public JTextField cardDetails;
  public JComboBox expiryDateMonth;
  public JComboBox expiryDateYear;
  public String cardNum;
/**
 *
 * 
 * Creates a PaymentScreen.
 *
 * @param gui
 * @throws SQLException
 */
  public PaymentScreen(MainGui gui)//, RepaintManager repaint)// throws SQLException
  {
	 // super(repaint);
    this.gui = gui;
    addHelp(EVENT_HELP);
    pay = new PaymentService();
    createForm();
  }
/**
 * Arranges componants on screen
 */

  private void createForm()
  {
    double b = 10;                    // border around components
    double g = 10;                    // gap between components
    double p = TableLayout.PREFERRED;

    double[][] size = {
        { b, p, p, p, p, p,p,p,p,b},   // columns
        { b, p, p,g, p,g, p,g, p,g, p,g, p, b }};       // rows


    TableLayout tl = new TableLayout(size);

    JPanel form = new JPanel();
    form.setLayout(tl);
    form.add(new JLabel("Amount due: "), "2,2,3,2");
    amountDue = new JLabel();
    form.add((amountDue), "4,2,5,2");
    String[] cards = {"Maestro", "MasterCard", "Visa"};
    form.add(new JLabel("Select card type: "), "2,4,3,4");
    cardType = new JComboBox(cards);
    form.add((cardType), "4,4,5,4");
    form.add(new JLabel("Card details: "), "2, 6, 3, 6 ");
    cardDetails = new JTextField(20);
    form.add(cardDetails, "4,6,5,6"); 
    form.add(new JLabel("Expiry date: "), "2,8,3,8");
    String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    String[] years = {"06", "07", "08", "09", "10", "11"};
    expiryDateMonth = new JComboBox(months);
    expiryDateYear = new JComboBox(years);
    form.add(expiryDateMonth, "4,8,5,8");
    form.add(expiryDateYear, "6,8,7,8");
    setMainComponent(form); 

  }



  public void start()
  {
    ticket.start();
  }
 /**
  * 
  * Check that all data is valid and print ticket if it is
  * @throws ServiceUnavailableException
  */

  private void makePayment()// throws ServiceUnavailableException
  {
    cardNum = cardDetails.getText();
 
    String type = cardType.getSelectedItem().toString();
    String date = expiryDateMonth.getSelectedItem().toString() + "/" + expiryDateYear.getSelectedItem().toString();
    Card card = new Card(type, cardNum, date);
    
    //if conditions of 16 or more characters or the string only containing numbers aren't met, a message pops up and the ticket isn't printed 
    if ((cardNum.length()<16))
      JOptionPane.showMessageDialog(gui, "The card details you have entered are wrong", "Card Number Invalid", JOptionPane.ERROR_MESSAGE);
    else
    {       
      //ticket = new TicketPrinter(gui, gui.getSelectedEvent(), gui.getNumTickets()); 
      try {
        if (pay.chargeTo(card, gui.getAmountDue()))
        {
          start();
          clearData();
          gui.selectLoginScreen();  
        }
        else
          JOptionPane.showMessageDialog(gui, "Error in Payment", "Card Number Invalid", JOptionPane.ERROR_MESSAGE);  
      }
      catch (ServiceUnavailableException error)
      {
        JOptionPane.showMessageDialog(gui, "Error in Payment", "Payment Service Busy\nPlease Try Again", JOptionPane.ERROR_MESSAGE);
      }
            
    }
  }
  /**
   * Clears date in the form 
   *
   */
  @Override
  public void clearData() 
  {
    cardType.setSelectedIndex(0);
    cardDetails.setText("");
    expiryDateMonth.setSelectedIndex(0);
    expiryDateYear.setSelectedIndex(0);
  }

@Override
protected void arrangeComponents() {
	// TODO Auto-generated method stub
	
}
@Override
protected void init() {
	// TODO Auto-generated method stub
	
}

}
