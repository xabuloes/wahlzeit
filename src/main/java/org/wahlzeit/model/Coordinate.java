package org.wahlzeit.model;

/**
 * A coordinate represents a position in a 3D Cartesian coordinate systems.
 *
 */
public class Coordinate {

	private double x;
	private double y;
	private double z;

	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getDistance(Coordinate coordinateB) {

		if (coordinateB == null) {
			throw new IllegalArgumentException("'null' value was given to calculate distance between to coordinates");
		}

		final double dx = Math.abs(this.x - coordinateB.x);
		final double dy = Math.abs(this.y - coordinateB.y);
		final double dz = Math.abs(this.z - coordinateB.z);

		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public boolean isEqual(Coordinate toCompare) {
		if (toCompare == null) {
			return false;
		}

		return (this.getX() == toCompare.getX()) && (this.getY() == toCompare.getY())
				&& (this.getZ() == toCompare.getZ());
	}

	public boolean equals(Coordinate toCompare) {
		return this.isEqual(toCompare);
	}

}
