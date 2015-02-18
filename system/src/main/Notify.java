package main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Notify implements ActionListener {
	
	public static ActionListener NO_FUNCTION = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			defaultMessage(null);
		}
		
	};
	
	public static ActionListener SYSTEM_EXIT = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
		
	};
	
	public static MouseListener NO_FUNCTION_RIGHT_CLICK = new MouseListener() {
			
			public void mouseClicked(MouseEvent arg0) {
		if(arg0.getButton()==MouseEvent.BUTTON3) //TODO: help menu specific to location
			Notify.defaultMessage("Right Click");
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
};
	
	private static final String message = "This action currently has no function";
	String customMessage;
	Component component = null;
	
	
	public Notify(String customMessage){
		this.customMessage = customMessage;
	}
	
	public Notify(Component c, String customMessage){
		this.customMessage = customMessage;
		component = c;
	}
	
	

	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(component, customMessage ,null, JOptionPane.DEFAULT_OPTION);
	}

	public static void defaultMessage(String customMessage){
		String newMessage = message;
		if (customMessage!=null) newMessage += "\nAction Performed: " + customMessage;
		JOptionPane.showMessageDialog(null, newMessage ,null, JOptionPane.DEFAULT_OPTION);
	}
	
	public static ActionListener headerButton(final Browser browser, final int i){
		return new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					browser.setMainComponent(i);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//TODO Fixit!
				System.out.println(Settings.BUTTON_LABEL[i]);
			}
		};
	}
	
}
