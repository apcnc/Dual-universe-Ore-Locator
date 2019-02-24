package trilateration;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.linear.OpenMapRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.RealVectorFormat;

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;
import uk.me.jstott.jcoord.UTMRef;
import uk.me.jstott.jcoord.datum.OSGB36Datum;
import uk.me.jstott.jcoord.datum.WGS84Datum;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trilateration tri = new Trilateration();
		tri.addPoint("::pos{0,2,33.3681,99.3460,110.3286};0.2");
		tri.addPoint("::pos{0,2,33.3674,99.2893,97.7909};0.25");
		tri.addPoint("::pos{0,2,33.3422,99.3574,104.6372};0.2");
		tri.addPoint("::pos{0,2,33.4117,99.4454,129.2137};0.25");
		tri.calculatePoint();
		Frame frame = new Frame();
		frame.show();
		/*double[][] positions = new double[][]{getPosition("::pos{0,2,33.3681,99.3460,110.3286}"),
				getPosition("::pos{0,2,33.3674,99.2893,97.7909}"),
				getPosition("::pos{0,2,33.3422,99.3574,104.6372}"),
				getPosition("::pos{0,2,33.4117,99.4454,129.2137}")};
		double[] distances = new double[] { 0.2,0.25,0.2,0.25};*/
	}
	
	
	
	
	

}
