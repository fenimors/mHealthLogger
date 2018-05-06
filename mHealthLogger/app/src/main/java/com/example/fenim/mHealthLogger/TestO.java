package com.example.fenim.mHealthLogger;

import com.akshaykale.swipetimeline.TimelineObject;

/**
 * Created by akshaykale on 2017/08/22.
 */

public class TestO implements TimelineObject {

    public TestO(long timeline, String name, String url) {
        this.timeline = timeline;
        this.name = name;
        this.url = url;
        this.id = id;
    }

    long timeline;
    String name, url;
    int id;

    @Override
    public long getTimestamp() {
        return timeline;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getImageUrl() {
        return url;
    }
}