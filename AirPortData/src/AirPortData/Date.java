package AirPortData;

import java.io.PrintWriter;
import java.util.Scanner;

import javax.xml.ws.Holder;

public class Date implements Comparable<Date> {
	private static int[] months= {31,28,31,30,31,30,31,31,30,31,30,31};

	private int day;
	private int month;
	private int year;

	public Date(int day,int month,int year) {
		setDay(day);
		setMonth(month);
		setYear(year);
	}

	public Date(Scanner s) {
		setDay(s.nextInt());
		setMonth(s.nextInt());
		setYear(s.nextInt());
		s.nextLine();
	}

	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Date))
			return false;
		Date temp = (Date)other;
		return day == temp.day && month == temp.month && year == temp .year;
	}
	public void save(PrintWriter pw) {
		pw.write(day+" "+ month+ " "+ year+"\n");
	}
	
//	@Override
//	public int compareTo(Date other) {
//		if(year < other.year)
//			return 1;
//		if(year== other.year) {
//			if(month < other.month)
//				return 1;
//			if(month == other.month)
//				if(day < other.day)
//					return 1;
//			if(day == day)
//				return 0;
//		}
//		return -1;
//	}

	@Override
	public int compareTo(Date other) {
		if(year==other.year) {
			if(month > other.month) {
				return 1;
			}
			else if(month < other.month) {
				return -1;
			}
			else {
				if(day > other.day) {
					return 1;
				}
				if(day < other.day) {
					return -1;
				}
			}
			return 0;
		}
		if(year > other.year) {
			return 1;
		}
		else if(year < other.year) {
			return -1;
		}
		return 0;
	}

	private void setYear(int year2) {
		if(year2<0 || year2>=2024) {
			this.year=2020;
		}
		else {
			this.year=year2;
		}
	}

	private void setMonth(int month2) {
		if(month2<0 || month2>12) {
			this.month=1;
		}
		else {
			this.month=month2;
		}
	}

	private void setDay(int day2) {
		if(day2>months[month] || day2<0) {
			this.day=1;
		}
		else {
			this.day=day2;
		}
	}

	@Override
	public String toString() {
		return day+"/"+month+"/"+year;
	}

}
