package com.android.uas1;

public class NumberModel {

    int id;
    int minNumber;

    public NumberModel() {
    }

    public NumberModel(int id, int minNumber) {
        this.id = id;
        this.minNumber = minNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }
}
