package dev.contursif.apobus.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

//import java.io.Serializable;

public final class BusStop implements Parcelable /*Serializable*/ {

    private static final byte PRESENT = 1;
    private static final byte NOT_PRESENT= 0;

    public static final Parcelable.Creator<BusStop> CREATOR = new Parcelable.Creator<BusStop>() {
        public BusStop createFromParcel(Parcel in) {
            return new BusStop(in);
        }

        public BusStop[] newArray(int size) {
            return new BusStop[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        if (direction != null) {
            dest.writeByte(PRESENT);
            dest.writeString(direction);
        } else {
            dest.writeByte(NOT_PRESENT);
        }
        if (latitude != 0.0f) {
            dest.writeByte(PRESENT);
            dest.writeFloat(latitude);
            dest.writeFloat(longitude);
        } else {
            dest.writeByte(NOT_PRESENT);
        }
    }
    private BusStop(Parcel in) {
        id = in.readString();
        name = in.readString();
        boolean present = in.readByte() == PRESENT;
        if (present){
            direction = in.readString();
        } else {
            direction = null;
        }
        present = in.readByte() == PRESENT;
        if (present){
            latitude = in.readFloat();
            longitude = in.readFloat();
        } else {
            latitude = 0.0f;
            longitude = 0.0f;
        }
    }
    public interface Keys {
        String ID = "id";
        String NAME = "name";
        String DIRECTION = "direction";
        String LATITUDE = "latitude";
        String LONGITUDE = "longitude";
    }

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

    public void toIntent(final Intent intent) {
        intent.putExtra(BusStop.Keys.ID, id);
        intent.putExtra(BusStop.Keys.NAME, name);
        intent.putExtra(BusStop.Keys.DIRECTION, direction);
        intent.putExtra(BusStop.Keys.LATITUDE, latitude);
        intent.putExtra(BusStop.Keys.LONGITUDE, longitude);
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

    public static BusStop fromIntent(final Intent inputIntent) {
        final String id = inputIntent.getStringExtra(BusStop.Keys.ID);
        final String name = inputIntent.getStringExtra(BusStop.Keys.NAME);
        final String direction = inputIntent.getStringExtra(BusStop.Keys.DIRECTION);
        final float latitude = inputIntent.getFloatExtra(BusStop.Keys.LATITUDE, 0.0f);
        final float longitude = inputIntent.getFloatExtra(BusStop.Keys.LONGITUDE, 0.0f);
        final BusStop busStop = BusStop.Builder.create(id, name)
                .withDirection(direction)
                .withLocation(latitude, longitude)
                .build();
        return busStop;
    }

}
