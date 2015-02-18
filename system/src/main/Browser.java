package main;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.RepaintManager;

import Database.DBGui;

import diary.Diary;


@SuppressWarnings("serial")
public class Browser extends JFrame implements Constants{
	JMenuBar menu;
	MyMenu file, edit, tools, help;
	BrowserScreen browser;
	//RepaintManager repaint;
	
	private static final int GUI_WIDTH = 700;
	private static final int GUI_HEIGHT = 600;
	
	private static final boolean password = false;
	
	LeftTree leftTree;
	Alerts alerts;
	Header header;
	Footer footer;
	Main main;
	LoginScreen login;
	Diary diary;
	Finance finance;
	
	Connection database;
	public Browser(Connection database) throws SQLException{
		this.database = database;
		leftTree = new LeftTree(this);
	    alerts = new Alerts(this);
	    header = new Header(this);
	    login = new LoginScreen(this);
	    footer = new Footer(this);
	    finance = new Finance(this);
	    //Main parts
	    main = new Main(this);
	    diary = new Diary(this);
	    
	    //repaint = new RepaintManager();
		init();
		RepaintManager.currentManager(this);
		addMouseListener(Notify.NO_FUNCTION_RIGHT_CLICK);
		new DBGui();
	}
	private void initA(){
		browser.setMainComponent(login);
	}
	
	public void initB(){
		createMenuBar();
		setJMenuBar(menu);
	    
	    
	    //footer = new Footer(this);
	    
	    browser.setMainComponent(new JScrollPane(main.getGui()));
	    browser.setTopComponent(header.getGui());
	    browser.setBottomComponent(footer.getGui());
	    browser.setLeftComponent(new JScrollPane(leftTree.getGui()));
	    browser.setRightComponent(new JScrollPane(alerts.getGui()));
	   
	    repaint();
	    browser.repaint();
	    }
	
	private void init(){
		browser = new BrowserScreen();
		add(browser);
		setSize(GUI_WIDTH, GUI_HEIGHT);
	    
		if (password) initA();
		else initB ();
		
	    setVisible(true);
	}  
	public void setMainComponent(){
	    browser.setMainComponent(new JScrollPane(new JLabel("bum")));
		browser.repaint();
	}
	

	public void setMainComponent(int i) throws SQLException{
		switch (i){
		case Settings.MAIN:
			browser.setMainComponent(main.getGui());
			break;
		case Settings.DIARY:
			browser.setMainComponent(diary.getGui());
			break;
		case Settings.FINANCE:
			browser.setMainComponent(finance.getGui());
			break;
		case Settings.CONTACTS:
			Notify.defaultMessage("Access Address Book");
			break;
		case Settings.FIVE:
			//Notify.defaultMessage(null);
			header.toggleButton();
			break;
		}
		setVisible(true);
	}
		
	private void createMenuBar() {
		menu = new JMenuBar();
		
		file = new MyMenu(this, MyMenu.FILE);
		tools = new MyMenu(this, MyMenu.TOOLS);
		help = new MyMenu(this, MyMenu.HELP);
	
		menu.add(file);
		menu.add(tools);
		menu.add(help);
	}
	
	public Connection getDatabase(){
		return database;
	}
	
	public void clearData() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
}
