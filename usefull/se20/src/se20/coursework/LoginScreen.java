package se20.coursework;


import info.clearthought.layout.TableLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

  private TicketMasterGui gui;
  private PreparedStatement authenticationQuery;
  private JTextField memberIdField;
  private JTextField memberPinField;


  /**
   * Creates a LoginScreen.
   *
   * @param gui User interface to which the LoginScreen belongs
   * @param database Connection to Z-Club's database
   * @throws SQLException if a problem with the database connection occurs
   */
  public LoginScreen(TicketMasterGui gui, Connection database)
   throws SQLException
  {
    this.gui = gui;

    authenticationQuery = database.prepareStatement(AUTH_SQL);

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

    addMainComponent(form);
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
      gui.selectEventScreen();
    }
    else {
      clearData();
      JOptionPane.showMessageDialog(
       gui, LOGIN_ERROR, "Error", JOptionPane.ERROR_MESSAGE);
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
}
