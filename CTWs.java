import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
    public class CTWs extends Application {
        private Parent root;    
        private final double x = 400;
        private final double y = 400;
	private Polygon[] Trianglefilling;
        @Override
        public void start(Stage primaryStage) {
            Button btn = new Button("Click Me");
            StackPane moot = new StackPane();
            btn.setOnAction((ActionEvent event)->{CTWSCr(primaryStage);});
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
        public static double[] triangleSideLength(double x1,double y1,double x2,double y2,double x3,double y3){
            double[] distances = new double[3];
            distances[0] = distance(x1,y1,x2,y2);
            distances[1] = distance(x2,y2,x3,y3);
            distances[2] = distance(x3,y3,x1,y1);
            return doInsertionSort(distances);
        }
        public static boolean isTriangulable(double[] distances){
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
	public Polygon[] randomTriangles(Stage secondaryStage){
            Polygon[] Polygon_array = new Polygon[10];
            for(int x1 =0;x1<Polygon_array.length;x1++){
                Polygon_array[x1] = new Polygon(0,0,1,0,0,1);
            }
            for(int x2 = 0;x2<Polygon_array.length;x2++){
                Polygon_array[x2] = makeTriangle(new Circle(200,200,(double)200,Color.GREY),true,Polygon_array); 
                load(secondaryStage,(x2+1)/100);
            }
            return Polygon_array;
        }
        public void load(Stage secondaryStage,double progress){
            StackPane moot = new StackPane();
            ProgressBar pb = new ProgressBar(progress);
            moot.getChildren().add(pb);
        }
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
        }
        public void CTWSCr(Stage secondaryStage){
            Button btn = new Button("Click Me");
            btn.setOnAction((ActionEvent event)->{CTWSCr(secondaryStage);});
            Pane boot = new Pane();
            Circle le_un = new Circle(200,200,(double)200,Color.GREY);
            le_un.setStroke(Color.BLACK);
            boot.getChildren().add(le_un);
            try{
                Trianglefilling = randomTriangles(secondaryStage);
                for(int x2 = 0;x2<Trianglefilling.length;x2++){
                   Trianglefilling[x2].setFill(Color.GREEN);
                   Trianglefilling[x2].setStroke(Color.BLACK);
                   for(int count = 0;count<Trianglefilling.length;count++){
                    System.out.println(isInter(Trianglefilling[count],Trianglefilling));
                    }
                    addTriangle(boot,Trianglefilling[x2]);
                }
                /*for(int x2 = 0;x2<Trianglefilling.length;x2++){
                    for (int x3 = 0;x3<Trianglefilling.length;x3++) {
                      Shape inter = Shape.intersect(Trianglefilling[x2],Trianglefilling[x3]);
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
            boot.getChildren().add(btn);
	    boot.setOnMouseMoved((MouseEvent me) -> {
                CTWSMOUSE(secondaryStage,200,200);
            });
            secondaryStage.setTitle("Circle With Triangles");
            secondaryStage.setScene(scene);
            secondaryStage.show();
            }
        public void CTWSMOUSE(Stage secondaryStage,double mouse_X,double mouse_Y){
            Button btn = new Button("Click Me");
            btn.setOnAction((ActionEvent event)->{CTWSCr(secondaryStage);});
            Pane boot = new Pane();
            Circle le_un = new Circle(200,200,(double)200,Color.GREY);
            Circle cusor = new Circle(mouse_X,mouse_Y,(double)2,Color.RED);
            le_un.setStroke(Color.BLACK);
            boot.getChildren().add(le_un);
            boot.getChildren().add(cusor);
            try{
                for(int x2 = 0;x2<Trianglefilling.length;x2++){
                    Trianglefilling[x2].setFill(Color.GREEN);
                    Trianglefilling[x2].setStroke(Color.BLACK);
                    for(int count = 0;count<Trianglefilling.length;count++){
                        System.out.println(isInter(Trianglefilling[count],Trianglefilling));
                    }
                    addTriangle(boot,Trianglefilling[x2]);
                }
            }
            catch(java.lang.Exception exception){}
            Scene scene = new Scene(boot,x,y,Color.BLACK);
            boot.getChildren().add(btn);
            boot.setOnMouseMoved((MouseEvent me) -> {
                CTWSMOUSE(secondaryStage,me.getX(),me.getY());
            });
            secondaryStage.setTitle("Circle With Triangles");
            secondaryStage.setScene(scene);
            secondaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }