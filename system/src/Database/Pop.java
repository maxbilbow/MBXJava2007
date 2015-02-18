package Database;

import javax.swing.JFrame;

import main.Screen;

public abstract class Pop extends JFrame{
	Screen main;
	public Pop(){
		super("Pop");
		main = new Screen(){

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
				
			}
			
		};
		init();
		//setSize(300,300);
		pack();
		setVisible(true);
	}

	public abstract void init();
}
