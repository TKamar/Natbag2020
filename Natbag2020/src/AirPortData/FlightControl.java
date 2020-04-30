package AirPortData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class FlightControl {

	private Flight [] allFlights;
	private int allFlightsPointer;
	
	public FlightControl(Flight [] allFlights) {
		this.allFlights=allFlights;
		setPointer();
		sort();
	}
	
	public FlightControl() {//default c'tor
		allFlights=new Flight[1];
	}
	
	private void sort() {
			Arrays.sort(allFlights, new CompareByDate());
			Arrays.sort(allFlights, new CompareByTime());
	}

	public void addFlight(Flight f1) {
		if(allFlightsPointer<allFlights.length) {
			allFlights[allFlightsPointer++]=f1;
			sort();
		}
		else {
			this.allFlights=Arrays.copyOf(allFlights, allFlights.length*2);
			allFlights[allFlightsPointer++]=f1;
			sort();
		}
	}
	
	private void setPointer() {
		for (int i = 0; i < allFlights.length; i++) {
			if(allFlights[i]!=null) {
				allFlightsPointer++;
			}
		}
	}

	public FlightControl(String fileName) throws Exception {
		Scanner s=new Scanner(new File(fileName));
		this.allFlights=new Flight[s.nextInt()];
		s.nextLine();
		s.nextLine();//clean the buffer
		for (int i = 0; i < allFlights.length; i++) {
			allFlights[i]=new Flight(s);
		}
		s.close();
		setPointer();
		sort();
	}
	
	public void save(String fileName) throws FileNotFoundException {
		PrintWriter pw=new PrintWriter(new File(fileName));
		pw.println(allFlightsPointer+"\n");
		for (int i = 0; i < allFlightsPointer; i++) {
			allFlights[i].save(pw);
			pw.println();
		}
		pw.close();
	}

	public String showAllDepartures() {
		StringBuilder sb= new StringBuilder("All departures\n");
		for (int i = 0; i < allFlightsPointer; i++) {
			if(!(allFlights[i].getDestenation().equalsIgnoreCase("TLV"))){
			sb.append(allFlights[i]+"\n");
			}
		}
		return sb.toString();
	}
	
	public String showAllArrivals() {
		StringBuilder sb= new StringBuilder("All arrivals\n");
		for (int i = 0; i < allFlightsPointer; i++) {
			if(allFlights[i].getDestenation().equalsIgnoreCase("TLV")){
			sb.append(allFlights[i]+"\n");
			}
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		for (int i = 0; i < allFlightsPointer; i++) {
			sb.append(allFlights[i]);
		}
		return sb.toString();
	}
}
