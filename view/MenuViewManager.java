package view;

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
import model.PuzzleButton;

public class MenuViewManager {
	private static final String BACKGROUND_IMAGE = "/resource/background.jpg";
	private static final int SCREEN_WIDTH = 500;
	private static final int SCREEN_HEIGHT = 600;
	private AnchorPane menuPane;
	private Scene menuScene;
	private Stage menuStage;
	Text text;
	
	public MenuViewManager() {
		menuPane = new AnchorPane();
		menuScene = new Scene(menuPane, SCREEN_WIDTH,SCREEN_HEIGHT);
		menuStage = new Stage();
		menuStage.setScene(menuScene);
		
		setBackground();
		createGameTitle();
		createStartButton();
	}
	
	private void createStartButton() {
		PuzzleButton StartButton = new PuzzleButton("START");
		StartButton.setLayoutX((SCREEN_WIDTH-120)/2);
		StartButton.setLayoutY((SCREEN_HEIGHT-60)/2);
		menuPane.getChildren().add(StartButton);
		
		StartButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				StartButton.setDisable(true);
				GameViewManager gameView = new GameViewManager();
				gameView.createNewGame(menuStage);
				
			}
		});
	}
	
	private void createGameTitle(){
    	text = new Text(110,100,"8 PUZZLE");
    	text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 50));
    	text.setFill(Color.BLUE);
    	text.setStrokeWidth(2);
    	menuPane.getChildren().add(text);
    }
	
	private void setBackground() {
		menuStage.setTitle("NUMBER PUZZLE");
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, SCREEN_WIDTH, SCREEN_HEIGHT, false, true), 
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		menuPane.setBackground(new Background(image));
	}
	
	public Stage getGameStage() {  
		return this.menuStage;
	}
}
