package diary;



import java.util.Calendar;
import java.util.StringTokenizer;


/** 
 * A class representing a calendar date.
 *
 * @author Nick Efford
 * @adapted for SE24 work.
 * @version 1.5 (2005-09-30)
 */

public class Date implements Comparable {


  // Some useful constants

  public static final int JANUARY   = 1;
  public static final int FEBRUARY  = 2;
  public static final int MARCH     = 3;
  public static final int APRIL     = 4;
  public static final int MAY       = 5;
  public static final int JUNE      = 6;
  public static final int JULY      = 7;
  public static final int AUGUST    = 8;
  public static final int SEPTEMBER = 9;
  public static final int OCTOBER   = 10;
  public static final int NOVEMBER  = 11;
  public static final int DECEMBER  = 12;

  public static final int[] DAYS_IN_MONTH = {
    0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
  };
  
  public static final String[] MONTH = { "", "January" , "February", "March", "April", "May", "June" , "July", 
	  "August", "September", "October", "November", "December" 
	  };
  

  // Instance variables

  private int day;
  private int month;
  private int year;


  /** 
   * Creates a Date object initialised to today's date.
   */

  public Date()
  {
    Calendar now = Calendar.getInstance();
    day = now.get(Calendar.DAY_OF_MONTH);
    month = now.get(Calendar.MONTH) + 1;
    year = now.get(Calendar.YEAR);
  }

  
  public String getDate(){
		return day + " " + MONTH[month] + " " + year;
	  }
  
  public String getMonthS(){
		return MONTH[month];
	  }
  /** 
   * Creates a Date object initialised to the specified date.
   *
   * <ul>
   *   <li>The specified year must be no less than 1.</li>
   *   <li>The specified month must be in the range 1-12 inclusive.</li>
   *   <li>The allowed range for day varies, depending on the month,
   *       but the day may never lie outside the range 1-31.</li>
   * </ul>
   * 
   * @param theDay Day component of desired date
   * @param theMonth Month component of desired date
   * @param theYear Year component of desired date
   * @throws IllegalArgumentException if day, month or year are invalid
   */

  public Date(int theDay, int theMonth, int theYear)
  {
    setDayMonthYear(theDay, theMonth, theYear);
  }


  /** 
   * Creates a Date object initialised to the specified date.
   * The latter is supplied as a string approximating the ISO 8601
   * standard of YYYY-MM-DD.  (It is not an error to have less than
   * four digits for the year, or just a single digit for month
   * or day.  The separating hyphens are required.)
   * 
   * @param dateString String specifying the desired date
   * @throws IllegalArgumentException if string isn't in the required
   *   ISO format or values of day, month or year are invalid
   */

  public Date(String dateString)
  {
    StringTokenizer parser = new StringTokenizer(dateString, "-");
    if (parser.countTokens() != 3)
      throw new IllegalArgumentException("invalid date string");

    try {
      int y = Integer.parseInt(parser.nextToken());
      int m = Integer.parseInt(parser.nextToken());
      int d = Integer.parseInt(parser.nextToken());
      setDayMonthYear(d, m, y);
    }
    catch (NumberFormatException error) {
      // Translate NumberFormatException into IllegalArgumentException
      throw new IllegalArgumentException("invalid date string");
    }
  }


  /**
   * Helper method to check and set day, month and year fields.
   */

  private void setDayMonthYear(int d, int m, int y)
  {
    // Because the range of valid days varies depending on both
    // month and year, we need to deal with these first

    if (m < JANUARY || m > DECEMBER)
      throw new IllegalArgumentException("invalid month");

    if (y < 1)
      throw new IllegalArgumentException("invalid year");

    int prevMonth = month;
    int prevYear = year;
    month = m;
    year = y;

    if (d < 1 || d > getLastDay()) {
      // Roll back object state before throwing the exception
      month = prevMonth;
      year = prevYear;
      throw new IllegalArgumentException("invalid day");
    }

    day = d;
  }


  /**
   * Helper method returning last day of this Date's month.
   */

  private int getLastDay()
  {
    int thisMonth = getMonth();
    if (thisMonth == FEBRUARY && isLeapYear())
      return 29;
    else
      return DAYS_IN_MONTH[thisMonth];
  }


  /** 
   * @return Day component of this Date
   */

  public int getDay()
  {
    return day;
  }


  /** 
   * @return Month component of this Date
   */

  public int getMonth()
  {
    return month;
  }


  /** 
   * @return Year component of this Date
   */

  public int getYear()
  {
    return year;
  }


  /** 
   * Queries whether this Date falls in a leap year.
   * 
   * @return True if this is a leap year, false otherwise
   */

  public boolean isLeapYear()
  {
    int thisYear = getYear();

    if (thisYear % 400 == 0)
      return true;
    else if (thisYear % 4 == 0 && thisYear % 100 != 0)
      return true;
    else
      return false;
  }


  /** 
   * Advances this Date by one day.
   */

  public void advance()
  {
    int d = getDay(), m = getMonth(), y = getYear();

    ++d;
    if (d > getLastDay()) {
      ++m;
      if (m > DECEMBER) {
        m = JANUARY;
        ++y;
      }
    }

    day = d;
    month = m;
    year = y;
  }


  /** 
   * Advances this Date by a given number of days.
   * 
   * @param days Number of days by which date should be advanced
   */

  public void advance(int days)
  {
    for (int i = 0; i < days; ++i)
      advance();
  }


  /**
   * Tests for equality of state with another Date object.
   *
   * @param other Another object
   * @return True if other object is a Date in same state as this one,
   *  false otherwise
   */

  @Override public boolean equals(Object other)
  {
    if (other == this)
      return true;
    else if (! (other instanceof Date))
      return false;
    else {
      Date otherDate = (Date) other;
      return getDay() == otherDate.getDay() &&
             getMonth() == otherDate.getMonth() &&
             getYear() == otherDate.getYear();
    }
  }

   public String toString()
   {
     return getDay() +"-" + getMonth() + "-" + getYear();
   }

  /**
   * Compares this Date object with another.
   *
   * @param other Another object
   * @return -1 if this Date comes before the other, +1 if it comes
   *  after and 0 if both objects represent the same date
   * @throws ClassCastException if the other object is not a Date
   */

  public int compareTo(Object other)
  {
    Date otherDate = (Date) other;

    if (getYear() < otherDate.getYear())
      return -1;
    else if (getYear() > otherDate.getYear())
      return 1;

    if (getMonth() < otherDate.getMonth())
      return -1;
    else if (getMonth() > otherDate.getMonth())
      return 1;

    if (getDay() < otherDate.getDay())
      return -1;
    else if (getDay() > otherDate.getDay())
      return 1;

    return 0;
  }


}
