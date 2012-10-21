package playground.astar;

import java.util.PriorityQueue;
import java.util.Vector;

public class AStar
{
	private PriorityQueue<AStarNode> openSet;
	private PriorityQueue<AStarNode> closedSet;
	private AStarDataProvider dataProvider;

	public AStar(AStarDataProvider dataProvider) {
		this.dataProvider = dataProvider;
		openSet = new PriorityQueue<AStarNode>();
		closedSet = new PriorityQueue<AStarNode>();
	}

	public Vector<AStarNode> find() {
		AStarNode startNode = dataProvider.getStartNode();
		AStarNode destinationNode = dataProvider.getDestinationNode();
		AStarNode lastNode = null;
		boolean pathFound = false;
		int endX = destinationNode.x;
		int endY = destinationNode.y;

		openSet.clear();
		closedSet.clear();
		openSet.add(startNode);

		while (!openSet.isEmpty()) {
			AStarNode current = openSet.poll();
			closedSet.add(current);
			lastNode = current;

			if (current == destinationNode) {
				lastNode = current;
				pathFound = true;
				break;
			}

			AStarNode neighbour = null;
			while ((neighbour = dataProvider.nextNeighbour(current)) != null) {
				if (neighbour.parent != null || neighbour == startNode) {
					continue;
				}

				neighbour.parent = current;
				neighbour.steps = current.steps + 1;
				neighbour.distance = Math.sqrt(Math.pow(neighbour.x - endX, 2) + Math.pow(neighbour.y - endY, 2));
				neighbour.calculateCost();
				openSet.add(neighbour);
			}
		}

		if (pathFound) {
			Vector<AStarNode> path = new Vector<AStarNode>();
			AStarNode current = lastNode;
			while (current != startNode) {
				path.add(current);
				current = current.parent;
			}
			return path;
		}

		return null;
	}

}