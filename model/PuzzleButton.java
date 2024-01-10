package model;

import javafx.scene.control.Button;

public class PuzzleButton extends Button{
	
	public PuzzleButton(String text) {
		setText(text);
		setStyle("-fx-font-size: 2em; -fx-font-weight: bold; -fx-background-color: #9ff090;");
		setMaxSize(120, 60);
	}
}
