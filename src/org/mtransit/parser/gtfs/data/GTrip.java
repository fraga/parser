package org.mtransit.parser.gtfs.data;

// https://developers.google.com/transit/gtfs/reference#trips_fields
public class GTrip {

	public static final String FILENAME = "trips.txt";

	public static final String ROUTE_ID = "route_id";
	private String route_id;
	public static final String SERVICE_ID = "service_id";
	public String service_id;
	public static final String TRIP_ID = "trip_id";
	private String trip_id;

	public static final String TRIP_HEADSIGN = "trip_headsign";
	public String trip_headsign;
	public static final String TRIP_SHORT_NAME = "trip_short_name";
	public String trip_short_name;
	public static final String DIRECTION_ID = "direction_id";
	public String direction_id;
	public static final String BLOCK_ID = "block_id";
	public String block_id;
	public static final String SHAPE_ID = "shape_id";
	public String shape_id;

	private String uid;

	public GTrip(String route_id, String service_id, String trip_id) {
		this.route_id = route_id;
		this.service_id = service_id;
		this.trip_id = trip_id;
		this.uid = this.route_id + this.trip_id;
	}

	public String getUID() {
		return this.uid;
	}

	public String getRouteId() {
		return route_id;
	}

	public String getTripId() {
		return trip_id;
	}

	@Override
	public String toString() {
		return new StringBuilder() //
				.append('\'').append(route_id).append('\'').append(',') //
				.append('\'').append(service_id).append('\'').append(',') //
				.append('\'').append(trip_id).append('\'').append(',') //
				.append('\'').append(trip_headsign).append('\'').append(',') //
				.append('\'').append(trip_short_name).append('\'').append(',') //
				.append('\'').append(direction_id).append('\'').append(',') //
				.append('\'').append(block_id).append('\'').append(',') //
				.append('\'').append(shape_id).append('\'') //
				.toString();
	}
}
