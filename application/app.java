package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MenuViewManager;

public class app extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
//			GameViewManager manager = new GameViewManager();
			MenuViewManager manager = new MenuViewManager();
			primaryStage = manager.getGameStage();
			primaryStage.setTitle("NUMBER PUZZLE");
			primaryStage.setResizable(false);
			primaryStage.show(); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] agrs) {
		launch();
	}
}
