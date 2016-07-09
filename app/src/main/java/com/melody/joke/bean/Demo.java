package com.melody.joke.bean;

import android.content.Intent;
import android.os.Parcel;

import com.melody.base.bean.Bean;

/**
 * description: demo bean
 * author: Melody
 * date: 2016/7/9
 * version: 0.0.0.1
 */
@SuppressWarnings("WeakerAccess, unused")
public class Demo extends Bean {

    private int type;

    private String title;

    private String description;

    private Intent intent;

    public Demo() {
    }

    public Demo(int type, String title, String description, Intent intent) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.intent = intent;
    }

    private Demo(Parcel in) {
        this.type = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.intent = in.readParcelable(Intent.class.getClassLoader());
    }

    private static final Creator<Demo> CREATOR = new Creator<Demo>() {
        @Override
        public Demo createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public Demo[] newArray(int size) {
            return new Demo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeParcelable(intent, flags);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
