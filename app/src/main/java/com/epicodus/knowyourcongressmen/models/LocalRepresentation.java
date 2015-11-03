package com.epicodus.knowyourcongressmen.models;

import java.util.ArrayList;

public class LocalRepresentation {

    private String mZipcode;
    private ArrayList<Representative> mRepresentatives;

    public LocalRepresentation() {
    }

    public String getZipcode() {
        return mZipcode;
    }

    public void setZipcode(String zipcode) {
        mZipcode = zipcode;
    }

    public ArrayList<Representative> getRepresentatives() {
        return mRepresentatives;
    }

    public void setRepresentatives(ArrayList<Representative> representatives) {
        mRepresentatives = representatives;
    }
}
