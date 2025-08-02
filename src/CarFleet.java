public class CarFleet {
    // T.C -> O(target), S.C -> O(target)

    public int carFleet(int target, int[] position, int[] speed) {
        int totalCars = speed.length;
        float[] track = new float[target];

        for(var i=0; i<totalCars; i++) {
            float distance = (float) (target - position[i]);
            float time = distance / speed[i];
            track[position[i]] = time;
        }

        int totalFleets = 0;
        float fleetTime = 0;
        for(var i=target-1; i>=0; i--) {
            var time = track[i];
            if(time == 0) continue;

            if(time > fleetTime) {
                fleetTime = time;
                totalFleets++;
            }
        }

        return totalFleets;
    }
}
