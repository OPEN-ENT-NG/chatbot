package fr.openent.chatbot.core.enums;

public enum Events {
    ACCESS("ACCESS");
    private final String name;

    Events(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}