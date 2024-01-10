package solution;

public class Node {
    public int[] status; // Current status
    public Node father; // Previous status, null if the node is the root node
    private int move;
    
    public Node(int[] status, Node father, int move) {
        this.status = status;
        this.father = father;
        this.move = move;
    }
    public int ToSequence()
    {
        int result = 0;
        for (int i = 0; i < status.length; i++)
            result = result * 10 + status[i];
        return result;
    }
    public int getMove() {
    	return this.move;
    }
}
