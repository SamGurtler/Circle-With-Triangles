import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import javafx.scene.layout.Pane;
    public class CTWs extends Application {
        private Parent root;    
        private final double x = 400;
        private final double y = 400;
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
        /*public static double getSlope(double x1,double y1,double x2,double y2){
            return ((y2-y1)/(x2-x1));
        }*/
        /*public static double[] getEqOfLine(double x1,double y1,double x2,double y2){
            //y=m(x-x1)+y1;
           double[] coefficients = new double[2];  
           coefficients[0] = getSlope(x1,y1,x2,y2);
           coefficients[1] = y1-(coefficients[0]*x1);
           System.out.println("y = "+coefficients[0]+" +"+coefficients[1]);
           return coefficients;
        }*/
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
        /*public static double[] triangleSideLength(Point2D p1,Point2D p2,Point2D p3){
            double[] distances = new double[3];
            distances[0] = p1.distance(p2);
            distances[1] = p2.distance(p3);
            distances[2] = p3.distance(p1);
            return doInsertionSort(distances);
        }*/
        public static double[] triangleSideLength(double x1,double y1,double x2,double y2,double x3,double y3){
            double[] distances = new double[3];
            distances[0] = distance(x1,y1,x2,y2);
            distances[1] = distance(x2,y2,x3,y3);
            distances[2] = distance(x3,y3,x1,y1);
            return doInsertionSort(distances);
        }
        public static boolean isTriangulable(double[] distances){
            //scalene triangle
            //return (distances[0]+distances[1])>distances[2];
            //equilateral triangle
           // return (int)distances[0]==(int)distances[1]&&(int)distances[1]==(int)distances[2];
            //isosceles triangle
            //return distances[0]==distances[1]||distances[1]==distances[2];
            return (distances[0]>=5&&distances[1]>=5&&distances[2]>=5);
        }
        public static void addTriangle(Pane root,Polygon tri){
            root.getChildren().add(tri);
        }
        public static void removeTriangle(Pane boot,Polygon tri){
            boot.getChildren().remove(tri);
        }
        public Polygon makeTriangle(Circle circle,boolean Real_or_Fake,Polygon[] tri){
			System.out.println("Entering makeTriangle.");
            double x1 = getRand(x);
            double y1 = getRand(y);
            double x2 = getRand(x);
            double y2 = getRand(y);
            double x3 = getRand(x);
            double y3 = getRand(y);
            Polygon triangle = new Polygon(x1,y1,x2,y2,x3,y3);
            System.out.println("\tFirst->("+x1+","+y1+")"+"("+x2+","+y2+")"+"("+x3+","+y3+")");
            while((isInter(triangle,tri))||(!CHT(circle,x1,y1,x2,y2,x3,y3))||((!isTriangulable(triangleSideLength(x1,y1,x2,y2,x3,y3)))==Real_or_Fake)){
				System.out.println("Entering While.");
				System.out.println("NOPE!\nIs"+"("+x1+","+y1+")"+"("+x2+","+y2+")"+"("+x3+","+y3+")"+" a triangle:"+isTriangulable(triangleSideLength(x1,y1,x2,y2,x3,y3)));
				System.out.println("Are"+"("+x1+","+y1+")"+"("+x2+","+y2+")"+"("+x3+","+y3+")"+"in the circle:"+CHT(circle,x1,y1,x2,y2,x3,y3));
				System.out.println("Does"+"("+x1+","+y1+")"+"("+x2+","+y2+")"+"("+x3+","+y3+")"+"intersect with another triangle:"+isInter(triangle,tri));
				x1 = getRand(x);
				y1 = getRand(y);
				x2 = getRand(x);
				y2 = getRand(y);
				x3 = getRand(x);
				y3 = getRand(y);
                triangle = new Polygon(x1,y1,x2,y2,x3,y3);
				System.out.println("Ending While.");
			}
            System.out.println("LAST->++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Is"+"("+x1+","+y1+")"+"("+x2+","+y2+")"+"("+x3+","+y3+")"+" a triangle:"+isTriangulable(triangleSideLength(x1,y1,x2,y2,x3,y3)));
            System.out.println("Are"+"("+x1+","+y1+")"+"("+x2+","+y2+")"+"("+x3+","+y3+")"+"in the circle:"+CHT(circle,x1,y1,x2,y2,x3,y3));
            System.out.println("Does"+"("+x1+","+y1+")"+"("+x2+","+y2+")"+"("+x3+","+y3+")"+"intersect with another triangle:"+isInter(triangle,tri));
            System.out.println("LAST->++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            return triangle;
        }
        /*public static double[][] getCircleCords(double centerX,double centerY, double radius){
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
        }*/
        public static boolean CHT(Circle circle,double x1,double y1,double x2,double y2,double x3,double y3){
            return circle.contains(x1, y1)&&circle.contains(x2, y2)&&circle.contains(x3, y3);
        }
        public static boolean isInter(Polygon tri,Polygon[] triz){
            for (Polygon triz1 : triz) {
		if(tri.intersects(triz1.getLayoutBounds())){
			return true;
		}
            }
            return false;
            //if(inter.getLayoutBounds().getHeight()<=0 || inter.getLayoutBounds().getWidth()<=0) {
        }
        public boolean  isInter2(Polygon poly, Polygon[] triz){
                for(Polygon triz1:triz) {
                        List<Double> points = poly.getPoints();
                        for(int count = 0;count<points.size();count+=2) {
                                if(!triz1.intersects(points.get(count),points.get(count+1),1, 1)){ // The 3rd and 4th parameters here are "width" and "height". 1 for a point.
                                        return true;
                                }
                        }
                }
                return false;	
        }
        public boolean isInter3(Polygon poly, Polygon[] triz){
                java.awt.Polygon polyAWT = getAWTPolygon(poly);
                java.awt.Polygon trizAWT[] = new java.awt.Polygon[triz.length];
            for(int count = 0;count<triz.length;count++){
                        trizAWT[count] = getAWTPolygon(triz[count]);
            }
            for (java.awt.Polygon trizAWT1 : trizAWT) {
                    AffineTransform normalTrans = AffineTransform.getTranslateInstance(0,0);
                    PathIterator iter = trizAWT1.getPathIterator(normalTrans);
                    double[] point = new double[]{0,0};
                    do{
                        iter.currentSegment(point);
                        double ax = point[0];
                        double ay = point[1];
                        if(polyAWT.contains(ax,ay)){
                            System.out.println("Intersection at ("+ax+","+ay+").");
                            return true;
                        }
                        iter.next();
                    }while( !iter.isDone() );
            }
            return false;
        }
        public static Polygon getInter3Shape(Polygon poly,Polygon triz ){
            AffineTransform normalTrans = AffineTransform.getTranslateInstance(0,0);
            PathIterator iter = getAWTPolygon(triz).getPathIterator(normalTrans);
            double[] point = new double[]{0,0};
            ArrayList<Double> interpoint =new ArrayList<>();
            do{
                iter.currentSegment(point);
                if(poly.contains(point[0],point[1])){
                   System.out.println("Intersection at ("+point[0]+","+point[1]+").");
                   interpoint.add(point[0]);
                   interpoint.add(point[1]);
                }
                iter.next();
            }while( !iter.isDone() );
            return new Polygon(maybePointless((Double[]) interpoint.toArray()));
        }
        public static double[] maybePointless(Double[] input){
            double[] output = new double[input.length];
            for(int count = 0;count<input.length;count++){
                output[count] = (double)input[count];
            }
            return output;
        }
        public static java.awt.Polygon getAWTPolygon(Polygon tri){
                List<Double> points = tri.getPoints();
                int[] xpoints = new int[(points.size()/2)];
                int[] ypoints = new int[(points.size()/2)];
                int count = 0;
                for(int x=0;x<points.size();x+=2){
			xpoints[count] = (int)(double)points.get(x);
			ypoints[count] = (int)(double)points.get(x+1);
			count++;
		}
 		return new java.awt.Polygon(xpoints,ypoints,(points.size()/2));
	}
        /*public static boolean circleHasTriangle(Circle circle,double x1,double y1,double x2,double y2,double x3,double y3){
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
        }*/
        public void CTWSCr(Stage secondaryStage){
            Button btn = new Button("Click Me");
            //Button btn2 = new Button ("Rapid Fire");
            btn.setOnAction((ActionEvent event)->{CTWSCr(secondaryStage);});
            /*btn2.setOnAction((ActionEvent event1)->{
                for(int x1 = 5;x1>0;x1--){
                    //I think I need to make another Stage for Rapidfire
                    //Also ask for help on how to detect a key held down
                    CTWSRF(secondaryStage);
                }
            });*/
            Pane boot = new Pane();
            Circle le_un = new Circle(200,200,(double)200,Color.GREY);
            le_un.setStroke(Color.BLACK);
            boot.getChildren().add(le_un);
            try{
                //For some reason the StackPane won't illustrate the polygons in the ArrayList
                /*ArrayList<Polygon> Trianglefilling = new ArrayList<>();
                for(int count = 0;count<10;count++){*/
                Polygon[] Trianglefilling = new Polygon[10];
                /*Trianglefilling[0] = new Polygon(25-25,25,125-25,25,125-25,125);
				Trianglefilling[0].setFill(Color.BLUE);
                Trianglefilling[0].setStroke(Color.BLACK);
				addTriangle(boot,Trianglefilling[0]);
				Trianglefilling[1] = new Polygon(125-25,25,125-25,125,25-25,125);
				Trianglefilling[1].setFill(Color.ORANGE);
                Trianglefilling[1].setStroke(Color.BLACK);
				addTriangle(boot,Trianglefilling[1]);*/
               // _____ commented out till I have come to the conclusion that intersect works or not 
		for(int x1 =0;x1<Trianglefilling.length;x1++){
                    Trianglefilling[x1] = new Polygon(0,0,1,0,0,1);
                }
                for(int x2 = 0;x2<Trianglefilling.length;x2++){
                   Trianglefilling[x2] = makeTriangle(le_un,true,Trianglefilling);
                   Trianglefilling[x2].setFill(Color.GREEN);
                   Trianglefilling[x2].setStroke(Color.BLACK);
                   /*Rectangle ShowBounds =new Rectangle(Trianglefilling[x2].getLayoutBounds().getMinX(),Trianglefilling[x2].getLayoutBounds().getMinY(),Trianglefilling[x2].getLayoutBounds().getWidth(),Trianglefilling[x2].getLayoutBounds().getHeight());
                    ShowBounds.setFill(Color.TRANSPARENT);
                    ShowBounds.setStroke(Color.YELLOWGREEN);
                    boot.getChildren().add(ShowBounds);*/
                   for(int count = 0;count<Trianglefilling.length;count++){
                    System.out.println(isInter(Trianglefilling[count],Trianglefilling));
                    }
                    addTriangle(boot,Trianglefilling[x2]);
                    /*//Keep Until you get ArrayList Working!!!
                    makeTriangles(boot,new Polygon(
                        getRand(x),getRand(y)
                        ,getRand(x),getRand(y)
                        ,getRand(x),getRand(y)));*/
                }
                for(int x2 = 0;x2<Trianglefilling.length;x2++){
                    for (int x3 = 0;x3<Trianglefilling.length;x3++) {
                      Shape inter = Shape.intersect(Trianglefilling[x2],Trianglefilling[x3]);
                        inter.setFill(Color.RED);
                        System.out.println(x2+" is not "+x3+":"+(x2!=x3));
                        if(x2!=x3){
                            boot.getChildren().add(inter);
                            System.out.println("Shape was made.");
                        }
                    }
                }
               /*Polygon inter1;
                for (Polygon Trianglefilling2 : Trianglefilling) {
                    for (Polygon Trianglefilling1 : Trianglefilling) {
                        inter1 = getInter3Shape(Trianglefilling2, Trianglefilling1);
                        inter1.setFill(Color.CRIMSON);
                        boot.getChildren().add(inter1);
                        System.out.println("Shape was made.");
                    }
                }
                for (Polygon Trianglefilling2 : Trianglefilling) {
                    for (Polygon Trianglefilling1 : Trianglefilling) {
                        inter1 = getInter3Shape(Trianglefilling1, Trianglefilling2);
                        inter1.setFill(Color.CRIMSON);
                        boot.getChildren().add(inter1);
                        System.out.println("Shape was made.");
                    }
                }*/
                /*for(int x2 = 0;x2<Trianglefilling.length;x2++){
                for (int x3 = 0;x3<Trianglefilling.length;x3++) {
                inter = Shape.intersect(Trianglefilling[x3],Trianglefilling[x2]);
                inter.setFill(Color.RED);
                System.out.println(x2+" is not "+x3+":"+(x2!=x3));
                if(x2!=x3){
                boot.getChildren().add(inter);
                System.out.println("Shape was made.");
                }
                }
                }*/
            }
            catch(java.lang.Exception exception){}
            Scene scene = new Scene(boot,x,y,Color.BLACK);
            //boot.getChildren().add(btn2);
            boot.getChildren().add(btn);
            //btn.setTranslateY(-185);
            //btn.setTranslateX(-167.5);
            //btn2.setTranslateY(-185);
            //btn2.setTranslateX(165);
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
            Pane boot = new Pane();
            Circle le_un = new Circle(200,200,(double)200,Color.GREY);
            boot.getChildren().add(le_un);
            try{
                /*//For some reason the StackPane won't illustrate the polygons in the ArrayList
                ArrayList<Polygon> Trianglefilling = new ArrayList<>();*/
                Polygon[] Trianglefilling = new Polygon[2];
                for(int x1 =0;x1<Trianglefilling.length;x1++){
                    Trianglefilling[x1] = new Polygon(175,176,174,176,172,175);
                }
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
                        Trianglefilling[x2].setFill(Color.GREEN);
                        addTriangle(boot,Trianglefilling[x2]);
                        /*//Keep Until you get ArrayList Working!!!
                        makeTriangles(boot,new Polygon(
                            getRand(x),getRand(y)
                            ,getRand(x),getRand(y)
                            ,getRand(x),getRand(y)));*/
                    }
                    Shape inter;
                    for(int x2 = 0;x2<Trianglefilling.length;x2++){
                        for (int x3 = 0;x3<Trianglefilling.length;x3++) {
                            inter = Shape.intersect(Trianglefilling[x2],Trianglefilling[x3]);
                            inter.setFill(Color.RED);
                            boot.getChildren().add(inter);
                        }
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
                Shape inter;
                for(int x2 = 0;x2<Trianglefilling.length;x2++){
                        for (int x3 = 0;x3<Trianglefilling.length;x3++) {
                            inter = Shape.intersect(Trianglefilling[x2],Trianglefilling[x3]);
                            inter.setFill(Color.RED);
                            boot.getChildren().add(inter);
                        }
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