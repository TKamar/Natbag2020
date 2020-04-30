package AirPortData;

import java.io.PrintWriter;
import java.util.Scanner;

public class Time implements Comparable<Time> {

	private int hours;
	private int minutes;

	public Time(int hours,int minutes) throws Exception {
		setHours(hours);
		setMinutes(minutes);
	}

	public Time(Scanner s) throws Exception {
		setHours(s.nextInt());
		setMinutes(s.nextInt());
		s.nextLine();//clean the buffer
	}

	public void save(PrintWriter pw) {
		pw.write(hours+" "+minutes+"\n");
	}

	private void setMinutes(int minutes) throws Exception {
		if(minutes>60 || minutes<0) {
			throw new Exception("minutes have to be between 0-60");
		}
		this.minutes=minutes;
	}

	private void setHours(int hours) throws Exception {
		if(hours>23 || hours<0) {
			throw new Exception("minutes have to be between 0-60");
		}
		this.hours=hours;

	}
	@Override
	public int compareTo(Time other) {
		if(hours==other.hours) {
			if(minutes>other.minutes) {
				return 1;
			}
			else if(minutes<other.minutes) {
				return -1;
			}
		}
		if(hours>other.hours) {
			return 1;
		}
		if(hours<other.hours) {
			return -1;
		}
		return 0;
	}
	
	private String printHours() {
		if(hours>=0 && hours <=9)
			return "0"+hours;
		return ""+hours;
	}
	
	@Override
	public String toString() {
		return printHours()+":"+minutes;
	}
}
