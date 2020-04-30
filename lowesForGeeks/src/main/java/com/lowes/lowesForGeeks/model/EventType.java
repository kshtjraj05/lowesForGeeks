package com.lowes.lowesForGeeks.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EventType {
    Org("Org"), Team("Team"), Private("Private");

    String text;

    EventType(String aPrivate) {
        this.text = aPrivate;
    }

    @Override
    public String toString() {
        return text;
    }

    @JsonCreator
    public static EventType fromText(String text) {
        for (EventType e : EventType.values()) {
            if (e.getText().equals(text)) {
                return e;
            }
        }
        return null;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
