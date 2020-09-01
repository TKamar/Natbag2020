package AirPortData;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
	// ToString,Equals,Functions,Cases
	private static Flight[] buildFlightsList(Scanner s) throws Exception {
		System.out.println("Enter how many flights would you like to insert:");
		int length = s.nextInt();
		Flight[] allFlights = new Flight[length];

		for (int i = 0; i < length; i++) {
			System.out.println("First enter flight details:");
			System.out.println("Enter flight number: ");
			String flightNumber = s.next();
			System.out.println("Enter flight company: ");
			String company = s.next();
			System.out.println("Enter flight destenation: ");
			String destenation = s.next();
			System.out.println("Enter where the flight comes from : ");
			String from = s.next();
			System.out.println("Enter flight terminal: ");
			String terminal = s.next();
			System.out.println("Enter flight date , enter day, month, year : ");
			int day = s.nextInt();
			int month = s.nextInt();
			int year = s.nextInt();
			Date date = new Date(day, month, year);
			System.out.println("Enter flight landing time- hours, minutes");
			int hours = s.nextInt();
			int minutes = s.nextInt();
			Time time = new Time(hours, minutes);
			Time estematedTime = new Time(hours, minutes);
			allFlights[i] = new Flight(flightNumber, company, destenation, from, terminal, date, time, estematedTime);
		}
		return allFlights;
	}
	
	public static String Show(Flight[] f1) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < f1.length; i++) {
			sb.append(f1[i].toString() + "\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		boolean isHtml = args.length > 0 && args[0].equalsIgnoreCase("html");
		boolean isDepartures = args.length > 1 && args[1].equalsIgnoreCase("departures");

		if (isHtml) {
			FlightControl f1 = new FlightControl("Flight.txt");
			if(isDepartures) {
				System.out.println("Departure Flights:");
				f1.showAllDepartures(isHtml);
			}
			else {
				System.out.println("Arrivals Flights:");
				f1.showAllArrivals(isHtml);
			}
			
			System.out.println(f1);
			f1.searchFlightsByCompanyName(args[2]);
			if (isHtml) System.out.println("<br>");
			f1.searchFlightByContry(args[3]);

			int day1 = Integer.parseInt(args[4]);
			int month1 = Integer.parseInt(args[5]);
			int year1 = Integer.parseInt(args[6]);
			int day2 = Integer.parseInt(args[7]);
			int month2 = Integer.parseInt(args[8]);
			int year2 = Integer.parseInt(args[9]);
			
			Date date1 = new Date(day1, month1, year1);
			Date date2 = new Date(day2, month2, year2);
			
			f1.searchFlightsByDateRange(date1, date2);
			
			Flight[] finalResult = Arrays.copyOf(f1.searchFlightsByDateRange(date1, date2), (f1.searchFlightsByDateRange(date1, date2)).length); 
			Show(finalResult);
			
		} 
		else {

			Scanner s = new Scanner(System.in);

			Flight[] allFlights = new Flight[3];
			allFlights[0] = new Flight("LY315", "El-Al", "London", "TLV", "3", new Date(20, 5, 2020), new Time(10, 10),
					new Time(10, 10));
			allFlights[1] = new Flight("LY001", "El-Al", "New-York", "TLV", "3", new Date(20, 5, 2020),
					new Time(00, 45), new Time(00, 45));
			allFlights[2] = new Flight("TK400", "TK", "TLV", "Amsterdam", "3", new Date(19, 4, 2020), new Time(12, 45),
					new Time(13, 45));

			FlightControl f1 = new FlightControl(allFlights);
			boolean fContinue = false;

			while (!fContinue) {
				System.out.println("Welvome to GIT\nchoose from the folowing:\n" + "1---> Add an outbound flight\n"
						+ "2---> Add an incoming flight\n" + "3---> Show all departuers\n" + "4---> Show all arrivals\n"
						+ "5---> Save all flights\n" + "6---> Load all flights\n"
						+ "7---> Search flights by date range\n" + "8---> Search flights by company name\n"
						+ "9---> Exit");
				int choice = s.nextInt();
				switch (choice) {
				case 1:
					buildFlightsList(s);
					break;

				case 2:
					buildFlightsList(s);
					break;

				case 3:
					System.out.print(f1.showAllDepartures());
					break;

				case 4:
					System.out.print(f1.showAllArrivals());
					break;

				case 5:
					f1.save("Flight.txt");
					break;

				case 6:
					if (f1.isEmpty())
						f1 = new FlightControl("Flight.txt");
					else
						f1.add("Flight.txt");
					break;

				case 7:
					System.out.println("Enter date (day,month,year) range, from: ");
					int day = s.nextInt();
					int month = s.nextInt();
					int year = s.nextInt();
					Date from = new Date(day, month, year);
					System.out.println("Enter date (day,month,year) range, to: ");
					day = s.nextInt();
					month = s.nextInt();
					year = s.nextInt();
					Date to = new Date(day, month, year);

					System.out.println(f1.searchFlightsByDateRange(from, to));
					break;

				case 8:
					System.out.println("Enter company name: ");
					System.out.println(f1.getAllCompany());
					String companyName = s.next();

					System.out.println(f1.searchFlightsByCompanyName(companyName));
					break;

				case 9:
					fContinue = true;
					break;
				}
			}
		}
	}
}
