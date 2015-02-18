package main;

import javax.swing.JLabel;
import javax.swing.JTree;

public class LeftTreeScreen extends Screen{
	JTree tree;
	
	
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
		tree = new JTree();
		tree.add(new JLabel("Test Element"));
		setMainComponent(tree);
	}

}
