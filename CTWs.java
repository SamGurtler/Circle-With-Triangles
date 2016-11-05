import java.util.ArrayList;
//import java.util.List;
import java.util.Random;
import java.lang.Double;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
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
        public static double getSlope(double x1,double y1,double x2,double y2){
            return ((y2-y1)/(x2-x1));
        }
        public static double[] getEqOfLine(double x1,double y1,double x2,double y2){
            //y=m(x-x1)+y1;
           double[] coefficients = new double[2];  
           coefficients[0] = getSlope(x1,y1,x2,y2);
           coefficients[1] = y1-(coefficients[0]*x1);
           System.out.println("y = "+coefficients[0]+" +"+coefficients[1]);
           return coefficients;
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
        public static void addTriangle(StackPane root,Polygon tri){
            root.getChildren().add(tri);
        }
        public static void removeTriangle(StackPane boot,Polygon tri){
            boot.getChildren().remove(tri);
        }
        public Polygon makeTriangle(Circle circle,boolean Real_or_Fake,Polygon[] tri){
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
            }while(CHT(circle,x1,y1,x2,y2,x3,y3)&&(!getInter(new Polygon(x1,y1,x2,y2,x3,y3),tri))&&(!isTriangulable(triangleSideLength(x1,y1,x2,y2,x3,y3))==Real_or_Fake));
            return new Polygon(x1,y1,x2,y2,x3,y3);
            
        }
        public static double[][] getCircleCords(double centerX,double centerY, double radius){
            ArrayList<double[]> matrix = new ArrayList<double[]>();
            int count = 0;
            for(double x = (centerX-radius);x<=(radius+centerX);x=+Double.MIN_VALUE){
                matrix.add(new double[3]);
                matrix.get(count)[0] = x;
                matrix.get(count)[1] = Math.sqrt(Math.pow(radius,2)-Math.pow(x,2));
                count++;
            }
            count = 0;
            for(double x = (centerX-radius);x<=(radius+centerX);x=+Double.MIN_VALUE){
                matrix.get(count)[3] = -Math.sqrt(Math.pow(radius,2)-Math.pow(x,2));
                count++;
            }
            double[][] cords = new double[matrix.size()][2];
            for(int x = 0;x<=matrix.size();x++){
              cords[x][0]  = matrix.get(x)[0];
              cords[x][1] = matrix.get(x)[1];
              cords[x][2] = matrix.get(x)[2];
            }
            return cords; 
        }
        public static boolean CHT(Circle circle,double x1,double y1,double x2,double y2,double x3,double y3){
            return circle.contains(x1, y1)&&circle.contains(x2, y2)&&circle.contains(x3, y3);
        }
        public static boolean getInter(Polygon tri,Polygon[] triz){
            Shape inter;
            for (Polygon triz1 : triz) {
                inter = Shape.intersect(triz1, tri);
                System.out.println(inter.getLayoutBounds().getWidth() + ":" + inter.getLayoutBounds().getHeight());
                if(inter.getLayoutBounds().getHeight()>=0 ||inter.getLayoutBounds().getWidth()>=0) {
                    return false;
                }
            }
            return true;
            
            //if(inter.getLayoutBounds().getHeight()<=0 || inter.getLayoutBounds().getWidth()<=0) {
        }
        public static boolean circleHasTriangle(Circle circle,double x1,double y1,double x2,double y2,double x3,double y3){
            //Change if you want to make work with other shapes
            boolean[] checkpoints = new boolean[3];
            //boolean[] checkpoints={true,true,true,true};
            double[][] cords = getCircleCords(circle.getCenterX(),circle.getCenterY(),circle.getRadius());
            for(int count = 0;count<=cords.length;count++){
                try{
                    if(x1 >= (circle.getCenterX() - circle.getRadius()) && x1 <= (circle.getCenterX() + circle.getRadius()) && cords[count][1] <= y1 && y1 <= cords[count][2]){
                        checkpoints[0] = true;
                    }
                    if(x2 >= (circle.getCenterX() - circle.getRadius()) && x2 <= (circle.getCenterX() + circle.getRadius()) && cords[count][1] <= y2 && y2 <= cords[count][2]){
                        checkpoints[1] = true;
                    }
                    if((x3 >= (circle.getCenterX() - circle.getRadius())) && (x3 <= (circle.getCenterX() + circle.getRadius())) && ((cords[count][1]) <= y3) && (y3 <= (cords[count][2]))){
                        checkpoints[2] = true;
                    }
                }
                catch(java.lang.Exception exception){
                    
                }
            }
            for(int count = 0;count<=checkpoints.length;count++){
                 if(!checkpoints[count]){
                    return false;
                 }
            }
            return true;
        }
        public void CTWSCr(Stage secondaryStage){
            Button btn = new Button("Click Me");
            Button btn2 = new Button ("Rapid Fire");
            btn.setOnAction((ActionEvent event)->{CTWSCr(secondaryStage);});
            btn2.setOnAction((ActionEvent event1)->{
                for(int x1 = 5;x1>0;x1--){
                    //I think I need to make another Stage for Rapidfire
                    //Also ask for help on how to detect a key held down
                    CTWSRF(secondaryStage);
                }
            });
            StackPane boot = new StackPane();
            Circle le_un = new Circle((double)200,Color.RED);
            boot.getChildren().add(le_un);
            try{
                //For some reason the StackPane won't illustrate the polygons in the ArrayList
                /*ArrayList<Polygon> Trianglefilling = new ArrayList<>();
                for(int count = 0;count<10;count++){*/
                Polygon[] Trianglefilling = new Polygon[2];
                for(int x2 = 0;x2<Trianglefilling.length;x2++){
                   Trianglefilling[x2] = makeTriangle(le_un,true,Trianglefilling);
                    /*for(int count = 0;count<Trianglefilling.length;count++){
                    System.out.println(getInter(Trianglefilling[count],Trianglefilling));
                    }*/
                    addTriangle(boot,Trianglefilling[x2]);
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
        public void CTWSRF(Stage secondaryStage){
            Button btn = new Button("Click Me");
            Button btn2 = new Button ("Rapid Fire");
            btn.setOnAction((ActionEvent event)->{CTWSCr(secondaryStage);});
            btn2.setOnAction((ActionEvent event1)->{
                for(int x1 = 5;x1>0;x1--){
                    //I think I need to make another Stage for Rapidfire
                    //Also ask for help on how to detect a key held down
                    CTWSRF(secondaryStage);
                }
            });
            StackPane boot = new StackPane();
            Circle le_un = new Circle((double)200,Color.RED);
            boot.getChildren().add(le_un);
            try{
                /*//For some reason the StackPane won't illustrate the polygons in the ArrayList
                ArrayList<Polygon> Trianglefilling = new ArrayList<>();*/
                Polygon[] Trianglefilling = new Polygon[2];
                for(int x1 = 4;x1>0;x1--){
                    //I think I need to make another Stage for Rapidfire
                    //Also ask for help on how to detect a key held down
                    Task<Void> sleeper = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            try{
                                Thread.sleep(5000);
                            }catch (InterruptedException e){}
                            return null;
                        }
                    };
                    //new Thread(sleeper).start();
                    //root.wait(4);
                    //root.notify();
                    for(int x2 = 0;x2<Trianglefilling.length;x2++){
                        Trianglefilling[x2] = makeTriangle(le_un,true,Trianglefilling);
                        addTriangle(boot,Trianglefilling[x2]);
                        /*//Keep Until you get ArrayList Working!!!
                        makeTriangles(boot,new Polygon(
                            getRand(x),getRand(y)
                            ,getRand(x),getRand(y)
                            ,getRand(x),getRand(y)));*/
                    }
                    //new Thread(sleeper).start();
                     //root.wait(4);
                    //root.notify();
                    for (Polygon Trianglefilling1 : Trianglefilling) {
                        removeTriangle(boot, Trianglefilling1);
                    }
                }
                for(int x2 = 0;x2<Trianglefilling.length;x2++){
                    Trianglefilling[x2] = makeTriangle(le_un,true,Trianglefilling);
                    addTriangle(boot,Trianglefilling[x2]);
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