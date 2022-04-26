package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;


public class Main extends Application {
	
	
	Pane stack =  new Pane();
	
	TextArea text ;
	TextArea text2;
	
	Group g = new Group();
	Circle redC = new Circle();
	Circle blueC = new Circle();
	@Override
	public void start(Stage stage) {
		try {
			Group group = new Group();
			Scene scene = new Scene(group,900,800);
			stage.setResizable(false);


			stack.setLayoutX(10);
			
			Image img = new Image("map.png");
			ImageView imgV = new ImageView(img);
			imgV.setFitHeight(790);
			imgV.setFitWidth(380);
			

//			//x 34.11839
//			//y 33.34777

				////
		
			//___________________________

			
			stack.getChildren().addAll(imgV, artLine);
			

			readFile();
			
			
			Label map = new Label("Map");
			map.setFont(Font.font("Times New Roman",FontWeight.BLACK, 20));
			map.setLayoutX(600);
			map.setLayoutY(10);
			map.setTextFill(Color.rgb(90, 84, 84));
			
			Line line = new Line(420,40,850,40);
			
			Label src = new Label("Source: ");
			src.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			ComboBox<String> srcC = new ComboBox<>();
			srcC.setPromptText("Source");
			srcC.setStyle("-fx-border-color:blue;-fx-background-radius:20px;-fx-border-radius:20px;");
			HBox srcB = new HBox(10);
			srcB.getChildren().addAll(src, srcC);
			
			Label trgt = new Label("Target: ");
			trgt.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			ComboBox<String> trgtC = new ComboBox<>();
			trgtC.setStyle("-fx-border-color:red;-fx-background-radius:20px;-fx-border-radius:20px;");
			
			trgtC.setPromptText("Target");
			HBox trgtB = new HBox(10);
			trgtB.getChildren().addAll(trgt, trgtC);
			
			Button run = new Button("Run");
			run.setStyle("-fx-background-radius:20;-fx-background-color:black;-fx-pref-width:300px;");
			run.setTextFill(Color.WHITE);
			
			run.setOnMouseEntered(ee->{
				run.setStyle("-fx-background-radius:20;-fx-background-color:white;-fx-border-color:black;-fx-border-radius:20px;-fx-pref-width:300");
				run.setTextFill(Color.BLACK);
			});
			run.setOnMouseExited(ee->{
				run.setStyle("-fx-background-radius:20;-fx-background-color:black;-fx-pref-width:300px;");
				run.setTextFill(Color.WHITE);
			});
			
			Label result = new Label("");
			
			VBox vbox = new VBox(20);
			vbox.getChildren().addAll(srcB, trgtB, run, result);
			vbox.setLayoutX(500);
			vbox.setLayoutY(100);
			
			
			
			run.setOnAction(e ->{
				redC.setVisible(false);
				blueC.setVisible(false);
				if(srcC.getValue() != null && trgtC.getValue() != null)
				{
					
					blueC.setLayoutX(newL[srcC.getItems().indexOf(srcC.getValue())].getLayoutX()+5);
					blueC.setLayoutY(newL[srcC.getItems().indexOf(srcC.getValue())].getLayoutY()+10);
					blueC.setVisible(true);
					
					
					redC.setLayoutX(newL[trgtC.getItems().indexOf(trgtC.getValue())].getLayoutX()+5);
					redC.setLayoutY(newL[trgtC.getItems().indexOf(trgtC.getValue())].getLayoutY()+10);
					redC.setVisible(true);
					
					newL[srcC.getItems().indexOf(srcC.getValue())].fire();
					newL[trgtC.getItems().indexOf(trgtC.getValue())].fire();
					
					
					System.out.println(srcC.getItems().indexOf(srcC.getValue()));
				}else {
					result.setText("chose");
				}
			});
			
			
			//
			
			Label path = new Label("Path: ");
			path.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			text = new TextArea("");
			
			text.setFont(Font.font("Arial", FontWeight.BOLD, 12));
			text.setStyle("-fx-border-color:black;-fx-padding:5 5 5 5;");
			text.setMaxHeight(100);
			text.setMaxWidth(350);
			
			Label distance = new Label("Distance: ");
			distance.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			text2 = new TextArea("");
			
			text2.setFont(Font.font("Arial", FontWeight.BOLD, 12));
			text2.setStyle("-fx-border-color:black;-fx-padding:5 5 5 5;");
			text2.setMaxHeight(50);
			text2.setMaxWidth(200);
			

			
			VBox vbox2 = new VBox(5);
			vbox2.getChildren().addAll(path, text, distance, text2);
			
			vbox2.setLayoutX(500);
			vbox2.setLayoutY(300);
			
			//button
			
			
			
			
			
			for(int i = 0 ; i < arr.length ; i++)
			{
				srcC.getItems().add(arr[i].name) ;
				trgtC.getItems().add(arr[i].name);
			}

			
			
			
			
			//___________________________
			

			g.getChildren().addAll(redC, blueC);
			redC.setVisible(false);
			blueC.setVisible(false);
			blueC.setFill(Color.BLUE);
			redC.setFill(Color.RED);
			blueC.setRadius(7);
			redC.setRadius(7);
			

			group.getChildren().addAll(map, stack, vbox, line, vbox2, g);

			
			
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//<<<<<<<<<<<<<<<<<<<<<<<<++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++>>>>>>>>>>>>>>>>>>>>>>>>
	
	int i;
	Node[] arr;
	String tempNameC;
	Button[] newL ;
	
	int check = 0;
	public void readFile() throws Exception
	{

		////
		File file = new File("C:\\Users\\CS Net Games\\Desktop\\WOW\\input.txt");
		Scanner scan = new Scanner(file);
		
		int numOfCity = scan.nextInt();
		int numOfAd = scan.nextInt();

		arr = new Node[numOfCity];
		newL = new Button[numOfCity];
		
		//read all citys and put on map
		for( i = 0 ; i < numOfCity ; i++)
		{
			String name = scan.next();
			double y = scan.nextDouble();
			double x = scan.nextDouble();
			
			Node newNode = new Node(name, false, Integer.MAX_VALUE, new Node(), x, y);
			arr[i] = newNode;
			
			
			
			newL[i] = new Button(name);
			newL[i].setTextFill(Color.BLACK);
			newL[i].setStyle("-fx-background-color:transparent;");
			newL[i].setLayoutX(((x-34.11839)/0.0050230263) - 10);
			newL[i].setLayoutY((33.34777-y)/0.0048763291);
			
			newL[i].setFont(Font.font(null, FontWeight.BOLD, 10));
			

			int index = i;			

			newL[i].setOnAction(e -> {
				
				artLine.getChildren().clear();

				if(check == 0){
					blueC.setVisible(false);
					redC.setVisible(false);
					try {
						readFile();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					System.out.println(i + " " + index);
					createDiaj(index);
					tempNameC = newL[index].getText();
					check = 1;
					text.setText("");
					blueC.setVisible(true);
					blueC.setLayoutX(newL[index].getLayoutX()+5);
					blueC.setLayoutY(newL[index].getLayoutY()+10);
					
					System.out.println(blueC.isVisible());
				}
				else {
					String s = gool(arr[index]);
					
//					tempNameC.equalsIgnoreCase(s.substring(5, tempNameC.length()+5)) &&
//					newL[index].getText().equalsIgnoreCase(s.substring(s.length()-newL[index].getText().length()))
//					
					redC.setVisible(true);
					redC.setLayoutX(newL[index].getLayoutX()+5);
					redC.setLayoutY(newL[index].getLayoutY()+10);

					
					System.out.println(s.length() + " " + tempNameC.length() + " " + s + " " + tempNameC);
					if(newL[index].getText().length()+5 < s.length()) {
						text.setText(s);
						text2.setText((int)(arr[index].dist+3.5)+ " km");
					}	
					else {
						text.setText("There is no road between the two cities");
						text2.setText("");
					}

					check = 0;
				}
			});
			
			System.out.println((x-34.11839)/0.0050230263);
			System.out.println((33.34777-y)/0.0048763291);
			
			newL[i].toFront();
			stack.getChildren().add(newL[i]);
		}//end for loop
		
		//____________________________________
		for(int i = 0 ; i < numOfAd ; i++)
		{
			String nameCity = scan.next();
			String nameAd = scan.next();
			
			System.out.println(nameAd + " " + nameCity);
			
			Node tempCity = null;
			Node tempAd = null;
			
			//.................
			for(int j=0 ; j < numOfCity ; j++)
			{
				if(arr[j].name.equalsIgnoreCase(nameCity))
				{
					tempCity = arr[j];
					
				}
				if(arr[j].name.equalsIgnoreCase(nameAd))
				{
					tempAd = arr[j];
				}
			}
			//.............
			
			tempCity.list.add(new adj(tempAd, (Math.sqrt((Math.abs(tempCity.x-tempAd.x)*Math.abs(tempCity.x-tempAd.x))+
														(Math.abs(tempCity.y-tempAd.y)*Math.abs(tempCity.y-tempAd.y))))));
			
			
		}
		//_____________________________________	
		
	}//end method


	
	public void createDiaj(int src)
	{
		arr[src].dist = 0;
		
		PriorityQueue<Node> pn = new PriorityQueue<>();
		pn.add(arr[src]);
		//..................................
		System.out.println();
		
		while(!pn.isEmpty())
		{
			
			Node temp = pn.poll();
			System.out.println(temp.name + " ffffffffff" + pn.size() + " " );


			temp.known = true;
			
			for(int i = 0 ; i < temp.list.size() ; i++)
			{
				if(temp.list.get(i).node.known == false)
				{
					if(temp.list.get(i).weight + temp.dist < temp.list.get(i).node.dist)
					{
						temp.list.get(i).node.dist = temp.list.get(i).weight + temp.dist;
						temp.list.get(i).node.path = temp;
						pn.add(temp.list.get(i).node);
					}
				}
			}
			System.out.println("..................");
		}
		System.out.println("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]");
	}
	
	//___________________________________________________________________________________________
	//___________________________________________________________________________________________
	//___________________________________________________________________________________________
	//___________________________________________________________________________________________
	
	
Pane artLine = new Pane();
	public String gool(Node node) {

//		if(node == null)
//			return "";
//		return  gool(node.path, name) + " --> " +  node.name;
		
		
		
		int count = 1;
		String str = "";
		while(node != null)
		{
			System.out.println(node.name + ";;;;;;;;;;");
			if(node.name != null) {
				str = " ==> " + node.name + str; 

				if(node.path.path != null)
				{
					Line line = new Line((((node.x-34.11839)/0.0050230263))-3, ((33.34777-node.y)/0.0048763291)+10,
							(((node.path.x-34.11839)/0.0050230263)),((33.34777-node.path.y)/0.0048763291)+10);
					line.setStrokeWidth(3);
					artLine.getChildren().add(line);
					line.setStyle("-fx-stroke: #939393;");
				}
				
			}
//			newL[i].setLayoutX(((x-34.11839)/0.0050230263) - 10);
//			newL[i].setLayoutY((33.34777-y)/0.0048763291);	

			count++;
			node = node.path;
		}
		System.out.println(str);
		
		return str;

	}
	
	//..........................................................................................
	
	public void reset()
	{
		for(int i = 0 ; i < arr.length ; i++)
		{
			arr[i].dist = Integer.MAX_VALUE;
			arr[i].known = false;
		}
	}
	
	
	
	
	
	private void zoomIn(Pane pane) {
	    Scale newScale = new Scale();
	    newScale.setX(pane.getScaleX() + .5);
	    newScale.setY(pane.getScaleY() + .5);
	    newScale.setPivotX(pane.getScaleX());
	    newScale.setPivotY(pane.getScaleY());
	    pane.getTransforms().add(newScale);
	}
	
	
	
	
	
	
	
	
	
}
