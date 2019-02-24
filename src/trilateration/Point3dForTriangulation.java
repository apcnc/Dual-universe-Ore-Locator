package trilateration;

public class Point3dForTriangulation {
public Point3dForTriangulation(double x, double y, double z, double distance) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.distance = distance;
	}
public Point3dForTriangulation(double[] xyz, double distance) {
	super();
	if(xyz.length!=3){
		throw new IllegalArgumentException("xyz should contain exactly 3 variables");
	}
	this.x = xyz[0];
	this.y = xyz[1];
	this.z = xyz[2];
	this.distance = distance;
}
private final double x,y,z,distance;

public double getX() {
	return x;
}

public double getY() {
	return y;
}



public double getZ() {
	return z;
}

public double getDistance() {
	return distance;
}
public double[] getPosition(){
	double[] position = new double[3];
	position[0]=x;
	position[1]=y;
	position[2]=z;
	return position;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(distance);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(x);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(y);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(z);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Point3dForTriangulation other = (Point3dForTriangulation) obj;
	if (Double.doubleToLongBits(distance) != Double
			.doubleToLongBits(other.distance))
		return false;
	if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
		return false;
	if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
		return false;
	if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
		return false;
	return true;
}
}
