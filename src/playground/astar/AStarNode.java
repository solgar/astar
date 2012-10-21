package playground.astar;

public class AStarNode implements Comparable<AStarNode> {
	public int x;
	public int y;
	public int steps = 0;
	public double distance = 0;
	public double additionalCost = 0;
	public AStarNode parent = null;
	public double cost = Double.NaN;

	public AStarNode() {
		this(-1, -1);
	}

	public AStarNode(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double getCost() {
		return cost;
	}

	void calculateCost() {
		cost = steps + distance + additionalCost;
	}

	public int compareTo(AStarNode node) {
		if (node.cost < this.cost)
			return 1;
		else if (node.cost == this.cost)
			return 0;
		else
			return -1;
	}
}