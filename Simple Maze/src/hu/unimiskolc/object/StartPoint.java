package hu.unimiskolc.object;

public class StartPoint extends GameObject
{
	public StartPoint(int x, int y)
	{
		setX(x);
		setY(y);
		setSymbol(DefaultSymbol.START.getSymbol());
	}

	public boolean isAt(GameObject go)
	{
		if (getX() == go.getX() && getY() == go.getY())
			return true;

		return false;
	}
}
