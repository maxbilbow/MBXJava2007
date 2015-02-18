package main;

import javax.swing.JList;

public class AlertScreen extends Screen {
	JList todo;
	private static final String HELP_TEXT = "TO DO";
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
		todo = new JList();
		setMainComponent(todo);
		addHelp(HELP_TEXT);
	}

}
