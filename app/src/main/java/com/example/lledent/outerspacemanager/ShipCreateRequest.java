package com.example.lledent.outerspacemanager;

/**
 * Created by lledent on 16/04/2018.
 */

public class ShipCreateRequest {
    private int id;
    private int amount;

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ShipCreateRequest(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}
