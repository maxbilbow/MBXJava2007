package diary;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.Control;
import main.Screen;
import main.HourBlock;

public class DiaryScreen extends Screen {
	public static final int DAY = 0, WEEK = 1, MONTH = 2, YEAR = 3;
	
	JPanel main;
	DiaryBlock[] diaryBlock;
	Control control;
	Date date;
	JLabel header;
	@Override
	protected void arrangeComponents() {
		setTopComponent(header);
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void init() {
		p("start init");
		// TODO Auto-generated method stub
		date = new Date();
		main = new JPanel();
		header = new JLabel("head");
		setMainComponent(new JScrollPane(main));
		setBottomComponent(new JLabel("Arrows left and right")); //TODO
		p("finish init");
	}
	
	public void setLayout(int i){
		p("Enters setLayout, number: "+i);
		switch (i){
		case DAY:
			setDayLayout();
			break;
		case WEEK:
			setWeekLayout();
			break;
		case MONTH:
			setMonthLayout();
			break;
		case YEAR:
			setYearLayout();
			break;
		}
	}

	private void setYearLayout() {
		header.setText(String.valueOf(date.getYear()));
		main.setLayout(new GridLayout(3,4));
		
		diaryBlock = new MonthBlock[12];
		for (int i=0;i<diaryBlock.length;++i){
			diaryBlock[i] = new MonthBlock(); //TODO: Replace with next line
			//dateBlock[i] = getDateBlock();
			main.add(diaryBlock[i]);
		}

		
	}

	private void setMonthLayout() {
		header.setText(date.getMonthS()+" "+ date.getYear());
		main.setLayout(new GridLayout(5,7));
				
		diaryBlock = new DayBlock[31];
		int days = Date.DAYS_IN_MONTH[date.getMonth()];
		if ((date.getMonth()==Date.FEBRUARY)&&(date.isLeapYear()))
				days += 1;
		for (int i=0;i<31;++i)
				diaryBlock[i] = (i<days)? new DayBlock() : new DiaryBlock();	
	}

	private void setWeekLayout() {
		header.setText(date.getMonthS()+" "+ date.getYear());
		main.setLayout(new GridLayout(7,1));
		
		diaryBlock = new DayBlock[7];
		for (int i=0;i<7;++i)
			diaryBlock[i] = new DayBlock();

	}

	private void setDayLayout() {
		diaryBlock = new HourBlock[24];
		for (int i=0;i<31;++i)
			diaryBlock[i] = (i<Date.DAYS_IN_MONTH[date.getMonth()])? new HourBlock() : new DiaryBlock();	

	}

}
