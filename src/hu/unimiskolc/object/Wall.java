package hu.unimiskolc.object;

import hu.unimiskolc.screen.GameScreen;

public class Wall extends GameObject
{
	public Wall()
	{
		setSymbol(DefaultSymbol.WALL.getSymbol());
	}

	public Wall(String symbol)
	{
		setSymbol(symbol);
	}

	public void addObject(GameScreen screen, int x, int y)
	{
		screen.setObject(this, x, y);
	}

	public void addObjectRow(GameScreen screen, int rowIndex)
	{
		for (int i = 0; i < screen.getWidth(); i++)
		{
			addObject(screen, i, rowIndex);
		}
	}

	public void addObjectColumn(GameScreen screen, int columnIndex)
	{
		for (int i = 0; i < screen.getHeight(); i++)
		{
			addObject(screen, columnIndex, i);
		}
	}

}
