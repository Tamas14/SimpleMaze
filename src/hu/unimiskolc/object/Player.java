package hu.unimiskolc.object;

import hu.unimiskolc.game.SimpleMaze;
import hu.unimiskolc.screen.GameScreen;

public class Player extends GameObject
{
	public Player()
	{
		setX(1);
		setY(1);
		setSymbol(DefaultSymbol.PLAYER.getSymbol());
	}

	public enum Direction
	{
		UP, DOWN, LEFT, RIGHT;
	}

	public void move(GameScreen s, Direction d)
	{
		if (!SimpleMaze.isGameRunning())
			return;

		switch (d)
		{
			case DOWN:
				if (s.getObject(getX(), getY() + 1) == DefaultSymbol.WALL.getSymbol().charAt(0))
					return;

				testEdgeCase(s);

				setY(getY() + 1);

				s.setObject(this, getX(), getY());
				s.printScreen();
				break;
			case LEFT:
				if (s.getObject(getX() - 1, getY()) == DefaultSymbol.WALL.getSymbol().charAt(0))
					return;

				testEdgeCase(s);

				setX(getX() - 1);

				s.setObject(this, getX(), getY());
				s.printScreen();
				break;
			case RIGHT:
				if (s.getObject(getX() + 1, getY()) == DefaultSymbol.WALL.getSymbol().charAt(0))
					return;

				testEdgeCase(s);

				setX(getX() + 1);

				s.setObject(this, getX(), getY());
				s.printScreen();
				break;
			case UP:
				if (s.getObject(getX(), getY() - 1) == DefaultSymbol.WALL.getSymbol().charAt(0))
					return;

				testEdgeCase(s);

				setY(getY() - 1);

				s.setObject(this, getX(), getY());
				s.printScreen();
				break;
			default:
				break;

		}

		s.setObject(this, getX(), getY());
		SimpleMaze.incTotalMoves();
		isPlayerAtTheEnd();
	}

	public void testEdgeCase(GameScreen s)
	{
		if (SimpleMaze.getStartPoint().isAt(this))
			s.setObject(SimpleMaze.getStartPoint(), getX(), getY());
		else
			s.setObject(new Path(), getX(), getY());
	}

	public void isPlayerAtTheEnd()
	{
		if (SimpleMaze.getEndPoint().isAt(this))
			SimpleMaze.setGameRunning(false);
	}

}
