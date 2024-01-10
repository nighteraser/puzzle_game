package view;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameBoard;
import model.PuzzleButton;
import solution.Auto;

public class GameViewManager {
	
	private final String BACKGROUND_IMAGE = "/resource/background.jpg";
	private final int SCREEN_WIDTH = 500;
	private final int SCREEN_HEIGHT = 600;
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private Stage menuStage;
	private GameBoard boradGameBoard;

	AnimationTimer gameTimer;
	public static boolean gameOver = false; 
	public static boolean auto = false;
	
	private int sec = 0;
	long last = 0;
	boolean st = true;
	Text text;
	
	public GameViewManager(){
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, SCREEN_WIDTH,SCREEN_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
		createAutoButton();
	}
	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		gameStage.show();
		
		setBackground();
		createGameTitle();
		createboradGameBoard();
		createGameTimer();
		createExitButton();
	}
	
	private void createGameTimer() {
		for(int i=0; i<8; i++) GameBoard.blocks[i].setDisable(false);
		gameTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if(st) { last = now; st = false;}
				if(gameOver) {
					gameTimer.stop();
					createExitButton();
				}else {
					sec = (int) ((now-last)/1000000000);
					text.setText(String.format("Time:%02d:%02d", sec/60, sec));
				}
			}
		};
		gameTimer.start();
	}
	
	private void createGameTitle(){
    	text = new Text(90,100,"Time:00:00");
    	text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 50));
    	text.setFill(Color.BLUE);
    	text.setStrokeWidth(2);
    	gamePane.getChildren().add(text);
    }
	
	private void createboradGameBoard() {
		boradGameBoard = new GameBoard();
		gamePane.getChildren().add(boradGameBoard);
	}
	
	private void createExitButton() {
		PuzzleButton exitButton = new PuzzleButton(" EXIT ");
		exitButton.setLayoutX(SCREEN_WIDTH-140);
		exitButton.setLayoutY(SCREEN_HEIGHT-80);
		gamePane.getChildren().add(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				gameOver = true;
				gameStage.close();
			}
		});
	}
	
	private void createAutoButton() {
		PuzzleButton autoButton = new PuzzleButton("AUTO");
		autoButton.setLayoutX(50);
		autoButton.setLayoutY(SCREEN_HEIGHT-80);
		gamePane.getChildren().add(autoButton);
		
		autoButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Auto a = new Auto();
				if(!gameOver) {
					text.setText("AUTO MODE");
					gameOver = true;
					a.run();
				}
				autoButton.setDisable(true);
				createExitButton();
			}
		});
	}
	
	private void setBackground() {
		gameStage.setTitle("NUMBER PUZZLE");
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, SCREEN_WIDTH, SCREEN_HEIGHT, false, true), 
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		gamePane.setBackground(new Background(image));
	}
	
	public Stage getGameStage() {  
		return this.gameStage;
	}
}
