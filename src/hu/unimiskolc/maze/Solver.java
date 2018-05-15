package hu.unimiskolc.maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import hu.unimiskolc.game.SimpleMaze;
import hu.unimiskolc.object.Path;
import hu.unimiskolc.test.Test;

public class Solver
{
	public class MazeData
	{
		private Point start;
		private Point end;
		private int minSteps;

		public MazeData(Point start, Point end, int minSteps)
		{
			this.start = start;
			this.end = end;
			this.minSteps = minSteps;
		}

		public Point getStart()
		{
			return start;
		}

		public Point getEnd()
		{
			return end;
		}

		public int getMinSteps()
		{
			return minSteps;
		}
	}

	public MazeData testMaze(int[][] maze)
	{
		List<Cell> path = findLongestPath(maze);

		if (path == null)
		{
			return null;
		}

		// To show path
		if (Test.TESTING_MODE)
		{
			for (Cell c : path)
				SimpleMaze.getGs().setObject(new Path("▩"), c.row, c.col);
		}

		return new MazeData(new Point(path.get(0).row, path.get(0).col),
				new Point(path.get(path.size() - 1).row, path.get(path.size() - 1).col), path.size());
	}

	private static List<Cell> findLongestPath(int[][] maze)
	{
		Cell start = new Cell(1, 1);
		Cell end = new Cell(maze.length - 2, maze[0].length - 2);

		List<Cell> path = findLongestPath(maze, start, end);
		return path;
	}

	private static List<Cell> findLongestPath(int[][] maze, Cell start, Cell end)
	{
		List<Cell> result = null;
		int rows = maze.length;
		int cols = maze[0].length;
		if (start.row < 0 || start.col < 0)
			return null;
		if (start.row == rows || start.col == cols)
			return null;
		if (maze[start.row][start.col] == 1)
			return null;
		if (start.equals(end))
		{
			List<Cell> path = new ArrayList<Cell>();
			path.add(start);
			return path;
		}

		maze[start.row][start.col] = 1;
		Cell[] nextCells = new Cell[4];
		nextCells[0] = new Cell(start.row + 1, start.col);
		nextCells[2] = new Cell(start.row, start.col + 1);
		nextCells[1] = new Cell(start.row - 1, start.col);
		nextCells[3] = new Cell(start.row, start.col - 1);
		int maxLength = -1;
		for (Cell nextCell : nextCells)
		{
			List<Cell> path = findLongestPath(maze, nextCell, end);
			if (path != null && path.size() > maxLength)
			{
				maxLength = path.size();
				path.add(0, start);
				result = path;
			}
		}

		maze[start.row][start.col] = 0;
		if (result == null || result.size() == 0)
			return null;
		return result;
	}

	private static class Cell
	{
		public int row;
		public int col;

		public Cell(int row, int column)
		{
			this.row = row;
			this.col = column;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if ((obj == null) || (obj.getClass() != this.getClass()))
				return false;
			Cell cell = (Cell) obj;
			if (row == cell.row && col == cell.col)
				return true;
			return false;
		}

		@Override
		public String toString()
		{
			return "(" + row + "," + col + ")";
		}
	}

}
