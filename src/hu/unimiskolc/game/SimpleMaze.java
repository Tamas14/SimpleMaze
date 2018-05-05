package hu.unimiskolc.game;

import hu.unimiskolc.generator.Generator;
import hu.unimiskolc.generator.Maze;
import hu.unimiskolc.generator.Maze.MazeData;
import hu.unimiskolc.object.EndPoint;
import hu.unimiskolc.object.Path;
import hu.unimiskolc.object.Player;
import hu.unimiskolc.object.StartPoint;
import hu.unimiskolc.object.Wall;
import hu.unimiskolc.screen.Console;
import hu.unimiskolc.screen.GameScreen;
import hu.unimiskolc.screen.Stats;

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

	// public SimpleMaze()
	// {
	// init();
	// }

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

		Maze maze = new Maze();

		do
		{
			mazeArray = gen.generateMaze();
		} while ((mazeData = maze.testMaze(mazeArray)) == null);

		startPoint = new StartPoint(mazeData.getStart().x, mazeData.getStart().y);
		minMoves = mazeData.getMinSteps();
		// For testing
		// startPoint = new StartPoint(mazeData.getEnd().x - 2, mazeData.getEnd().y -
		// 2);
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

		// maze.testMaze(mazeArray);

		Thread t = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (gameRunning)
				{
					try
					{
						con.setLine("Time: " + ellapsedTime, 0, true);
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}

					ellapsedTime++;
				}

				new Stats();
			}
		});

		t.start();

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
