package AirPortData;

import java.io.PrintWriter;
import java.util.Scanner;

public class Flight implements Comparable<Flight> {

	private enum eStatus {onTime, delayed, landed, canceld};

	private String flightNumber,company,destenation,from,terminal;
	private Date date;
	private Time time,estematedTime;
	private eStatus status;

	public Flight(String flightNumber, String company, String destenation, String from, String terminal, Date date,Time time, Time estematedTime) {
		this.flightNumber = flightNumber;
		this.company = company;
		this.from = from;
		this.destenation = destenation;
		this.terminal = terminal;
		this.date = date;
		this.time = time;
		this.estematedTime = estematedTime;
	}

	public Flight(Scanner s) throws Exception {
		this.flightNumber=s.nextLine();
		this.company = s.nextLine();
		this.from = s.nextLine();
		this.destenation = s.nextLine();
		this.terminal = s.nextLine();
		this.date=new Date(s);
		this.time=new Time(s);
		this.estematedTime=new Time(s);
	}

	public void save(PrintWriter pw) {
		pw.println(flightNumber+"\n"+company+"\n"+from+"\n"+destenation+"\n"+terminal);
		date.save(pw);
		time.save(pw);
		estematedTime.save(pw);
	}

	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Flight)) {
			return false;
		}
		return (this.flightNumber.equalsIgnoreCase(((Flight)other).getFlightNumber()));
	}

	@Override
	public int compareTo(Flight other) {
		if(date.compareTo(other.date) == 0) {
			if(time.compareTo(other.time) == 1)
				return 1;
			if(time.compareTo(other.time) == 0)
				return 0;
			return -1;
		}
		if(date.compareTo(other.date) == 1)
			return 1;
		return 0;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public String getCompany() {
		return company;
	}

	public String getFrom() {
		return from;
	}

	public String getTerminal() {
		return terminal;
	}

	public Time getEstematedTime() {
		return estematedTime;
	}

	public void setStatus() {

	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}


	public String getDestenation() {  
		return destenation;
	}
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("Company: "+company+" Destenation: "+getDestenation()+" Date: "+getDate()
		+" Time: " + getTime()+" Estemated time: "+getEstematedTime()+
		" Flight number: "+getFlightNumber()+" Terminal: "+getTerminal());

		return sb.toString();
	}

}
