package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import view.GameViewManager;

public class Block extends ImageView{
	
	private static final int UNIT = GameBoard.UNIT;
	private int name;
	private int x;
	private int y;
	
	public Block(int x, int y, int name){
		setLayoutX(x*UNIT);
		setLayoutY(y*UNIT);
		setImage(new Image(String.format("resource/0%d.png", name)));
		setPickOnBounds(true);
		Rectangle clip = new Rectangle(UNIT, UNIT);
        clip.setArcWidth(20);
        clip.setArcHeight(20);

        setClip(clip);
		
		setOnMouseClicked((MouseEvent e) -> {
			
			if(isMovable()) {
				exchangeBoard();
				exchangeBlock();
				if(checkgame()) {
					System.out.println("game over!!!");
                    for(int i=0; i<8; i++) GameBoard.blocks[i].setDisable(true);
                    GameViewManager.gameOver = true;
				}
			}
	    });
		this.name = name;
		this.x = x;
		this.y = y;
	}
	public static boolean checkgame(){
		for(int i=0; i<9; i++) {
			if(GameBoard.board[i] != GameBoard.ANSBOARD[i])
				return false;
		}
		return true;
	}
	public void exchangeBoard(){
		for(int i=0; i<9; i++) {
			if(GameBoard.board[i] == this.name) { 
				GameBoard.board[i] = 9;
				GameBoard.board[GameBoard.B_borad_ptr] = this.name;
				GameBoard.B_borad_ptr = i;
				break;
			}
		}
	}
	
	public boolean isMovable() {
		if(GameBoard.B_x == this.x+1 && GameBoard.B_y == this.y) {
			return true;
		}
		if(GameBoard.B_x == this.x-1 && GameBoard.B_y == this.y) {
			return true;
		}
		if(GameBoard.B_x == this.x && GameBoard.B_y == this.y+1) {
			return true;
		}
		if(GameBoard.B_x == this.x && GameBoard.B_y == this.y-1) {
			return true;
		}
		return false;
	}
	
	public void exchangeBlock() {
		int x = this.x;
		int y = this.y;
		this.x = GameBoard.B_x;
		this.y = GameBoard.B_y;
		setLayoutX(GameBoard.B_x*UNIT);
		setLayoutY(GameBoard.B_y*UNIT);
		GameBoard.B_x = x;
		GameBoard.B_y = y;
	}
	public int getname() {
		return this.name;
	}
	public int getx() {
		return this.x;
	}
	public int gety() {
		return this.y;
	}
}
