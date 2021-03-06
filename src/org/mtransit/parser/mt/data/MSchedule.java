package org.mtransit.parser.mt.data;

import org.mtransit.parser.Constants;

public class MSchedule implements Comparable<MSchedule> {

	private String serviceId;
	private long tripId;
	private int stopId;
	private int departure;

	private int headsignType = -1;
	private String headsignValue = null;

	public MSchedule(String serviceId, long routeId, long tripId, int stopId, int departure) {
		this.stopId = stopId;
		this.tripId = tripId;
		this.serviceId = serviceId;
		this.departure = departure;
	}

	public void setHeadsign(int headsignType, String headsignValue) {
		this.headsignType = headsignType;
		this.headsignValue = headsignValue;
	}

	public void clearHeadsign() {
		this.headsignType = -1;
		this.headsignValue = null;
	}

	public int getHeadsignType() {
		return this.headsignType;
	}

	public String getHeadsignValue() {
		return this.headsignValue;
	}

	public long getTripId() {
		return this.tripId;
	}

	public int getStopId() {
		return this.stopId;
	}

	public String getUID() {
		// identifies a stop + trip + service (date) => departure
		return this.serviceId + Constants.UUID_SEPARATOR + this.tripId + Constants.UUID_SEPARATOR + this.stopId + Constants.UUID_SEPARATOR + this.departure;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); //
		sb.append(Constants.STRING_DELIMITER).append(MSpec.escape(this.serviceId)).append(Constants.STRING_DELIMITER); // service ID
		sb.append(Constants.COLUMN_SEPARATOR); //
		// no route ID, just for file split
		sb.append(this.tripId); // trip ID
		sb.append(Constants.COLUMN_SEPARATOR); //
		sb.append(this.departure); // departure
		sb.append(Constants.COLUMN_SEPARATOR); //
		sb.append(this.headsignType < 0 ? Constants.EMPTY : this.headsignType); // HEADSIGN TYPE
		sb.append(Constants.COLUMN_SEPARATOR); //
		sb.append(Constants.STRING_DELIMITER)//
				.append(this.headsignValue == null ? Constants.EMPTY : this.headsignValue) //
				.append(Constants.STRING_DELIMITER); // HEADSIGN STRING
		return sb.toString();
	}

	public String toStringSameServiceIdAndTripId(MSchedule lastSchedule) {
		StringBuilder sb = new StringBuilder(); //
		if (lastSchedule == null) {
			sb.append(this.departure); // departure
		} else {
			sb.append(this.departure - lastSchedule.departure); // departure
		}
		sb.append(Constants.COLUMN_SEPARATOR); //
		sb.append(this.headsignType < 0 ? Constants.EMPTY : this.headsignType); // HEADSIGN TYPE
		sb.append(Constants.COLUMN_SEPARATOR); //
		sb.append(Constants.STRING_DELIMITER) //
				.append(this.headsignValue == null ? Constants.EMPTY : this.headsignValue) //
				.append(Constants.STRING_DELIMITER); // HEADSIGN STRING
		return sb.toString();
	}

	public boolean sameServiceIdAndTripId(MSchedule lastSchedule) {
		if (lastSchedule == null) {
			return false;
		}
		return lastSchedule.serviceId.equals(this.serviceId) && lastSchedule.tripId == this.tripId;
	}

	@Override
	public int compareTo(MSchedule otherSchedule) {
		// sort by service_id => trip_id => stop_id => departure
		if (!this.serviceId.equals(otherSchedule.serviceId)) {
			return this.serviceId.compareTo(otherSchedule.serviceId);
		}
		// no route ID, just for file split
		if (this.tripId != otherSchedule.tripId) {
			return Long.compare(this.tripId, otherSchedule.tripId);
		}
		if (this.stopId != otherSchedule.stopId) {
			return this.stopId - otherSchedule.stopId;
		}
		return this.departure - otherSchedule.departure;
	}

	@Override
	public boolean equals(Object obj) {
		MSchedule ts = (MSchedule) obj;
		if (ts.serviceId != null && !ts.serviceId.equals(this.serviceId)) {
			return false;
		}
		// no route ID, just for file split
		if (ts.tripId != 0 && ts.tripId != this.tripId) {
			return false;
		}
		if (ts.stopId != 0 && ts.stopId != this.stopId) {
			return false;
		}
		if (ts.departure != 0 && ts.departure != this.departure) {
			return false;
		}
		return true;
	}

}
