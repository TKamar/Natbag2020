package tests;

import AirPortData.Date;
import AirPortData.Flight;
import AirPortData.FlightControl;
import AirPortData.Time;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestSort {

	@Test
	public void sortTest() throws Exception {
		Flight [] allFlights= new Flight[3];
		
		FlightControl f1=new FlightControl("C:\\FileExample\\Flight.txt");

		StringBuilder expectedResult=new StringBuilder();
		expectedResult.append("All departures\nCompany: El-Al Destenation: New-York Date: 20/5/2020 Time: 00:45 Estemated time: 00:45 Flight number: LY001 Terminal: 3\n" +  
				"Company: El-Al Destenation: London Date: 20/5/2020 Time: 10:10 Estemated time: 10:10 Flight number: LY315 Terminal: 3\n");
		assertEquals(expectedResult.toString(),f1.showAllDepartures());
	}
}
