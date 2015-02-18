package main;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.Popup;

import Database.ToolbarSettings;

public class HeaderScreen extends Screen {
	
	Boolean[] buttonOn;
	JButton[] buttons;
	JPanel buttonPanel;
	JToolTip[] buttonHelp;
	static int buttonCount;
	@Override
	protected void arrangeComponents() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void init() {
		addHelp("Buttons for navigation");
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		initButtons();
		initButtonHelp();		
		addButtons();
		setBottomComponent(buttonPanel);
		
		
	}
	
	private void addButtons() {
		for(int i=0;i<buttonOn.length;++i)
			if (buttonOn[i]) buttonPanel.add(buttons[i]);
	}

	private void initButtons(){
		buttons = new JButton[Settings.MAX_HEADER_BUTTONS];
		for (int i=0;i<Settings.MAX_HEADER_BUTTONS;++i){
			buttons[i] = new JButton(Settings.BUTTON_LABEL[i]);
			buttons[i].setToolTipText(Settings.BUTTON_HELP[i]);
		}
		buttonOn = new Boolean[Settings.MAX_HEADER_BUTTONS];
		enableAllButtons();
		
		//initButtonHelp();
		
		
	}
	
	private void initButtonHelp(){
		buttonHelp = new JToolTip[Settings.MAX_HEADER_BUTTONS];
		for (int i=0;i<Settings.MAX_HEADER_BUTTONS;++i){
			buttonHelp[i] = new JToolTip();
			buttonHelp[i].setTipText(Settings.BUTTON_HELP[i]);
			buttons[i].setToolTipText(Settings.BUTTON_HELP[i]);
		}
			
	}
	
	public void enableAllButtons(){
		for (int i=0;i<Settings.MAX_HEADER_BUTTONS;++i)
			buttonOn[i]=true;
	}
	
	public void addButtonListener(int i, ActionListener a){
		buttons[i].addActionListener(a);
	}

	public void enableButtons(ToolbarSettings toolbarSettings) {
		for (int i=0;i<Settings.MAX_HEADER_BUTTONS;++i)
			buttonOn[i]=toolbarSettings.isButtonUsed(i);
		buttonPanel.removeAll();
		addButtons();
	}
}
