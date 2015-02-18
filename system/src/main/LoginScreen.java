package main;


import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



/**
 * Login screen for Z-Club members wishing to purchase tickets.
 *
 * @author Rick Denoff
 * @version 1.0 (2006-10-29)
 */
public class LoginScreen extends Screen
{

  private static final String AUTH_SQL =
    "select * from Member where id=? and pin=?";

  private static final String LOGIN_HELP =
    "Please enter your Z-Club member ID and PIN.\n"
  + "Then press the 'Continue' button to choose an event.";

  private static final String LOGIN_ERROR =
    "Login failed!\nPlease check your ID and PIN and try again.";

  private JButton continueButton;
  private JButton cancelButton;
  /**
   * Specifies how clicks on this Screen's Continue button are handled.
   *
   * @param listener Object that will handle button clicks
   */
  public void setContinueButtonHandler(ActionListener listener)
  {
    continueButton.addActionListener(listener);
  }


  /**
   * Specifies how clicks on this Screen's Cancel button are handled.
   *
   * @param listener Object that will handle button clicks
   */
  public void setCancelButtonHandler(ActionListener listener)
  {
    cancelButton.addActionListener(listener);
  }


  /**
   * Creates the buttons displayed at the foot of this Screen.
   */
  protected void createButtons()
  {
    Font buttonFont = new Font("Sans", Font.BOLD, 16);

    continueButton = new JButton("Continue");
    continueButton.setFont(buttonFont);

    cancelButton = new JButton("Cancel");
    cancelButton.setFont(buttonFont);
  }


  /**
   * Arranges this Screen's buttons.
   */
  protected void arrangeComponents()
  {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    buttonPanel.add(continueButton);
    buttonPanel.add(cancelButton);
    setBottomComponent(buttonPanel);
  }
  private Browser browser;
  private PreparedStatement authenticationQuery;
  private JTextField memberIdField;
  private JTextField memberPinField;


  
 /* public LoginScreen(MainGui gui, Connection database)
   throws SQLException
  {
    this.browser = gui;

    authenticationQuery = database.prepareStatement(AUTH_SQL);

    addHelp(LOGIN_HELP);
    createFields();
    createForm();
    configureButtons();
  }*/

  /**
   * Creates a LoginScreen.
   *
   * @param gui User interface to which the LoginScreen belongs
   * @param database Connection to Z-Club's database
   * @throws SQLException if a problem with the database connection occurs
   */
  public LoginScreen(Browser browser) throws SQLException {
	  //super(null);//TODO: Maybe
	  this.browser = browser;
	  authenticationQuery = browser.getDatabase().prepareStatement(AUTH_SQL);

	    addHelp(LOGIN_HELP);
	    createFields();
	    createForm();
	    configureButtons();
}


/**
   * Creates the text fields used by this LoginScreen.
   */
  private void createFields()
  {
    Font fieldFont = new Font("Sans", Font.PLAIN, 14);

    memberIdField = new JTextField(12);
    memberIdField.setFont(fieldFont);

    memberPinField = new JPasswordField(8);
    memberPinField.setFont(fieldFont);
  }


  /**
   * Creates the form used by this LoginScreen.
   */
  private void createForm()
  {
    double b = 10;                    // border around components
    double g = 10;                    // gap between components
    double f = TableLayout.FILL;
    double p = TableLayout.PREFERRED;

    double[][] size = {
        { b, f, p, g, p, p, f, b },   // columns
        { b, f, p, g, p, f, b }       // rows
    };

    JPanel form = new JPanel();
    form.setLayout(new TableLayout(size));

    form.add(new JLabel("Member ID"), "2,2,r,c");
    form.add(memberIdField, "4,2,5,2");
    form.add(new JLabel("Member PIN"), "2,4,r,c");
    form.add(memberPinField, "4,4");

    setMainComponent(form);
  }


  /**
   * Configures this LoginScreen's Continue and Cancel buttons.
   */
  private void configureButtons()
  {
    setContinueButtonHandler(
      new ActionListener()
      {
        public void actionPerformed(ActionEvent actionEvent)
        {
          login();
        }
      }
    );

    setCancelButtonHandler(
      new ActionListener()
      {
        public void actionPerformed(ActionEvent actionEvent)
        {
          clearData();
        }
      }
    );
  }


  /**
   * Attempts to login by authenticating against the members database.
   */
  public void login()
  {
    if (authenticated()) {
      clearData();
      browser.initB();
    }
    else {
      clearData();
      JOptionPane.showMessageDialog(
       browser, LOGIN_ERROR, "Error", JOptionPane.ERROR_MESSAGE);
    }
  }


  /**
   * Tests whether the member ID and PIN supplied in the form are valid.
   *
   * @return True if the member details are valid, false otherwise
   */
  public boolean authenticated()
  {
    try {
      authenticationQuery.setString(1, memberIdField.getText());
      authenticationQuery.setString(2, memberPinField.getText());
      ResultSet results = authenticationQuery.executeQuery();
      if (results.next())
        return true;
      else
        return false;
    }
    catch (SQLException error) {
      error.printStackTrace();
      return false;
    }
  }


  /**
   * Clears this LoginScreen of member details.
   */
  @Override public void clearData()
  {
    memberIdField.setText("");
    memberPinField.setText("");
  }


@Override
protected void init() {
	createButtons();
}



}
