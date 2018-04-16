package com.example.lledent.outerspacemanager;

/**
 * Created by lledent on 16/04/2018.
 */

public class UserScore {
    private String username;
    private long points;

    public String getUsername() {
        return username;
    }

    public long getPoints() {
        return points;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public UserScore(String username, long points) {
        this.username = username;
        this.points = points;

    }
}
