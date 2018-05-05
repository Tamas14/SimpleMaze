package hu.unimiskolc.screen;

import hu.unimiskolc.game.SimpleMaze;
import hu.unimiskolc.object.GameObject;

public class GameScreen
{
	private int width, height;
	private char[][] matrix;

	public GameScreen(int width, int height)
	{
		this.width = width;
		this.height = height;
		matrix = new char[this.height][this.width];
	}

	public void init()
	{
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				matrix[i][j] = ' ';
			}
		}
	}

	public void printScreen()
	{
		String txt;

		for (int i = 0; i < height; i++)
		{
			txt = "";

			for (int j = 0; j < width; j++)
			{
				txt += matrix[i][j];
			}

			SimpleMaze.getCon().setLine(txt, i + 1);
		}

		SimpleMaze.getCon().update();
	}

	public char getObject(int x, int y)
	{
		return matrix[y][x];
	}

	public void setObject(GameObject obj, int x, int y)
	{
		matrix[y][x] = obj.getSymbol().charAt(0);
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
}
