package org.mtransit.parser.mt.data;

import org.apache.commons.lang3.StringUtils;

public class MRoute implements Comparable<MRoute> {

	public long id;
	public String shortName;
	public String longName;

	public String color;

	public MRoute(long id, String shortName, String longName) {
		this.id = id;
		this.shortName = shortName;
		this.longName = longName;
	}

	@Override
	public String toString() {
		return new StringBuilder() //
				.append(this.id) // ID
				.append(',') //
				.append('\'').append(this.shortName == null ? "" : MSpec.escape(this.shortName)).append('\'') // short name
				.append(',') //
				.append('\'').append(this.longName == null ? "" : MSpec.escape(this.longName)).append('\'') // long name
				.append(',') //
				.append('\'').append(this.color == null ? "" : this.color).append('\'') // color
				.toString();
	}

	@Override
	public int compareTo(MRoute otherRoute) {
		return Long.compare(this.id, otherRoute.id);
	}

	@Override
	public boolean equals(Object obj) {
		MRoute o = (MRoute) obj;
		if (this.id != o.id) {
			return false;
		}
		if (!StringUtils.equals(this.shortName, o.shortName)) {
			return false;
		}
		if (!StringUtils.equals(this.longName, o.longName)) {
			return false;
		}
		return true;
	}

	public boolean equalsExceptLongName(Object obj) {
		MRoute o = (MRoute) obj;
		if (this.id != o.id) {
			return false;
		}
		if (!StringUtils.equals(this.shortName, o.shortName)) {
			return false;
		}
		return true;
	}

	public boolean mergeLongName(MRoute mRouteToMerge) {
		if (mRouteToMerge == null || mRouteToMerge.longName == null) {
			return true;
		}
		if (this.longName == null) {
			this.longName = mRouteToMerge.longName;
			return true;
		}
		if (mRouteToMerge.longName.contains(this.longName)) {
			this.longName = mRouteToMerge.longName;
			return true;
		}
		if (this.longName.contains(mRouteToMerge.longName)) {
			return true;
		}
		if (this.longName.compareTo(mRouteToMerge.longName) > 0) {
			this.longName = mRouteToMerge.longName + " / " + this.longName;
		} else {
			this.longName = this.longName + " / " + mRouteToMerge.longName;
		}
		return true;
	}
}
