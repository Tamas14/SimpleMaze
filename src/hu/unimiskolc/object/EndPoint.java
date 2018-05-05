package hu.unimiskolc.object;

public class EndPoint extends GameObject
{
	public EndPoint(int x, int y)
	{
		setX(x);
		setY(y);
		setSymbol(DefaultSymbol.ENDPOINT.getSymbol());
	}

	public boolean isAt(GameObject go)
	{
		if (getX() == go.getX() && getY() == go.getY())
			return true;

		return false;
	}
}