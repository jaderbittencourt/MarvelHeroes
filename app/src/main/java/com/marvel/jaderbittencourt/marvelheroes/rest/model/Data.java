package com.marvel.jaderbittencourt.marvelheroes.rest.model;

import java.util.List;

public class Data {

    List<Hero> results;

    public Data(List<Hero> results) {
        this.results = results;
    }

    public List<Hero> getResults() {
        return results;
    }
}
