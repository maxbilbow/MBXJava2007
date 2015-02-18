package main;


import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

	/**
	 * Login screen for Z-Club members wishing to purchase tickets.
	 *
	 * @author Rick Denoff
	 * @version 1.0 (2006-10-29)
	 */
public class FinanceScreen extends Screen {

	  private static final String EVENT_HELP =
	    "Choose event from table and select how many tickets you want from the drop down menu";
	  
	  
	  //private Browser browser;
	 Vector data;
    public JComboBox tickets;
    public JTable finance;
	  /**
	   * Creates the text fields used by this LoginScreen.
	   */
//connects to the database, adds the cancel and continue buttons to the 
// bottom of the screen and the help at the top
public FinanceScreen(Vector data)
//throws SQLException
    {
        super();
        this.data=data;
    }

//gets all the values from the database for the table of events 
@SuppressWarnings("unchecked") public void financeTable()
{
    Vector headings = new Vector();
    headings.add("Date");
    headings.add("Details");
    headings.add("IN/OUT");
    headings.add("Amount");

    
    finance = new JTable(data, headings) {
      @Override public boolean isCellEditable(int row, int col) { return false; }
    };
    finance.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    finance.getTableHeader().setReorderingAllowed(false);
    //StaticStuff.repaint.markCompletelyDirty(finance);
}


/**
 * Creates the form used by this LoginScreen.
 * Arranges the components on the screen
 * @throws SQLException
 */

private void createForm() 
{
  double b = 10;                    // border around components
  double g = 10;                    // gap between components
  double f = TableLayout.FILL;
  double p = TableLayout.PREFERRED;

  double[][] size = {
      { b, 20, p, 20, b},   // columns
      { b, p, g, p, p,b }       // rows
  };
  
  JPanel form = new JPanel();
  form.setLayout(new BorderLayout());
  financeTable();
  form.add(new JScrollPane(finance), BorderLayout.CENTER);
  
  JPanel bottom = new JPanel();
  bottom.add(new JLabel("Number of tickets required: "), "2,4,r,c");
  String[] comboData = {"1", "2", "3", "4"};
  tickets = new JComboBox(comboData);
  bottom.add((tickets), "2,5,r,c");
  
  form.add((bottom), BorderLayout.SOUTH);
  setMainComponent(form); 
  
}

    /**
     * Clears date in the form 
     *
     */
	@Override
	public void clearData() 
  {
    finance.clearSelection();
    tickets.setSelectedIndex(0);
	}


	@Override
	protected void arrangeComponents() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void init() {
		// TODO Auto-generated method stub
		addHelp(EVENT_HELP);
	     	

			createForm();

		
		
	}
}


