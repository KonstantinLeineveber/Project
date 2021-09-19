package com.tms.springapp.model.film;

import org.jetbrains.annotations.NotNull;

public enum Genre {
    ACTION, ADVENTURE, ANIMATED,
    COMEDY, DRAMA, FANTASY, HISTORICAL,
    HORROR, SCIENCE_FICTION, THRILLER, WESTERN;

    @Override
    public String toString() {
        return firstUpperCase();
    }

    @NotNull
    public String firstUpperCase() {
        return this.name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }

}
