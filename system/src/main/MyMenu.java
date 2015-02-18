package main;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MyMenu extends JMenu {
	public static final int FILE = 0, TOOLS = 1, HELP = 2;
	static final String[] menuName = {"File", "Tools", "Help"};
	Browser browser;
	JMenuItem[] menuItems;
	int type;
	
	final String[] FILE_LABELS = {"Backup", "Restore", "Exit"};
	final int BACKUP = 0, RESTORE=1, EXIT=2;
	
	final String[] TOOLS_LABELS = {"Preferences"};
	final int SETTINGS = 0;
	
	final String[] HELP_LABELS = {"About"};
	final int ABOUT=0;
		
	
	public MyMenu(Browser browser, int type){
		super(menuName[type]);
		this.browser = browser;
		this.type = type;
		init();
	}
	
	private void init() {
		String[] labels = null;
		switch (type){
		case FILE:
			labels = FILE_LABELS;
			break;
		case TOOLS:
			labels = TOOLS_LABELS;
			break;
		case HELP:
			labels = HELP_LABELS;
			break;
		}
		menuItems = new JMenuItem[labels.length];
		for (int i=0;i<menuItems.length;++i)
			menuItems[i] = new JMenuItem(labels[i]);
		arrangeMenu();
		addListeners();
	}

	private void arrangeMenu() {
		switch (type){
		case FILE:
			add(menuItems[BACKUP]);
			add(menuItems[RESTORE]);
			addSeparator();
			add(menuItems[EXIT]);
			break;
		case TOOLS:
			add(menuItems[SETTINGS]);
			break;
		case HELP:
			add(menuItems[ABOUT]);
			break;
		}
	}

	public void addListeners(){
		switch (type){
		case FILE:
			menuItems[BACKUP].addActionListener(Notify.NO_FUNCTION);;
			menuItems[RESTORE].addActionListener(Notify.NO_FUNCTION);
			menuItems[EXIT].addActionListener(Notify.SYSTEM_EXIT);
			System.out.println("File menu");
			break;
		case TOOLS:
			menuItems[SETTINGS].addActionListener(Notify.NO_FUNCTION);
			System.out.println("Tools Menu");
			break;
		case HELP:
			menuItems[ABOUT].addActionListener(new Notify(browser,Settings.AUTHOR_INFO));
			System.out.println("Help menu");
			break;
		}
	}
	
}
