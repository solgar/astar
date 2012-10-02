package playground.astar;

public interface AStarDataProvider {
	public AStarNode nextNeighbour(AStarNode node);
	public AStarNode getStartNode();
	public AStarNode getDestinationNode();
}
