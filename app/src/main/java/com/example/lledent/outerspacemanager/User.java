package com.example.lledent.outerspacemanager;

/**
 * Created by lledent on 16/01/2018.
 */

public class User {
    private String email;
    private String username;
    private String password;

    private Float gas;
    private int gasModifier;
    private Float minerals;
    private int mineralsModifier;
    private int points;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getGas() {
        return gas;
    }

    public void setGas(Float gas) {
        this.gas = gas;
    }

    public int getGasModifier() {
        return gasModifier;
    }

    public void setGasModifier(int gasModifier) {
        this.gasModifier = gasModifier;
    }

    public Float getMinerals() {
        return minerals;
    }

    public void setMinerals(Float minerals) {
        this.minerals = minerals;
    }

    public int getMineralsModifier() {
        return mineralsModifier;
    }

    public void setMineralsModifier(int mineralsModifier) {
        this.mineralsModifier = mineralsModifier;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public User() {}
}
