package trilateration;


import java.awt.datatransfer.DataFlavor;

import com.sun.javafx.tk.Toolkit;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Frame extends Application{
	private static Stage window;
	private static Scene scene;
	Trilateration trilateration = new Trilateration();
	
	public void show() {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("started window");
		window = primaryStage;
		window.setTitle("Dual Universe Ore Locator");
		updateStartingWindow();
		System.out.println("finished window");
	}
	TextField[] coords;
	TextField[] distance;
	TextField surfaceArea;
	private void updateStartingWindow(){
		BorderPane pane = new BorderPane();
        GridPane grid = new GridPane();
		coords = new TextField[4];
		distance = new TextField[4];
		for(int i =0;i<coords.length;i++){
			coords[i] = new TextField();
			distance[i] = new TextField();
			coords[i].setText("new PointCoordinates");
			distance[i].setText("distance in m");
			grid.add(coords[i], i, 0);
			grid.add(distance[i], i, 1);
		}
		surfaceArea = new TextField("Planet Surface Area");
        grid.add(surfaceArea,coords.length,1);
        Button update = new Button();
        
        update.setText("add Point");
        update.setOnAction(e -> calculatePoint());
        
        Button reset = new Button();
        
        reset.setText("Reset");
        reset.setOnAction(e -> trilateration.clear());
        Text coordInfo = new Text("Format: ::pos{0,2,33.3681,99.3460,110.3286}");
        grid.add(coordInfo,coords.length,0);
        
		Text currentState = new Text();
		
		
		grid.add(update,0,2);
		grid.add(reset,1,2);
		
		//pane.setLeft(update);
		pane.setCenter(currentState);
		pane.setTop(grid);
		currentState.setText(trilateration.getState());
		 if(window.getScene()!=null){
	        	scene = new Scene(pane,window.getScene().getWidth(),window.getScene().getHeight());
	        	System.out.println(window.getScene().getWidth()+":"+window.getScene().getHeight());
	        }else{
	        	scene = new Scene(pane,830,462);
	        }
		window.setScene(scene);
		window.show();
	}
	
	private void calculatePoint(){
		for(int i=0;i<coords.length;i++){
			String pos = coords[i].getText().trim();
			String dis = distance[i].getText().trim();
			if(!pos.equals("")&&!pos.equals("new PointCoordinates")&&!dis.equals("")&&!dis.equals("distance in m")){
				System.out.println(pos+";"+dis);
				trilateration.addPoint(pos+";"+dis);
			}
		}
		String area = surfaceArea.getText().trim();
		if(!area.equals("")&&!area.equals("Planet Surface Area")){
			trilateration.calculateRadiusBasedOnSurfaceArea(Long.parseLong(area));
		}
		trilateration.calculatePoint();
		updateStartingWindow();
	}
	
	
	
	
	
}
