package se20.coursework;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;



/**
 * Basis for screens used in the TicketMaster application.
 *
 * @author Rick Denoff
 * @version 1.0 (2006-10-29)
 */
public abstract class Screen extends JPanel
{

  private JButton continueButton;
  private JButton cancelButton;


  /**
   * Creates a Screen.
   */
  public Screen()
  {
    createButtons();
    arrangeButtons();
  }


  /**
   * Adds instructions on how to use this Screen.
   *
   * @param helpText Instructions to be added
   */
  public void addHelp(String helpText)
  {
    JTextArea help = new JTextArea(helpText);
    help.setOpaque(false);
    help.setEditable(false);
    help.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    add(help, BorderLayout.NORTH);
  }


  /**
   * Adds the main component to this Screen (typically a form of some kind).
   *
   * @param component Component to be added
   */
  public void addMainComponent(JComponent component)
  {
    add(component, BorderLayout.CENTER);
  }


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
   * Clears this Screen of data.
   */
  public abstract void clearData();


  /**
   * Creates the buttons displayed at the foot of this Screen.
   */
  private void createButtons()
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
  private void arrangeButtons()
  {
    setLayout(new BorderLayout());

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    buttonPanel.add(continueButton);
    buttonPanel.add(cancelButton);

    add(buttonPanel, BorderLayout.SOUTH);
  }
}
