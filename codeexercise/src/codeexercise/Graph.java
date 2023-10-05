package codeexercise;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

	private ArrayList<String> vertexList; // the collection of vertexs
	private static int[][] edges; // the edges 's matrix 
	private int numOfEdges; // the number of edges
	private boolean[] isVisited;

	public Graph(int n) {
		// init matrix and vertexList
		edges = new int[n][n];
		vertexList = new ArrayList<String>(n);
		numOfEdges = 0;

	}

	public static int[][] getcurrentedges() {

		return edges;
	}

	public int getNumOfVertex() {
		return vertexList.size();
	}

	// Display the graph matrix
	public void showGraph() {
		for (int[] link : edges) {
			System.err.println(Arrays.toString(link));
		}
	}

	// Get the number of Edges
	public int getNumOfEdges() {
		return numOfEdges;
	}

	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}

	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}


	// return  0->"A" 1->"B" 2->"C"
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}

	// return the v2 and v1 's weight
	public int getWeight(int v1, int v2) {
		return edges[v1][v2];
	}


	/**
	 * @param matrix: an integer matrix
	 * @return: the length of the longest increasing path
	 */
	public int longestIncreasingPath(int[][] matrix) {
		// write your code here
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int m = matrix.length, n = matrix[0].length;

		boolean[][] visited = new boolean[m][n];
		int[][] memo = new int[m][n];
		int[] directions = { 0, 1, 0, -1, 0 };
		int res = 0;

		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				res = Math.max(res, dfs(i, j, matrix, visited, memo, directions));
			}
		}

		return res;
	}

	// Function that returns the longest path
	private int dfs(int x, int y, int[][] matrix, boolean[][] visited, int[][] memo, int[] directions) {

		if (visited[x][y]) {
			return memo[x][y];
		}

		int m = matrix.length, n = matrix[0].length;

		int res = 1;
		for (int i = 0; i < 4; ++i) {
			int nx = x + directions[i];
			int ny = y + directions[i + 1];

			if (nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[nx][ny] > matrix[x][y]) {
				res = Math.max(res, 1 + dfs(nx, ny, matrix, visited, memo, directions));
			}
		}

		visited[x][y] = true;
		memo[x][y] = res;
		return res;
	}

	public static void main(String[] args) {
		int n = 8; 
		String Vertexs[] = { "1", "2", "3", "4", "5", "6", "7", "8" };

		// Create a graph object
		Graph graph = new Graph(n);
		// Add the vertexs 
		for (String vertex : Vertexs) {
			graph.insertVertex(vertex);
		}

		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		graph.insertEdge(3, 7, 1);
		graph.insertEdge(4, 7, 1);
		graph.insertEdge(2, 5, 1);
		graph.insertEdge(2, 6, 1);
		graph.insertEdge(5, 6, 1);

		System.out.println("*****Display the Graph Matrix******");
		graph.showGraph();
		int result = graph.longestIncreasingPath(getcurrentedges());
		System.out.println("*****Display the longest Directed path from the vertex*****");
		System.out.print(result);
		
	}

}
