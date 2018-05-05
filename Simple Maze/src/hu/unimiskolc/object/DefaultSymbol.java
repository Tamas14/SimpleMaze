package hu.unimiskolc.object;

public enum DefaultSymbol
{
	WALL("◼"), PATH("⛶"), PLAYER("◉"), START("▣"), ENDPOINT("▣");

	private String symbol;

	private DefaultSymbol(String symbol)
	{
		this.symbol = symbol;
	}

	public String getSymbol()
	{
		return symbol;
	}
}
