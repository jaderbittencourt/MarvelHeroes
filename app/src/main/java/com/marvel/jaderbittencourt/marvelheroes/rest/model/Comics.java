package com.marvel.jaderbittencourt.marvelheroes.rest.model;

import java.util.List;

public class Comics {

    List<Items> items;

    public Comics(List<Items> items) {
        this.items = items;
    }

    public List<Items> getItems() {
        return items;
    }
}
