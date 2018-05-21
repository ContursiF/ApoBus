package dev.contursif.apobus.model;


public final class ArrivalTime  {


    public final String id;

    public final String stopId;

    public final String direction;

    public final String route;

    public final long estimatedTime;


    private ArrivalTime(final String id, final String name, final String direction,
                        final String route, final long estimatedTime) {
        this.id = id;
        this.stopId = name;
        this.direction = direction;
        this.route = route;
        this.estimatedTime = estimatedTime;
    }

    public static class Builder {

        private String mId;
        private String mStopId;
        private String mDirection;
        private String mRoute;
        private long mArrivalTime;

        private Builder(final String id, final String stopId, final long arrivalTime) {
            this.mId = id;
            this.mStopId = stopId;
            this.mArrivalTime = arrivalTime;
        }

        public static Builder create(final String id, final String stopId, final long arrivalTime) {
            return new Builder(id, stopId, arrivalTime);
        }
        public Builder withDirection(final String direction) {
            this.mDirection = direction;
            return this;
        }

        public Builder withRoute(final String route) {
            this.mRoute = route;
            return this;
        }

        public ArrivalTime build() {
            return new ArrivalTime(mId, mStopId, mDirection, mRoute, mArrivalTime);
        }

    }

}
