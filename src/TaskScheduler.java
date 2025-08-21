class TaskScheduler {
    // T.C -> O(tasks * n), S.C -> O(tasks)

    private record TaskFrequency(Integer task, Integer freq) {}

    public int leastInterval(char[] tasks, int n) {
        int cpuInterval = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        (new String(tasks)).chars().forEach(task -> freq.put(task, freq.getOrDefault(task, 0) + 1));
        PriorityQueue<TaskFrequency> tasksPQ = new PriorityQueue<>(this::compareTaskFrequency);

        freq.forEach((t, f) -> tasksPQ.offer(new TaskFrequency(t, f)));

        while(!tasksPQ.isEmpty()) {
            List<TaskFrequency> temp = new LinkedList<>();
            for(int i=n; i>=0; i--) {
                if(tasksPQ.isEmpty() && temp.isEmpty()) return cpuInterval;
                cpuInterval++;

                TaskFrequency mostFrequentTask = tasksPQ.poll();
                if(mostFrequentTask != null && mostFrequentTask.freq > 1) 
                    temp.add(new TaskFrequency(mostFrequentTask.task(), mostFrequentTask.freq() - 1));
            }
            temp.forEach(tasksPQ::offer);
        }
        return cpuInterval;
    }

    private int compareTaskFrequency(TaskFrequency t1, TaskFrequency t2) {
        return Integer.compare(t2.freq(), t1.freq());
    }
}
