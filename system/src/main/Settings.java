package main;


public interface Settings{
	
	public static final int MAX_HEADER_BUTTONS = 5;
	public static final String BUTTON_LABEL[] = { "Home", "Diary", "Finance", "Contacts", "Five" };
	public static String[] BUTTON_HELP = { "Home","Diary","Finance", "Contacts", "five" };
	public static final int MAIN = 0, DIARY = 1, FINANCE = 2, CONTACTS=3, FIVE = 4;
	public static final String AUTHOR_INFO = "Created by Max Bilbow\n" +
											"Tel: 07739 832 170\n" +
											"email: max@liquidsatisfaction.co.uk";
}
