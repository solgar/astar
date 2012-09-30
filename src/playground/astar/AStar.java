package playground.astar;

import java.util.PriorityQueue;
import java.util.Vector;

public class AStar
{
	private PriorityQueue<AStarNode> openSet;
	private PriorityQueue<AStarNode> closedSet;
	private AStarDataProvider dataProvider;

	private int startX = -1;
	private int startY = -1;
	private int endX = -1;
	private int endY = -1;

	public AStar() {
		openSet = new PriorityQueue<AStarNode>();
		closedSet = new PriorityQueue<AStarNode>();
	}

	public void setStartingPoint(int x, int y) {
		startX = x;
		startY = y;
	}

	public void setDestinationPoint(int x, int y) {
		endX = x;
		endY = y;
	}

	public Vector<AStarNode> find() {
		AStarNode start = new AStarNode();
		boolean pathFound = false;
		start.x = startX;
		start.y = startY;
		openSet.add(start);

		while (!openSet.isEmpty()) {
			AStarNode current = openSet.poll();
			closedSet.add(current);

			if (current.x == endX && current.y == endY) {
				pathFound = true;
				break;
			}

			AStarNode neighbour = null;
			while ((neighbour = dataProvider.nextNeighbour(current)) != null) {
				if (neighbour.parent != null)
					continue;

				neighbour.parent = current;
				neighbour.steps = current.steps + 1;
				neighbour.distance = Math.sqrt(Math.pow(neighbour.x - endX, 2) + Math.pow(neighbour.y - endY, 2));
				openSet.add(neighbour);
			}
		}

		if (pathFound) {
			Vector<AStarNode> path = new Vector<AStarNode>();
			AStarNode current = closedSet.poll();
			while (current != start) {
				path.add(current);
				current = current.parent;
			}
			return path;
		}

		return null;
	}

}