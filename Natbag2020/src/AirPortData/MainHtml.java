package AirPortData;

public class MainHtml {

	public static void main(String[] args) {
		boolean isHtml = args.length > 0 && args[0].equalsIgnoreCase("html");
		boolean isDepartures = args.length > 1 && args[1].equalsIgnoreCase("departures");
		if (isDepartures) {
			System.out.println("Departure 1");
			if (isHtml) System.out.println("<br>");
			System.out.println("Departure 2");
			if (isHtml) System.out.println("<br>");
		}
		else {
			System.out.println("Arrival 1");
			if (isHtml) System.out.println("<br>");
			System.out.println("Arrival 2");
			if (isHtml) System.out.println("<br>");
		}
	}

}
