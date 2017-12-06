import java.util.List;
import java.util.stream.Collectors;

/**
 * Day6
 */
public class Day6 {
	public static void main(String[] args) throws Exception {
		List<String> csv = Utils.readFile("input6.txt");
		csv = csv.subList(1, csv.size());

		List<Place> capitals = csv.stream()
			.map(row -> new Place(row))
			.filter(s -> s.hovedstad)
			.distinct()
			.sorted()
			.collect(Collectors.toList());

		double speed = 7274; // km/h
		double time = 0;
		int i = 0;
		while (time < 24) {
			Place capital = capitals.get(i++);
			// round trip, twice the time
			time += 2 * capital.dist / speed;
			System.out.println(String.format("%s %f (round-trip) %f (too, one-way)", capital, time, time - capital.dist / speed));
		}
		if (time - capitals.get(i).dist / speed < 24) {
			System.out.println(i);
		} else {
			System.out.println(--i);
		}
	}
}

// 0 Landskode
// 1 Stadnamn nynorsk
// 2 Stadnamn bokmål
// 3 Stadnamn engelsk
// 4 Geonames-ID
// 5 Stadtype nynorsk
// 6 Stadtype bokmål
// 7 Stadtype engelsk
// 8 Landsnamn nynorsk
// 9 Landsnamn bokmål
// 10 Landsnamn engelsk
// 11 Folketal
// 12 Lat
// 13 Lon
// 14 Høgd over havet
// 15 Lenke til nynorsk-XML
// 16 Lenke til bokmåls-XML
// 17 Lenke til engelsk-XML
class Place implements Comparable<Place> {
	String str;
	String[] values;
	String name;
	String stadtype;
	boolean hovedstad;
	double lat;
	double lon;
	double dist;

	final static double R = 6371; // radius of the earth in km
	final static double latOslo = 59.911491;
	final double lonOslo = 10.757933;

	Place(String s) {
		str = s;
		values = s.split("\t");
		name = values[2];
		stadtype = values[6];
		hovedstad = stadtype.equals("Hovedstad");
		lat = Double.parseDouble(values[12]);
		lon = Double.parseDouble(values[13]);
		dist = distance(lat, lon, latOslo, lonOslo);
	}

	public String toString() {
		return String.format("%s (%f,%f) %f km", name, lat*180/Math.PI, lon*180/Math.PI, distanceToOslo());
	}

	public double distanceToOslo() {
		return distance(lat, lon, latOslo, lonOslo);
	}

	@Override
	public int compareTo(Place b) {
		double diff = dist - b.dist;
		if (diff > 0) {
			return 1;
		} else if (diff < 0) {
			return -1;
		}
		return 0;
	}

	@Override
	public boolean equals(Object b) {
		if (b == null) {
			return false;
		}
		return name.equals(((Place) b).name);
	}

	@Override public int hashCode() {
		return name.hashCode();
	 }

	 /**
	 * Jason Winn
	 * http://jasonwinn.org
	 * Created July 10, 2013
	 *
	 * Description: Small class that provides approximate distance between
	 * two points using the Haversine formula.
	 *
	 * Call in a static context:
	 * Haversine.distance(47.6788206, -122.3271205,
	 *                    47.6788206, -122.5271205)
	 * --> 14.973190481586224 [km]
	 *
	 */
	private static double distance(double startLat, double startLong,
																double endLat, double endLong) {

			double dLat  = Math.toRadians((endLat - startLat));
			double dLong = Math.toRadians((endLong - startLong));

			startLat = Math.toRadians(startLat);
			endLat   = Math.toRadians(endLat);

			double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

			return R * c; // <-- d
	}

	public static double haversin(double val) {
			return Math.pow(Math.sin(val / 2), 2);
	}
}
