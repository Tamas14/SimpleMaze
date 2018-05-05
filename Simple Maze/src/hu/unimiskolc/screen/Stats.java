package hu.unimiskolc.screen;

import javax.swing.text.BadLocationException;

import hu.unimiskolc.game.SimpleMaze;

public class Stats
{
	Console con;

	public Stats()
	{
		con = SimpleMaze.getCon();

		calcStats();
	}

	public void calcStats()
	{
		int ellapsedTime = SimpleMaze.getEllapsedTime();
		int steps = SimpleMaze.getTotalMoves();
		int minSteps = SimpleMaze.getMinMoves() - 1;

		int stepDiff = steps - minSteps;
		double speed = steps / (double) ellapsedTime;

		try
		{
			con.printStats(ellapsedTime, steps, stepDiff, speed);
		} catch (BadLocationException e)
		{
			e.printStackTrace();
		}
	}

}
