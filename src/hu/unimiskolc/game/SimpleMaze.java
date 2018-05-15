package hu.unimiskolc.game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.text.BadLocationException;

import hu.unimiskolc.maze.Generator;
import hu.unimiskolc.maze.Solver;
import hu.unimiskolc.maze.Solver.MazeData;
import hu.unimiskolc.object.EndPoint;
import hu.unimiskolc.object.Path;
import hu.unimiskolc.object.Player;
import hu.unimiskolc.object.StartPoint;
import hu.unimiskolc.object.Wall;
import hu.unimiskolc.screen.Console;
import hu.unimiskolc.screen.GameScreen;
import hu.unimiskolc.screen.Stats;
import hu.unimiskolc.screen.Stats.StatData;
import hu.unimiskolc.test.Test;

public class SimpleMaze
{
	private static boolean gameRunning;
	private static int ellapsedTime;
	private static int totalMoves;
	private static int minMoves;

	private static Console con;
	private static GameScreen gs;
	private static Generator gen;

	private static Wall wall;
	private static Path path;

	private static Player player;
	private static StartPoint startPoint;
	private static EndPoint endPoint;

	public static void init()
	{
		gameRunning = true;
		ellapsedTime = 0;
		totalMoves = 0;
		minMoves = 0;
		con = new Console();

		gs = new GameScreen(79, 23);
		gs.init();

		wall = new Wall();
		gen = new Generator(gs);

		int[][] mazeArray;
		MazeData mazeData;

		Solver maze = new Solver();

		do
		{
			mazeArray = gen.generateMaze();
		} while ((mazeData = maze.testMaze(mazeArray)) == null);

		startPoint = new StartPoint(mazeData.getStart().x, mazeData.getStart().y);
		minMoves = mazeData.getMinSteps();

		if (Test.START_CLOSE)
			startPoint = new StartPoint(mazeData.getEnd().x - 2, mazeData.getEnd().y - 2);

		endPoint = new EndPoint(mazeData.getEnd().x, mazeData.getEnd().y);

		path = new Path();

		for (int i = 0; i < mazeArray.length; i++)
		{
			for (int j = 0; j < mazeArray[i].length; j++)
			{
				if (mazeArray[i][j] == 1)
					wall.addObject(gs, i, j);
				else
					path.addObject(gs, i, j);
			}
		}

		if (Test.TESTING_MODE)
			maze.testMaze(mazeArray);

		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(new Runnable()
		{

			@Override
			public void run()
			{
				if (!gameRunning)
				{
					executor.shutdown();
					StatData data = Stats.calcStats();

					try
					{
						con.printStats(data.getEllapsedTime(), data.getSteps(), data.getStepDiff(), data.getSpeed());
					} catch (BadLocationException e)
					{
						e.printStackTrace();
					}
				}
				con.setLine("Time: " + ellapsedTime, 0, true);
				ellapsedTime++;
			}

		}, 0, 1, TimeUnit.SECONDS);

		player = new Player();
		gs.setObject(startPoint, startPoint.getX(), startPoint.getY());
		gs.setObject(endPoint, endPoint.getX(), endPoint.getY());

		player.setX(startPoint.getX());
		player.setY(startPoint.getY());

		gs.setObject(player, player.getX(), player.getY());

		gs.printScreen();
	}

	public static void incTotalMoves()
	{
		SimpleMaze.totalMoves++;
	}

	public static int getTotalMoves()
	{
		return totalMoves;
	}

	public static int getMinMoves()
	{
		return minMoves;
	}

	public static int getEllapsedTime()
	{
		return ellapsedTime;
	}

	public static Player getPlayer()
	{
		return player;
	}

	public static Console getCon()
	{
		return con;
	}

	public static StartPoint getStartPoint()
	{
		return startPoint;
	}

	public static EndPoint getEndPoint()
	{
		return endPoint;
	}

	public static GameScreen getGs()
	{
		return gs;
	}

	public static boolean isGameRunning()
	{
		return gameRunning;
	}

	public static void setGameRunning(boolean gameRunning)
	{
		SimpleMaze.gameRunning = gameRunning;
	}
}
