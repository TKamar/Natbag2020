package AirPortData;

import java.util.Comparator;

public class CompareByDate implements Comparator<Flight>{

	@Override
	public int compare(Flight f1,Flight f2) {
		return f1.getDate().compareTo(f2.getDate());
	}
}
