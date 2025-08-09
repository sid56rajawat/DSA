class KClosestPointsToOrigin {
    // T.C -> O(n * log(k)), S.C -> O(k)
    private record Coordinate(int x, int y) {}

    private record Distance(double distance, Coordinate coordinate) {}

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Distance> distances = new PriorityQueue<Distance>(k, this::compareDistance);
        Arrays.stream(points).forEach(point -> {
            Coordinate coordinate = new Coordinate(point[0], point[1]);
            Distance distance = distanceFromOrigin(coordinate);
            
            if(distances.size() == k) {
                if(distance.distance < distances.peek().distance) {
                    distances.poll(); 
                }
                else return;
            }
            distances.offer(distance);
        });

        int[][] kClosest = new int[k][2];
        Iterator<Distance> iterator = distances.iterator();

        int i = 0;
        while(iterator.hasNext()) {
            Distance d = iterator.next();
            Coordinate c = d.coordinate;
            kClosest[i] = new int[]{c.x, c.y};
            i++;
        }

        return kClosest;
    }

    private int compareDistance(Distance d1, Distance d2) {
        return Double.compare(d2.distance, d1.distance);
    }

    private Distance distanceFromOrigin(Coordinate c) {
        double eucledianDistance = Math.sqrt((c.x * c.x) + (c.y * c.y));
        return new Distance(eucledianDistance, c);
    }
}
