package hu.unimiskolc.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import hu.unimiskolc.screen.GameScreen;

public class Generator
{
	GameScreen screen;
	int width, height;

	public Generator(GameScreen gs)
	{
		this.screen = gs;
		this.width = screen.getWidth();
		this.height = screen.getHeight();
	}

	int[][] maze;

	public int[][] generateMaze()
	{
		maze = new int[width][height];
		// Initialize
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				maze[i][j] = 1;

		Random rand = new Random();

		int r = rand.nextInt(width);
		while (r % 2 == 0)
		{
			r = rand.nextInt(width);
		}

		int c = rand.nextInt(height);
		while (c % 2 == 0)
		{
			c = rand.nextInt(height);
		}

		maze[r][c] = 0;

		recursion(r, c);

		return maze;
	}

	public void recursion(int r, int c)
	{
		// 4 random directions
		Integer[] randDirs = generateRandomDirections();
		// Examine each direction
		for (int i = 0; i < randDirs.length; i++)
		{

			switch (randDirs[i])
			{
				case 1: // Up
					if (r - 2 <= 0)
						continue;
					if (maze[r - 2][c] != 0)
					{
						maze[r - 2][c] = 0;
						maze[r - 1][c] = 0;
						recursion(r - 2, c);
					}
					break;
				case 2: // Right
					if (c + 2 >= height - 1)
						continue;
					if (maze[r][c + 2] != 0)
					{
						maze[r][c + 2] = 0;
						maze[r][c + 1] = 0;
						recursion(r, c + 2);
					}
					break;
				case 3: // Down
					if (r + 2 >= width - 1)
						continue;
					if (maze[r + 2][c] != 0)
					{
						maze[r + 2][c] = 0;
						maze[r + 1][c] = 0;
						recursion(r + 2, c);
					}
					break;
				case 4: // Left
					if (c - 2 <= 0)
						continue;
					if (maze[r][c - 2] != 0)
					{
						maze[r][c - 2] = 0;
						maze[r][c - 1] = 0;
						recursion(r, c - 2);
					}
					break;
			}
		}

	}

	public Integer[] generateRandomDirections()
	{
		ArrayList<Integer> randoms = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++)
			randoms.add(i + 1);
		Collections.shuffle(randoms);

		return randoms.toArray(new Integer[4]);
	}

}
