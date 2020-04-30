package AirPortData;

import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
	//ToString,Equals,Functions,Cases
	private static Flight [] buildFlightsList(Scanner s) throws Exception {
		System.out.println("Enter how many flights would you like to insert:");
		int length= s.nextInt();
		Flight [] allFlights= new Flight [length];
		for (int i = 0; i < length; i++) {
			System.out.println("First enter flight details:");
			System.out.println("Enter flight number: ");
			String flightNumber=s.next();
			System.out.println("Enter flight company: ");
			String company=s.next();
			System.out.println("Enter flight destenation: ");
			String destenation=s.next();
			System.out.println("Enter where the flight comes from : ");
			String from=s.next();
			System.out.println("Enter flight terminal: ");
			String terminal=s.next();
			System.out.println("Enter flight date , enter day, month, year : ");
			int day=s.nextInt();
			int month=s.nextInt();
			int year=s.nextInt();
			Date date=new Date(day, month, year);
			System.out.println("Enter flight landing time- hours, minutes");
			int hours=s.nextInt();
			int minutes=s.nextInt();
			Time time= new Time(hours, minutes);
			Time estematedTime=new Time(hours, minutes);
			allFlights[i]= new Flight(flightNumber, company, destenation, from, terminal, date, time, estematedTime);
		}
		return allFlights;
	}
	

	public static void main(String[] args) throws Exception {                                                                                                                                                                                                                                                                                                                                
		Scanner s= new Scanner(System.in);
		
		Flight [] allFlights= new Flight[3];
		allFlights[0]=new Flight("LY315", "El-Al", "London", "TLV", "3", new Date(20, 5, 2020), new Time(10, 10), new Time(10, 10));
		allFlights[1]=new Flight("LY001", "El-Al", "New-York", "TLV", "3", new Date(20, 5, 2020), new Time(00, 45),  new Time(00, 45));
		allFlights[2]=new Flight("TK400", "TK", "TLV", "Amsterdam", "3", new Date(19, 4, 2020), new Time(12, 45), new Time(13, 45));
		
		FlightControl f1=new FlightControl(allFlights);
//		f1.save("C:\\FileExample\\Flight.txt");
		boolean fContinue=false;
//		FlightControl f1=new FlightControl();
		
		while(!fContinue) {
			System.out.println("Welvome to GIT\nchoose from the folowing:\n"+
					"1---> Add an outbound flight\n"+
					"2---> Add an incoming flight\n"+
					"3---> Show all departuers\n"+
					"4---> Show all arrivals\n"+
					"5---> Save all flights\n"+
					"6---> Load all flights\n"+
					"7---> Exit");
			int choice=s.nextInt();
			switch(choice) {
			case 1:
				buildFlightsList(s);
				break;

			case 2:
				buildFlightsList(s);
				break;

			case 3:
				System.out.println(f1.showAllDepartures());
				break;

			case 4:
				System.out.println(f1.showAllArrivals());
				break;

			case 5:
				f1.save("C:\\FileExample\\Flight.txt");
				break;
				
			case 6:
			    f1=new FlightControl("C:\\FileExample\\Flight.txt");
				break;
				
			case 7:
				fContinue=true;
				break;
			}
		}
	}
}
