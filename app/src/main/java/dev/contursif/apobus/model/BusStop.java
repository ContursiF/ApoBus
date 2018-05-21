package dev.contursif.apobus.model;

public final class BusStop {

    public final String id;

    public final String name;

    public final String direction;

    public final float latitude;

    public final float longitude;

    private BusStop (final String id, final String name, final String direction, final float latitude, final float longitude){

        this.id = id;
        this.name = name;
        this.direction = direction;
        this.longitude = longitude;
        this.latitude= latitude;
    }

    public static class Builder{

        private String mId;
        private String mName;
        private String mDirection;
        private float mLatitude;
        private float mLongitute;
        private Builder (final String id, final String name){
            this.mId = id;
            this.mName = name;
        }


        public static Builder create(final String id, final String name){
            return new Builder(id, name);
        }

        public Builder withDirection (final String direction){
            this.mDirection = direction;
            return this;
        }

        public Builder withLocation (final Float latitude, final Float longitude){
            this.mLatitude = latitude;
            this.mLongitute = longitude;
            return this;
        }

        public BusStop build(){
            return new BusStop(mId, mName, mDirection, mLatitude, mLongitute);
        }
    }

}
