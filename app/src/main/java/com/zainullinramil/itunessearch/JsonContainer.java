package com.zainullinramil.itunessearch;

import java.util.ArrayList;

/**
 * Created by user on 31.03.2016.
 */
public class JsonContainer {

    ArrayList<ItemSearch> results;

    public void setResults(ArrayList<ItemSearch> results) {
        this.results = results;
    }

    public ArrayList<ItemSearch> getResults() {
        return results;
    }
}
