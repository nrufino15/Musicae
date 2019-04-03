package com.example.musicae;

public class Music {
    private String name;
    private String artist;
    private int song;

    public Music(String name, String artist, int song) {
        this.name = name;
        this.artist = artist;
        this.song = song;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }
}
