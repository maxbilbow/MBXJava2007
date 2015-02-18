package diary;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DiaryBlock extends JPanel{
public DiaryBlock(){
	add(new JScrollPane(new JLabel("Blank")));
}
}
