package application;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Optional;
import java.util.Random;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application {	


	private	int width = 500;
	private	int height = 500;
	Random random = new Random();
	World world = new World(width, height);
	private Button addAntButton = new Button();
	private Button addBeeButton = new Button();
	private Button addSpiderButton = new Button();
	private Button addFlowerButton = new Button();
	private Button addAppleButton = new Button();
	private Button addObstacleButton = new Button();
	private Group root = new Group();
	private Label label = new Label();
	private ImageView welcomImage = new ImageView(new Image("welcom.gif"));
	
	@Override
	public void start(final Stage stage) throws Exception {

		
		Image title = new Image("title.png");
		stage.getIcons().add(title);
		stage.setTitle("Bug World Game");	
		stage.setResizable(false);
		Scene scene = new Scene(root, 600, 500, Color.GRAY);
		
	
		
		//grass
		ImageView grassImage = new ImageView(new Image("grass.jpg"));
		grassImage.setFitWidth(width);
		grassImage.setFitHeight(height);
		root.getChildren().add(grassImage);
		
		Slider speed = new Slider(0, 100, 10);
		speed.setShowTickLabels(true);
		speed.setLayoutX(350);
		speed.setLayoutY(450);
		root.getChildren().add(speed);

		Button startButton = new Button();
		startButton.setText("Start Game");
		startButton.setLayoutX(500);
		startButton.setLayoutY(0);
		startButton.setPadding(new Insets(10,10,10,10));
		root.getChildren().add(startButton);
		
		addAntButton.setText("Add Ant");
		addAntButton.setLayoutX(500);
		addAntButton.setLayoutY(50);
		addAntButton.setPadding(new Insets(10,10,10,10));
		root.getChildren().add(addAntButton);
		
		addBeeButton.setText("Add Bee");
		addBeeButton.setLayoutX(500);
		addBeeButton.setLayoutY(100);
		addBeeButton.setPadding(new Insets(10,10,10,10));
		root.getChildren().add(addBeeButton);
		
		addSpiderButton.setText("Add Spider");
		addSpiderButton.setLayoutX(500);
		addSpiderButton.setLayoutY(150);
		addSpiderButton.setPadding(new Insets(10,10,10,10));
		root.getChildren().add(addSpiderButton);
			
		addFlowerButton.setText("Add Flower");
		addFlowerButton.setLayoutX(500);
		addFlowerButton.setLayoutY(200);
		addFlowerButton.setPadding(new Insets(10,10,10,10));
		root.getChildren().add(addFlowerButton);
		
		addAppleButton.setText("Add Apple");
		addAppleButton.setLayoutX(500);
		addAppleButton.setLayoutY(250);
		addAppleButton.setPadding(new Insets(10,10,10,10));
		root.getChildren().add(addAppleButton);
		
		addObstacleButton.setText("Add Obstacle");
		addObstacleButton.setLayoutX(500);
		addObstacleButton.setLayoutY(300);
		addObstacleButton.setPadding(new Insets(10,10,10,10));
		root.getChildren().add(addObstacleButton);
		
		Button pauseButton = new Button();
		pauseButton.setText("Pause Game");
		pauseButton.setLayoutX(500);
		pauseButton.setLayoutY(350);
		pauseButton.setPadding(new Insets(10,10,10,10));
		root.getChildren().add(pauseButton);
		
		Button restartButton = new Button();
		restartButton.setText("Restart Game");
		restartButton.setLayoutX(500);
		restartButton.setLayoutY(400);
		restartButton.setPadding(new Insets(10,10,10,10));
		root.getChildren().add(restartButton);
		
		Button quitButton = new Button();
		quitButton.setText("Quit Game");
		quitButton.setLayoutX(500);
		quitButton.setLayoutY(450);
		quitButton.setPadding(new Insets(10,10,10,10));
		root.getChildren().add(quitButton);		
		
		//Add start button to welcome	
		startButton.setOnAction(event ->{
				
			welcomImage.setFitWidth(width);
			welcomImage.setFitHeight(height);			
			label.setText(" TRY TO ADD SOMETHING!");
			label.setFont(Font.font(40));
			label.setTextFill(Color.BLUE);
			root.getChildren().add(label);	
			root.getChildren().add(welcomImage);	
			});
			
		//Add one ant randomly by press button (measure1:lambda)
		addAntButton.setOnAction(event ->{
			removeWelcomeImage(); 
			root.getChildren().add(addAnt());});
//		addAntButton.setOnAction(new EventHandler<ActionEvent>() {	//measure2	
//			@Override
//			public void handle(ActionEvent arg0) {
//				root.getChildren().remove(label);
//				root.getChildren().remove(welcomImage);
//				root.getChildren().add(addAnt());	
//			}
//		});		
		//Add one bee randomly by press button			
		addBeeButton.setOnAction(event ->{
			removeWelcomeImage(); 
			root.getChildren().add(addBee());});
		//Add one spider randomly by press button	
		addSpiderButton.setOnAction(event ->{
			removeWelcomeImage(); 
			root.getChildren().add(addSpider());});	
		//Add one flower randomly by press button	
		addFlowerButton.setOnAction(event ->{
			removeWelcomeImage(); 
			root.getChildren().add(addFlower());});
		//Add one apple randomly by press button	
		addAppleButton.setOnAction(event ->{
			removeWelcomeImage(); 
			root.getChildren().add(addApple());});
		//Add one obstacle randomly by press button	
		addObstacleButton.setOnAction(event ->{
			removeWelcomeImage(); 
			root.getChildren().add(addObstacle());});				

		//add button to quit and confirmation
		quitButton.setOnAction(event ->{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setHeaderText("QUIT GAME?");
	        Optional<ButtonType> buttonType = alert.showAndWait();
	        if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) { 

	            Platform.exit();
	        } 
			
		});
		

		KeyFrame frame = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {			
			
				for(Button bug: world.things) {
					if(bug instanceof Bug) {
						Bug b = (Bug)bug;	
						world.move(b);						
						world.touchPlant(b);
						b.setLayoutX(b.getX());
						b.setLayoutY(b.getY());					
						
					}
				}		
				removeDeadPlantAndRandomCreateOne();
			}	
			
		});

		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
		timeline.getKeyFrames().add(frame);
		timeline.play();
		
		speed.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				timeline.setRate(speed.getValue());			
			}	
		});
		
		//Add pause button
		pauseButton.setOnAction(event ->{
			timeline.pause();
		});
		//Add restart button 	
		restartButton.setOnAction(event ->{
			timeline.play();
		});
		
		stage.setScene(scene);
		stage.show();	
				
		
	}
	//add ant into the screen and user can click it to get its information
	private Ant addAnt() {
		
		Ant newAnt = new Ant("Ant", random.nextInt(width), random.nextInt(height), 10);
		world.addThing(newAnt);
		ImageView antImageView = new ImageView("Ant.png");
		antImageView.setFitWidth(20);
		antImageView.setFitHeight(20);
		newAnt.setGraphic(antImageView);
		newAnt.setLayoutX(newAnt.getX());
		newAnt.setLayoutY(newAnt.getY());	
		newAnt.setPadding(new Insets(0, 0, 0, 0));
		
		newAnt.setOnAction(event -> {
		root.getChildren().remove(label);							
		label.setFont(Font.font("verdama", 15));
		label.setTextFill(Color.BROWN);
		label.setText(newAnt.toString());
		root.getChildren().add(label);				
	});
		return newAnt;
	}
	//add bee into the screen and user can click it to get its information
	private Bee addBee() {
		
		Bee newBee = new Bee("Bee", random.nextInt(width), random.nextInt(height), 10);
		world.addThing(newBee);
		ImageView beeImageView = new ImageView("Bee.png");
		beeImageView.setFitWidth(20);
		beeImageView.setFitHeight(20);
		newBee.setGraphic(beeImageView);
		newBee.setLayoutX(newBee.getX());
		newBee.setLayoutY(newBee.getY());
		newBee.setPadding(new Insets(0, 0, 0, 0));

		newBee.setOnAction(event -> {
			root.getChildren().remove(label);							
			label.setFont(Font.font("verdama", 15));
			label.setTextFill(Color.BROWN);
			label.setText(newBee.toString());
			root.getChildren().add(label);				
		});
		
		return newBee;
	}
	//add spider into the screen and user can click it to get its information
	private Spider addSpider() {
		
		Spider newSpider = new Spider("Spider", random.nextInt(width), random.nextInt(height), 10);
		world.addThing(newSpider);
		ImageView spiderImageView = new ImageView("Spider.png");
		spiderImageView.setFitWidth(20);
		spiderImageView.setFitHeight(20);
		newSpider.setGraphic(spiderImageView);
		newSpider.setLayoutX(newSpider.getX());
		newSpider.setLayoutY(newSpider.getY());	
		newSpider.setPadding(new Insets(0, 0, 0, 0));
		
		newSpider.setOnAction(event -> {
			root.getChildren().remove(label);							
			label.setFont(Font.font("verdama", 15));
			label.setTextFill(Color.BROWN);
			label.setText(newSpider.toString());
			root.getChildren().add(label);				
		});
		return newSpider;
	}
	//add flower into the screen and user can click it to get its information
	private Flower addFlower() {
		
		Flower newFlower = new Flower("Flower", random.nextInt(width), random.nextInt(height), 10);
		world.addThing(newFlower);
		ImageView flowerImageView = new ImageView("Flower.png");
		flowerImageView.setFitWidth(20);
		flowerImageView.setFitHeight(20);
		newFlower.setGraphic(flowerImageView);
		newFlower.setLayoutX(newFlower.getX());
		newFlower.setLayoutY(newFlower.getY());		
		newFlower.setPadding(new Insets(0, 0, 0, 0));
		
		newFlower.setOnAction(event -> {
			root.getChildren().remove(label);							
			label.setFont(Font.font("verdama", 15));
			label.setTextFill(Color.BROWN);
			label.setText(newFlower.toString());
			root.getChildren().add(label);
		});
		return newFlower;
	}
	//add apple into the screen and user can click it to get its information
	private Apple addApple() {
		
		Apple newApple = new Apple("Apple", random.nextInt(width), random.nextInt(height), 10);
		world.addThing(newApple);
		ImageView appleImageView = new ImageView("Apple.png");
		appleImageView.setFitWidth(20);
		appleImageView.setFitHeight(20);
		newApple.setGraphic(appleImageView);
		newApple.setLayoutX(newApple.getX());
		newApple.setLayoutY(newApple.getY());	
		newApple.setPadding(new Insets(0, 0, 0, 0));
		
		newApple.setOnAction(event -> {
			root.getChildren().remove(label);							
			label.setFont(Font.font("verdama", 15));
			label.setTextFill(Color.BROWN);
			label.setText(newApple.toString());
			root.getChildren().add(label);
		});
		return newApple;
	}	
	//add obstacle into the screen and user can click it to get its information
	private Obstacle addObstacle() {
		
		Obstacle newObstacle = new Obstacle("Obstacle", random.nextInt(width), random.nextInt(height), 10);
		world.addThing(newObstacle);
		ImageView obstacleImageView = new ImageView("Obstacle.png");
		obstacleImageView.setFitWidth(20);
		obstacleImageView.setFitHeight(20);
		newObstacle.setGraphic(obstacleImageView);
		newObstacle.setLayoutX(newObstacle.getX());
		newObstacle.setLayoutY(newObstacle.getY());	
		newObstacle.setPadding(new Insets(0, 0, 0, 0));
		return newObstacle;
	}
	//remove the welcome picture 
	private void removeWelcomeImage() {
		root.getChildren().remove(label);
		root.getChildren().remove(welcomImage);
	}
	
	private void removeDeadPlantAndRandomCreateOne() {
		for(Button deadPlant: world.deadPlants) {
			root.getChildren().remove(deadPlant);
			world.things.remove(deadPlant);
			if(deadPlant instanceof Flower) {
				root.getChildren().add(addFlower());
				break;
			}else {
				root.getChildren().add(addApple());
				break;
			}
		}
		world.deadPlants.clear();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}




