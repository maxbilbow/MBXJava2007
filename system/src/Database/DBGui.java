package Database;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import main.Screen;

public class DBGui extends JFrame{
	private ResultSet results;
	private static final int NAME = 0, SQL = 1, FIRST_SQL = 2;
	private static final int MAX_COLS = 1000;
	private final String[][] sql = {
			{"Run","Create All" , "Drop All", "SELECT * FROM"},
			{""   ,"", DB.DROP_ALL, "SELECT * FROM member"}
	};
	private final int MAX_BUTTONS = sql[0].length;
	JPanel mainP, presetP, buttonsP;
	JButton[] buttons;
	JTextArea query;
	JTable resultTable;
	JScrollPane tableS;
	
	public DBGui(){
		super("Database Command");
		DB.open();
		init();
		createButtons();
		addListeners();
		arrange();
		setSize(500,500);
		setVisible(true);
	}

	private void addListeners() {
		buttons[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					//DB.open();
					results = DB.query(query.getText());
					updateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
			
		});
		
		
		for (int i=FIRST_SQL;i<MAX_BUTTONS;i++){
			System.out.println(i);
			final int temp = i;
			buttons[temp].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					writeToQuery(temp);
				}
			});
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void updateTable() throws SQLException {
		int cols = MAX_COLS;
		 Vector data = new Vector();
	      while (results.next()) {
	    	  Vector row = new Vector();
		        for (int i=1;i<=cols;i++){
		        	try {
		        		row.add(results.getObject(i));
		        		System.out.println(results.getString(i));
		        	} catch (Exception e){
		        		System.err.println(e+" :: End of results");
		        		cols = i-1;
		        		break;
		        	}
		        }
		        data.add(row);
	      }
	      Vector headings = new Vector();
	      	for(int i=0;i<((Vector)data.get(0)).size();++i)
	      		headings.add("Heading "+i);
	      resultTable = new JTable(data, headings){
	          @Override public boolean isCellEditable(int row, int col) { return false; }
	      };
	      //resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	      //resultTable.getTableHeader().setReorderingAllowed(false);
	      resultTable.setToolTipText("Don't think this is working");
	      new Pop(){
			@Override
			public void init() {
				main.setMainComponent(new JScrollPane(resultTable));
				main.repaint();
			}
	      };
	      tableS.setViewportView(resultTable);
	      setVisible(true);
	      
	      
	}
	
	@SuppressWarnings("serial")
	private void init() {
		buttons = new JButton[MAX_BUTTONS];
		mainP = new Screen(){

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
		presetP = new JPanel();
		buttonsP = new JPanel();
		query = new JTextArea();
		add(mainP);
		addWindowListener(
		        new WindowAdapter()
		        {
		          @Override public void windowClosing(WindowEvent windowEvent)
		          {
		            // Ensure that database shuts down cleanly when window closes

		            Window window = windowEvent.getWindow();
		            window.setVisible(false);
		            window.dispose();
		            DB.close();
		            System.exit(1);
		          }
		        }
		      );
		resultTable = new JTable(50,2);
	}

	private void arrange() {
		createPresetP();
		createButtonsP();
		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());
	
		main.add(new JScrollPane(query),BorderLayout.CENTER);

		tableS = new JScrollPane();
		
		main.add(tableS,BorderLayout.SOUTH);
		((Screen) mainP).setMainComponent(main);
		((Screen) mainP).setRightComponent(presetP);
		((Screen) mainP).setBottomComponent(buttonsP);
	}

	private void createButtonsP() {
		buttonsP.setLayout(new BorderLayout());
		buttonsP.add(buttons[0],BorderLayout.NORTH);
		//buttonsP.add(new JScrollPane(resultTable),BorderLayout.CENTER);
	}

	private void createPresetP() {
		presetP.setLayout(new BoxLayout(presetP,BoxLayout.Y_AXIS));
		for (int i=1;i<MAX_BUTTONS;i++)
			presetP.add(buttons[i]);
	}

	private void createButtons() {
		for (int i=0;i<MAX_BUTTONS;i++){
			buttons[i] = new JButton(sql[0][i]);
		}
	}
	
	private void writeToQuery(int i){
		query.setText(sql[SQL][i]);
	}
	
	public static void main(String[] args){
		new DBGui();
	}
}
