

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;


public class MatController {

	private int count = 0;
	private int size = 60;
	private int times = 10;
	private int[][] before = new int[10][10];
	private int[][] after = new int[10][10];
	
    @FXML
    private Canvas canvas;

    @FXML
    private Button next;

    @FXML
    private Button start;


    @FXML
    void startPressed(ActionEvent event) {
    	draw();
    }
    
    @FXML
    void initialize() {
    	draw();
    }

    @FXML
    void nextPressed(ActionEvent event) {
       	count =0;
    	for(int i=0;i<before.length;i++) {
    		for(int j=0;j<before.length;j++) {
    			//right
    			if(j+1!=before.length) { 
    				if(before[i][j+1]==1) {count++;}
    			}
    			//left
    			if(j-1>=0) {
    				if(before[i][j-1]==1) {count++;}
    			}
    			//down
    			if(i+1!=before.length) { 
    				if(before[i+1][j]==1) {count++;}
    			}
    			//up
    			if(i-1>=0) { 
    				if(before[i-1][j]==1) {count++;}
    			}
    			// left up
    			if(i-1>=0 && j-1>=0) { 
    				if(before[i-1][j-1]==1) {count++;}
    			}
    			//right up
    			if(j+1!=before.length && i-1>=0) { 
    				if(before[i-1][j+1]==1) {count++;}
    			}
    			// left down
    			if(j-1>=0 && i+1!=before.length) { 
    				if(before[i+1][j-1]==1) {count++;}
    			}
    			//right down
    			if(j+1!=before.length && i+1!=before.length) { 
    				if(before[i+1][j+1]==1) {count++;}
    			}
    			
    			// Birth
    			if(before[i][j]==0) { 
    				if(count==3) {after[i][j]=1;}
    				else {after[i][j]=0;}
    			}
    			// dead
    			if(before[i][j]==1) { 
    				if(count<2 || count>3) {after[i][j]=0;}
    			//existence		
    				if(1<count && count<4) {after[i][j]=1;}
    			}
    			count=0;
    		}
    	}
    	setRandomConway();
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    	for(int i=0;i<10;i++) {
    		for(int j=0;j<10;j++) {
    			if(after[i][j]==0) {
    				gc.strokeRect(j*60,i*60,60,60);
    			}else
    				gc.fillRect(j*60,i*60,60,60);
    		}
    	}    	
    }	

    private void setRandomConway() {
    	Random r = new Random();
    	for(int i=0;i<before.length;i++) {
    		for(int j=0;j<before.length;j++) {
    			before[i][j]=r.nextInt(2);
    		}
    	}
    }
    
    public void draw() {
    	setRandomConway();
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    	for(int i=0;i<10;i++) {
    		for(int j=0;j<10;j++) {
    			if(before[i][j]==0) {
    				gc.strokeRect(j*60,i*60,60,60);
    			}else
    				gc.fillRect(j*60,i*60,60,60);
    		}
    	}    	
    	
    }
}