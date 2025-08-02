import java.util.*;

public class DailyTemperatures {
	// T.C -> O(n), S.C -> O(n)
	private record Pair(int temp, int day) {}

	public int[] dailyTemperatures(int[] temperatures) {
		int days = temperatures.length;
		int lastDay = days - 1;
		int[] ans = new int[days];
		Stack<Pair> prevTemps = new Stack<>();

		for(var day=lastDay; day>=0; day--) {
			ans[day] = nextWarmerDay(temperatures[day], day, prevTemps) - day;
		}

		return ans;
	}

	private int nextWarmerDay(int temp, int day, Stack<Pair> prevTemps) {
		while(!prevTemps.isEmpty() && !(prevTemps.peek().temp > temp)) {
			prevTemps.pop();
		}
		int nextWarmDay = prevTemps.isEmpty() ? day : prevTemps.peek().day;
		prevTemps.push(new Pair(temp, day));
		return nextWarmDay;
	}
}
