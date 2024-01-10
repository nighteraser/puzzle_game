package model;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;

public class GameBoard extends SubScene{
	
	private AnchorPane root2;
	public final static int UNIT = 110;
	private final static int WIDTH = UNIT * 3;
	private final static int HEIGHT = UNIT * 3;
	
	public static int B_x; // blank block position
	public static int B_y;
	public static int B_borad_ptr;
	
	public static Block[] blocks = new Block[8];
	
	public static final int[] ANSBOARD = {1,2,3,4,5,6,7,8,9}; // "none" = 9
	public static int[] board = {1,2,3,4,5,6,7,8,9};
	
	
	public GameBoard() {
		super(new AnchorPane(), WIDTH, HEIGHT);
		root2 = (AnchorPane) this.getRoot();
		root2.setStyle("-fx-background-color: #f5eac9;"+"-fx-background-radius: 10px; ");
		prefWidth(WIDTH);
		prefHeight(HEIGHT);
		setLayoutX((500-WIDTH)/2);
		setLayoutY(150);
		shuffleboard();
		createBlocks();
	}  
	
	public void createBlocks() {
		int k = 0;
		for (int i = 0; i < 9; i++) {
			if(board[i] != 9) {
				Block block = new Block(i%3, i/3, board[i]);
				block.setDisable(true);
				root2.getChildren().add(block);
				blocks[k++] = block;
			}else {
				B_x = i%3;
				B_y = i/3;
				B_borad_ptr = i;
			}
		}
	}
	
	private boolean check_is_solvable(ArrayList<Integer> arr) {
		int inversions = 0;
		for(int i=0; i<arr.size()-1; i++) {
			if(arr.get(i)==9) continue;
			for(int j=i+1; j<arr.size(); j++) {
				if(arr.get(j)==9) continue;
				if(arr.get(j)>arr.get(i)) inversions++;
			}
		}
		if (inversions%2==1) return true;
		return false;
	}
	
	public void shuffleboard() {
		ArrayList<Integer> initialArray = new ArrayList<Integer>();
		
		for (int i = 1; i < 10; i++) initialArray.add(i);
		
		do {
			Collections.shuffle(initialArray);
		}while(check_is_solvable(initialArray));
		
	    for (int i = 0; i < board.length; i++) board[i] = initialArray.get(i);
	    initialArray.clear();
	}
}
