package main;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;


import javax.swing.BorderFactory;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.RepaintManager;

import sun.security.krb5.internal.crypto.c;



/**
 * Basis for screens 
 *
 * @author Max Bilbow
 * @version 
 */
public abstract class Screen extends JPanel implements Constants
{
	//RepaintManager repaint;
	Component top, bottom, left, right, main;
	Browser browser;
  /**
   * Creates a Screen.
   */
  public Screen()
  {
	//this.repaint = repaint;
	setLayout(new BorderLayout());
    init();
    arrangeComponents();
  }


  /**
   * Adds instructions on how to use this Screen.
   *
   * @param helpText Instructions to be added
   */
  public  void addHelp(String helpText)
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
  public void setMainComponent(JComponent component)//, RepaintManager repaint)
  {
	if (main != null)
		remove(main);
	main = component;
	//if (repaint!=null) 
    add(component, BorderLayout.CENTER);
    //repaint.markCompletelyDirty((JComponent) main);
    //repaint();
   //	refresh();
	
  }
  public void setLeftComponent(Component component){
	  if (left != null)
			remove(left);
		left = component;
		add(component, BorderLayout.WEST);
		//repaint.markCompletelyDirty((JComponent) left);
		
	}
	
  public void setRightComponent(Component component){
	  if (right != null)
			remove(right);
		right = component;
  	add(component, BorderLayout.EAST);
  	//repaint.markCompletelyDirty((JComponent) right);
  	//repaint();
	}
  
  public void setTopComponent(Component component){
	  if (top != null)
			remove(top);
		top = component;
  	add(component, BorderLayout.NORTH);
  	//repaint.markCompletelyDirty((JComponent) top);
  	//repaint();
  }
  
  public void setBottomComponent(Component component){
	  if (bottom != null)
			remove(bottom);
		bottom = component;
  	add(component, BorderLayout.SOUTH);
  	
  	//StaticStuff.repaint.markCompletelyDirty((JComponent) bottom);
  	//repaint();
  }

  private void refresh(){
	  	//repaint.markCompletelyDirty(this);
	    //repaint.paintDirtyRegions();  
	    //super.repaint();
	if (this.getParent() instanceof Browser) 
		this.getParent().setVisible(true);
  }
  /**
   * Clears this Screen of data.
   */
  public abstract void clearData();


  /**
   * Arranges this Screen's buttons.
   */
  protected abstract void arrangeComponents();
  
  public void p(Object o){
	  System.out.println(getClass()+" ::::: "+o);
  }
  protected abstract void init();
//  {
//    setLayout(new BorderLayout());
//
//    JPanel buttonPanel = new JPanel();
//    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
//    buttonPanel.add(continueButton);
//    buttonPanel.add(cancelButton);
//
//    add(buttonPanel, BorderLayout.SOUTH);
//  }
}
