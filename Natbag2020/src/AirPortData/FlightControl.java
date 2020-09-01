package AirPortData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FlightControl {

	private Flight[] allFlights;
	private int allFlightsPointer;

	public FlightControl(Flight[] allFlights) {
		this.allFlights = allFlights;
		setPointer();
		sort();
	}

	public FlightControl() {// default c'tor
		allFlights = new Flight[1];
	}

	public FlightControl(String fileName) throws Exception {
		Scanner s = new Scanner(new File(fileName));
		this.allFlights = new Flight[s.nextInt()];
		s.nextLine();
		s.nextLine();// clean the buffer
		for (int i = 0; i < allFlights.length; i++) {
			allFlights[i] = new Flight(s);
			s.nextLine();// clean the buffer
		}
		s.close();
		setPointer();
		sort();
	}

	private void sort() {
		Flight[] temp = Arrays.copyOf(allFlights, allFlightsPointer);
		Arrays.sort(temp, new CompareByDate());
		for (int i = 0; i < temp.length - 1; i++) {
			if (temp[i].getDate().equals(temp[i + 1].getDate())) {
				if (temp[i].getTime().compareTo(temp[i + 1].getTime()) > 0) {
					Flight from = temp[i];
					temp[i] = temp[i + 1];
					temp[i + 1] = from;
				}
			}
		}
		this.allFlights = temp;
	}

	public void addFlight(Flight f1) {
		if (allFlightsPointer < allFlights.length) {
			allFlights[allFlightsPointer++] = f1;
			sort();
		} else {
			this.allFlights = Arrays.copyOf(allFlights, allFlights.length * 2);
			allFlights[allFlightsPointer++] = f1;
			sort();
		}
	}

	private void setPointer() {
		for (int i = 0; i < allFlights.length; i++) {
			if (allFlights[i] != null) {
				allFlightsPointer++;
			}
		}
	}

	public void save(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName));
		pw.println(allFlightsPointer + "\n");
		for (int i = 0; i < allFlightsPointer; i++) {
			allFlights[i].save(pw);
			pw.println();
		}
		pw.close();
	}

	public void add(String FileName) throws Exception {
		Scanner s = new Scanner(new File(FileName));
		boolean isExict = false;
		int loop = s.nextInt();
		s.nextLine();
		s.nextLine();// clean the buffer
		for (int i = 0; i < loop; i++) {
			Flight temp = new Flight(s);
			for (int j = 0; j < allFlightsPointer; j++) {
				if (allFlights[i].equals(temp)) {
					isExict = true;
				}
			}
			if (!isExict) {
				addFlight(temp);
				s.nextLine();// clean the buffer
			}
		}
		s.close();
	}

	public boolean isEmpty() {
		if (allFlights[0] == null)
			return true;
		return false;
	}

	public ArrayList<Flight> showAllDepartures() {
		return showAllDepartures(false);
	}

	public ArrayList<Flight> showAllDepartures(boolean isHtml) {
		String lineBreak = (isHtml) ? "<br>" : "\n";
		StringBuilder sb = new StringBuilder("All departures\n");
		ArrayList<Flight> newArr = new ArrayList<Flight>();
		for (int i = 0; i < allFlightsPointer; i++) {
			if (!(allFlights[i].getDestenation().equalsIgnoreCase("TLV"))) {
				sb.append(allFlights[i] + lineBreak);
				newArr.add(allFlights[i]);
			}
		}
		System.out.println(sb);
		return newArr;
	}

	public ArrayList<Flight> showAllArrivals() {
		return showAllArrivals(false);
	}

	public ArrayList<Flight> showAllArrivals(boolean isHtml) {
		String lineBreak = (isHtml) ? "<br>" : "\n";
		StringBuilder sb = new StringBuilder("All arrivals\n");
		ArrayList<Flight> newArr = new ArrayList<Flight>();
		for (int i = 0; i < allFlightsPointer; i++) {
			if (allFlights[i].getDestenation().equalsIgnoreCase("TLV")) {
				sb.append(allFlights[i] + lineBreak);
				newArr.add(allFlights[i]);
			}
		}
		System.out.println(sb);
		return newArr;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < allFlightsPointer; i++) {
			sb.append(allFlights[i] + "\n");
		}
		return sb.toString();
	}

	public Flight[] searchFlightsByDateRange(Date from, Date to) {
		StringBuilder sb = new StringBuilder();
		ArrayList<Flight> newArr = new ArrayList<Flight>();
		for (int i = 0; i < allFlightsPointer; i++) {
			if (allFlights[i].getDate().compareTo(from) >= 0 && allFlights[i].getDate().compareTo(to) <= 0) {
				sb.append(allFlights[i] + "\n");
				newArr.add(allFlights[i]);
			}
		}
		if (sb.toString().isEmpty()) {
			System.out.println("No flights found");
		}
		System.out.println(sb.toString());
		return (Flight[]) newArr.toArray();
	}

	public String searchFlightsByCompanyName(String companyName) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < allFlightsPointer; i++) {
			if (allFlights[i].getCompany().equalsIgnoreCase(companyName)) {
				sb.append(allFlights[i] + "\n");
			}
		}
		if (sb.toString().isEmpty()) {
			return "No flights found";
		}

		return sb.toString();
	}

	public String getAllCompany() {
		StringBuilder sb = new StringBuilder("All companies: \n");
		Set<String> allCompanies = new HashSet<String>();
		for (int i = 0; i < allFlightsPointer; i++) {
			allCompanies.add(allFlights[i].getCompany());
		}
		for (int i = 0; i < allCompanies.size(); i++) {
			sb.append(allCompanies.toArray()[i] + "\n");
		}
		return sb.toString();
	}

	public ArrayList<Flight> searchFlightByContry(String str) {
		ArrayList<Flight> f1 = new ArrayList<Flight>();
		for (int i = 0; i < allFlightsPointer; i++) {
			if (allFlights[i].getFrom().equalsIgnoreCase(str) || allFlights[i].getDestenation().equalsIgnoreCase(str)) {
				f1.add(allFlights[i]);
			}
		}
		return f1;

	}

}
