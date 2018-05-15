package hu.unimiskolc.screen;

import hu.unimiskolc.game.SimpleMaze;

public class Stats
{
	public static class StatData
	{
		int ellapsedTime;
		int steps;
		int stepDiff;
		double speed;

		public StatData(int ellapsedTime, int steps, int stepDiff, double speed)
		{
			this.ellapsedTime = ellapsedTime;
			this.steps = steps;
			this.stepDiff = stepDiff;
			this.speed = speed;
		}

		public int getEllapsedTime()
		{
			return ellapsedTime;
		}

		public int getSteps()
		{
			return steps;
		}

		public int getStepDiff()
		{
			return stepDiff;
		}

		public double getSpeed()
		{
			return speed;
		}

	}

	public static StatData calcStats()
	{
		int ellapsedTime = SimpleMaze.getEllapsedTime();
		int steps = SimpleMaze.getTotalMoves();
		int minSteps = SimpleMaze.getMinMoves() - 1;

		int stepDiff = steps - minSteps;
		double speed = steps / (double) ellapsedTime;

		return new StatData(ellapsedTime, minSteps, stepDiff, speed);
	}

}
