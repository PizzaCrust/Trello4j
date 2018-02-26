package online.pizzacrust.trello.impl;

import online.pizzacrust.trello.Identifiable;

public abstract class BasicIdentifiable implements Identifiable {

    protected String id;

    public BasicIdentifiable() {

    }

    public BasicIdentifiable(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
