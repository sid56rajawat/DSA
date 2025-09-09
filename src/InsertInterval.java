class InsertInterval {
  private static class Interval {
    public int start;
    public int end;
    public boolean isUsed;

    Interval(int start, int end) {
      this.start = start;
      this.end = end;
      this.isUsed = false;
    }

    public boolean collides(Interval i2) {
      if (this.start > i2.start) return i2.collides(this);
      return this.end >= i2.start;
    }

    public void update(Interval i2) {
      this.start = Math.min(this.start, i2.start);
      this.end = Math.max(this.end, i2.end);
    }

    public boolean comesAfter(Interval i2) {
      return this.start > i2.start;
    }
  }

  public int[][] insert(int[][] intervalsArray, int[] newIntervalArray) {
    List<Interval> intervals =
        Arrays.stream(intervalsArray).map(ints -> new Interval(ints[0], ints[1])).toList();

    Interval newInterval = new Interval(newIntervalArray[0], newIntervalArray[1]);

    List<Interval> updatedIntervals = new ArrayList<>();

    for (var interval : intervals) {
      if (newInterval.isUsed) {
        updatedIntervals.add(interval);
        continue;
      }
      if (newInterval.collides(interval)) {
        newInterval.update(interval);
        continue;
      }
      if (interval.comesAfter(newInterval)) {
        updatedIntervals.add(newInterval);
        newInterval.isUsed = true;
        updatedIntervals.add(interval);
        continue;
      }
      updatedIntervals.add(interval);
    }
    if(!newInterval.isUsed) updatedIntervals.add(newInterval);

    int[][] ans = new int[updatedIntervals.size()][2];
    AtomicInteger i = new AtomicInteger();
    updatedIntervals.forEach(interval -> {
        ans[i.get()] = new int[]{interval.start, interval.end};
        i.getAndIncrement();
    });
    return ans;
  }
}
