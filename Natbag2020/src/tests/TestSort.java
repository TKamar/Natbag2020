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
		allFlights[0]=new Flight("LY315", "El-Al", "London", "TLV", "3", new Date(20, 5, 2020), new Time(10, 10), new Time(10, 10));
		allFlights[1]=new Flight("LY001", "El-Al", "New-York", "TLV", "3", new Date(20, 5, 2020), new Time(00, 45),  new Time(00, 45));
		allFlights[2]=new Flight("TK400", "TK", "TLV", "Amsterdam", "3", new Date(19, 4, 2020), new Time(12, 45), new Time(13, 45));
		
		FlightControl f1=new FlightControl(allFlights);

		StringBuilder expectedResult=new StringBuilder();
		expectedResult.append("Company: El-Al Destenation: New-York Date: 20/5/2020 Time: 0:45 Estemated time: 0:45 Flight number: LY001 Terminal: 3\r\n" + 
				"\r\n" + 
				"Company: El-Al Destenation: London Date: 20/5/2020 Time: 10:10 Estemated time: 10:10 Flight number: LY315 Terminal: 3\r\n");
		assertEquals(expectedResult.toString(),f1.showAllDepartures());
	}
}
