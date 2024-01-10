package solution;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

import javafx.animation.AnimationTimer;
import model.GameBoard;

public class Auto {
	
	private Node start;
	private Node end;
	public static int time = 0;
	LinkedList<Node> path;
	int x = 0;
	AnimationTimer gameTimer;
	
	public boolean run(){
		for(int i=0; i<8; i++) GameBoard.blocks[i].setDisable(true);
		path = new LinkedList<Node>();
		path = Solve(GameBoard.board, GameBoard.ANSBOARD);
		if(path == null) {
			System.out.println("no answer?");
			return false;
		}
		gameTimer = new AnimationTimer() {
			private long lastUpdate = 0 ;
			@Override
			public void handle(long now) {
				if (now - lastUpdate >= 250_000_000) {
					movePuzzle(path.get(x).getMove());
					if(x<path.size()) x++;
                    lastUpdate = now ;
                }
				if(x>=path.size()) {
					gameTimer.stop();
				}
			}
		};
		gameTimer.start();
		return true;
	}
		
	
	private LinkedList<Node> Solve(int[] source, int[] goal) {
		
		Queue<Node> queue = new LinkedList<Node>();
		TreeMap<Integer, Boolean> book = new TreeMap<Integer, Boolean>();
		
		end = new Node(goal, null, 0);
	    start = new Node(source, null, 0);
	    
	    queue.add(start);
	    book.put(start.ToSequence(), true);
	    
	    int endStatus = end.ToSequence();
	    
	    while (queue.size() > 0) {
	    	Node now = queue.poll();
//	    	System.out.println(now.ToSequence());//////////////////////////////////////////
	    	
	    	if (now.ToSequence() == endStatus)
	            return PathTrace(now);
	    	
	    	LinkedList<Node> nextPath = GetNext(now);
	    	for(Node p : nextPath) {
	    		int sign = p.ToSequence();
//	    		System.out.println(sign);	
	    		if (!book.containsKey(sign))
	            {                
	                queue.add(p);
	                book.put(sign, true);
	            }
	    	}
	    }
	    return null;
	}
	
	private LinkedList<Node> GetNext(Node now)
	{
	    int index = getBBlock(now.status);
	    int col = index % 3;
	    int row = index / 3;

	    LinkedList<Node> nextPush = new LinkedList<Node>();
//	    int[] next = new int[9];
//	    for(int i=0;i<9;i++)System.out.print(next[i]);System.out.println();

	    if (row != 0) // Top 
	    {
	    	int[] next = new int[9];
	    	System.arraycopy(now.status, 0, next, 0, now.status.length);
	        swap(next, index, index - 3);   
	        nextPush.add(new Node(next, now, next[index]));  /// before is correct, after add in this list is wrong
	      
	    }
	    if (col != 2) // Right
	    {
	    	int[] next = new int[9];
	    	System.arraycopy(now.status, 0, next, 0, now.status.length);      
	    	swap(next, index, index + 1); 
	        nextPush.add(new Node(next, now, next[index]));
	    }
	    if (row != 2) // Bottom
	    {
	    	int[] next = new int[9];
	    	System.arraycopy(now.status, 0, next, 0, now.status.length);      
	    	swap(next, index, index + 3); 
	        nextPush.add(new Node(next, now, next[index]));
	    }

	    if (col != 0) // Left
	    {
	    	int[] next = new int[9];
	    	System.arraycopy(now.status, 0, next, 0, now.status.length);        
	    	swap(next, index, index - 1); 
	        nextPush.add(new Node(next, now, next[index]));
	    }
//	    for(int i=0; i<nextPush.size(); i++) {
//	    	System.out.print(nextPush.get(i).ToSequence()+"\n");
//	    }
	    return nextPush;
	}
	
	private LinkedList<Node> PathTrace(Node now)
	{
		LinkedList<Node> path = new LinkedList<Node>();
	    while (now.father != null)
	    {
	        path.add(now);
	        now = now.father;
	    }
	    Collections.reverse(path);
	    return path;
	}
	private void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
	}
	
	private int getBBlock(int[] arr) {
		int a =0;
		while(a<9) {
			if(arr[a] == 9) {
				return a;
			}
			a++;
		}
		return -1;
	}

///////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////
	
	private void movePuzzle(int pos) {
		if(pos == -1) return;
		for(int i=0; i<8; i++) {
			if(GameBoard.blocks[i].getname() == pos && GameBoard.blocks[i].isMovable()) {
				GameBoard.blocks[i].exchangeBoard();
				GameBoard.blocks[i].exchangeBlock();
				break;
			}
		}
	}
}
