//import java.util.ArrayList;
//import java.util.List;
import java.util.Random;
import javafx.application.Application;
//import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;
    public class CTWs extends Application {
        private Parent root;    
        private double x = 400;
        private double y = 400;
        @Override
        public void start(Stage primaryStage) {
            Button btn = new Button("Click Me");
            btn.setOnAction((ActionEvent event)->{CTWSCr(primaryStage);});
            StackPane moot = new StackPane();
            moot.getChildren().add(btn);
            Scene scene = new Scene(moot, 300, 50,Color.BLACK);
            primaryStage.setTitle("Circle With Triangles");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        public static double getRand(double limit){
            Random generator = new Random();
            return generator.nextDouble()*limit; 
        }
        public static double distance(double x1,double y1,double x2,double y2){
            return Math.sqrt(Math.pow(((x2)-(x1)),2)+Math.pow((y2-y1),2));
        }
        public static double[] doInsertionSort(double[] input){
            double temp;
            for (int i = 1; i < input.length; i++) {
                for(int j = i ; j > 0 ; j--){
                    if(input[j] < input[j-1]){
                        temp = input[j];
                        input[j] = input[j-1];
                        input[j-1] = temp;
                    }
                }
            }
            return input;
        }
        public static double[] triangleSideLength(Point2D p1,Point2D p2,Point2D p3){
            double[] distances = new double[3];
            distances[0] = p1.distance(p2);
            distances[1] = p2.distance(p3);
            distances[2] = p3.distance(p1);
            return doInsertionSort(distances);
        }
        public static double[] triangleSideLength(double x1,double y1,double x2,double y2,double x3,double y3){
            double[] distances = new double[3];
            distances[0] = distance(x1,y1,x2,y2);
            distances[1] = distance(x2,y2,x3,y3);
            distances[2] = distance(x3,y3,x1,y1);
            return doInsertionSort(distances);
        }
        public static boolean isTriangulable(double[] distances){
            if((distances[0]+distances[1])>distances[2]){
                return true;
            }
            else return false;
        }
        public static void addTriangles(StackPane root,Polygon tri){
            root.getChildren().add(tri);
        }
        public Polygon makeTriangle(boolean Real_or_Fake){
            double x1;
            double y1;
            double x2;
            double y2;
            double x3;
            double y3;
            do{
                x1 = getRand(x);
                y1 = getRand(y);
                x2 = getRand(x);
                y2 = getRand(y);
                x3 = getRand(x);
                y3 = getRand(y);
            }while(!isTriangulable(triangleSideLength(x1,y1,x2,y2,x3,y3))==Real_or_Fake);
            return new Polygon(x1,y1,x2,y2,x3,y3);
        }
        public void CTWSCr(Stage secondaryStage){
            Button btn = new Button("Click Me");
            Button btn2 = new Button ("Rapid Fire");
            btn.setOnAction((ActionEvent event)->{CTWSCr(secondaryStage);});
            btn2.setOnAction((ActionEvent event1)->{
                for(int x1 = 5;x1>0;x1--){
                    //I think I need to make another Stage for Rapidfire
                    //Also ask for help on how to detect a key held down
                    /*Task<Void> sleeper = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            try{
                                Thread.sleep(125);
                            }catch (InterruptedException e){}
                            return null;
                        }
                    };
                    new Thread(sleeper).start();*/
                    CTWSCr(secondaryStage);
                }
            });
            StackPane boot = new StackPane();
            Circle le_un = new Circle((double)200,Color.RED);
            boot.getChildren().add(le_un);
            try{
                /*//For some reason the StackPane won't illustrate the polygons in the ArrayList
                ArrayList<Polygon> Trianglefilling = new ArrayList<>();*/
                Polygon[] Trianglefilling = new Polygon[20];
                for(int x2 = 0;x2<Trianglefilling.length;x2++){
                    Trianglefilling[x2] = makeTriangle(true);
                    addTriangles(boot,Trianglefilling[x2]);
                    /*//Keep Until you get ArrayList Working!!!
                    makeTriangles(boot,new Polygon(
                        getRand(x),getRand(y)
                        ,getRand(x),getRand(y)
                        ,getRand(x),getRand(y)));*/
                }
            }
            catch(java.lang.Exception exception){}
            Scene scene = new Scene(boot,x,y,Color.BLACK);
            boot.getChildren().add(btn2);
            boot.getChildren().add(btn);
            btn.setTranslateY(-185);
            btn.setTranslateX(-167.5);
            btn2.setTranslateY(-185);
            btn2.setTranslateX(165);
            secondaryStage.setTitle("Circle With Triangles");
            secondaryStage.setScene(scene);
            secondaryStage.show();
            }
        public static void main(String[] args) {
            launch(args);
        }
    }