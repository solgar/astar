package playground.astar;

public class AStarNode implements Comparable<AStarNode> {
	public int x;
	public int y;
	public int steps = 0;
	public double distance = 0;
	public AStarNode parent = null;

	public AStarNode() {
		this(-1, -1);
	}

	public AStarNode(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double getCost() {
		return steps + distance;
	}

	public int compareTo(AStarNode node) {
		double cost = node.getCost();
		double myCost = getCost();
		if (cost < myCost)
			return 1;
		else if (cost == myCost)
			return 0;
		else
			return -1;
	}
}