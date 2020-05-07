package com.ostrov.mediaplayerapp;

class Song {
    private String artist;
    private String title;
    private String description;
    private int songResourceId;

    Song(int id, String artist, String title) {
        this.songResourceId = id;
        this.artist = artist;
        this.title = title;
        this.description = "";
    }

    Song(int id, String artist, String title, String description) {
        this.songResourceId = id;
        this.artist = artist;
        this.title = title;
        this.description = description;
    }

    int getSongResourceId() {
        return songResourceId;
    }

    String getArtist() {
        return artist;
    }

    String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }
}
