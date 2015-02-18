package main;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.RepaintManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainScreen extends Screen {
//	public MainScreen(RepaintManager repaint) {
//		super(repaint);
//		// TODO Auto-generated constructor stub
//	}

	JTabbedPane tabs;
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
		// TODO Auto-generated method stub
		tabs = new JTabbedPane();
		tabs.addTab("Tab 1",new JTextArea("Hello"));
		tabs.addTab("Tab 2",new JLabel("Bum"));
		
		
		
		setMainComponent(tabs);
	}

}
