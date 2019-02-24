package trilateration;

import java.util.ArrayList;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.linear.OpenMapRealVector;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

public class Trilateration {
	private double radius = 126.06794214576 ;
	private long planetSurfaceArea = 199718912000L;
	private ArrayList<Point3dForTriangulation> points = new ArrayList<>();
	private double[] currentPointInLatLon = new double[3];
	private double[] getPosition(String string){
		double[] position = new double[3];
		
		string = string.replace(":", "");
		string = string.replace("pos", "");
		string = string.replace("{", "");
		string = string.replace("}", "");
		String[] pos = string.split(",");
		double r = radius+Double.parseDouble(pos[4])/1000;
		for(int i =2;i<4;i++){
			position[i-2] = Double.parseDouble(pos[i]);
		}
		double lat = Double.parseDouble(pos[2]);
		lat = Math.toRadians(lat);
		double lon = Double.parseDouble(pos[3]);
		lon = Math.toRadians(lon);
		position[0]=  r*Math.cos(lat)*Math.cos(lon);
		position[1]= r*Math.cos(lat)*Math.sin(lon);
		position[2] = r*Math.sin(lat);
		for(double d:position){
			//System.out.print(d+":");
		}//System.out.println();
		return position;
	}
	public void calculateRadiusBasedOnSurfaceArea(long r){
		double radius = Math.sqrt(r/(4*Math.PI))/1000;
		planetSurfaceArea = r;
		System.out.println(radius);
		setRadius(radius);
	}
	private void setRadius(double r){
		this.radius =r;
	}
	public long getSurfaceArea(){
		return planetSurfaceArea;
	}
	public double getRadius(){
		return radius;
	}
	public void clear(){
		points = new ArrayList<>();
	}
	public String getState(){
		return getPoint()+"\nPlanet Radius: "+radius+"\nPlanet Surface Area: "+planetSurfaceArea;
	}
	public String getPoint(){
		String string;
		string = "Height: "+currentPointInLatLon[0]+"\nLat: "+currentPointInLatLon[1]+"\nLon: "+currentPointInLatLon[2];
		return string;
	}
	private void printPosition(){
		System.out.println("Height:"+currentPointInLatLon[0]);
		System.out.println("Lat:"+currentPointInLatLon[1]);
		System.out.println("Lon:"+currentPointInLatLon[2]);
	}
	public void addPoint(String newpoint){
		String[] s = newpoint.split(";");
		Point3dForTriangulation point = new Point3dForTriangulation(getPosition(s[0]),Double.parseDouble(s[1])/1000);
		points.add(point);
	}
	
	private double[][] getPositions(){
		double[][] positions = new double[points.size()][];
		for(int i=0;i<points.size();i++){
			positions[i] = points.get(i).getPosition();
		}
		return positions;
	}
	private double[] getDistances(){
		double[] distances = new double[points.size()];
		for(int i=0;i<points.size();i++){
			distances[i] = points.get(i).getDistance();
		}
		return distances;
	}
	public void calculatePoint(){
		if(points.size()>1){
		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(getPositions(), getDistances()), new LevenbergMarquardtOptimizer());
		Optimum optimum = solver.solve();

		// the answer
		double[] centroid = optimum.getPoint().toArray();
		
		
		
		currentPointInLatLon[0] = Math.sqrt(centroid[0]*centroid[0]+centroid[1]*centroid[1]+centroid[2]*centroid[2]);
		
		
		currentPointInLatLon[1] = Math.toDegrees(Math.asin(centroid[2]/currentPointInLatLon[0]));
		
		
		currentPointInLatLon[2] = Math.toDegrees(Math.atan2(centroid[1], centroid[0]));
		currentPointInLatLon[0]-=radius;
		printPosition();}
		
	}
}
