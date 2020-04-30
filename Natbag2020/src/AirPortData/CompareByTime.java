package AirPortData;

import java.util.Comparator;

public class CompareByTime implements Comparator<Flight> {

	@Override
	public int compare(Flight f1,Flight f2) {
		return f1.getTime().compareTo(f2.getTime());
	}
}
