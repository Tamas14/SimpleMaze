package hu.unimiskolc.object;

public class Path extends Wall
{
	public Path()
	{
		setSymbol(DefaultSymbol.PATH.getSymbol());
	}

	public Path(String symbol)
	{
		setSymbol(symbol);
	}
}
