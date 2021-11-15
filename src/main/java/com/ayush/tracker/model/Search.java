package com.ayush.tracker.model;

public class Search {
    private String from;
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Search{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
