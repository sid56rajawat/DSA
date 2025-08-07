class TimeBasedKeyValueStore {
    private record Pair(String value, int timestamp) {}

    private Map<String, List<Pair>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    // T.C -> O(1)
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)) map.put(key, new ArrayList<Pair>());
        List<Pair> array = map.get(key);
        array.add(new Pair(value, timestamp));
    }
    
    // T.C -> O(log(max_timestamp))
    public String get(String key, int timestamp) {
        if(!map.containsKey(key)) return "";
        List<Pair> array = map.get(key);
        return closestSmaller(array, timestamp);
    }

    private String closestSmaller(List<Pair> array, int timestamp) {
        int left = 0, right = array.size() - 1;
        while(left <= right) {
            int mid = (right + left) / 2;
            Pair midP = array.get(mid);
            if(timestamp == midP.timestamp) {
                return midP.value;
            } else if(timestamp > midP.timestamp) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right == -1 ? "" : array.get(right).value;
    }
}
