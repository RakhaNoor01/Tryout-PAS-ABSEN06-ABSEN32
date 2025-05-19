package com.example.tryout_pas.model;

public class HoloMember {
    private int id;
    private String name;
    private String nickname;
    private String generation;
    private String imageUrl;

    public HoloMember() {} // Default constructor for Gson

    public int getId() { return id; }
    public String getName() { return name; }
    public String getNickname() { return nickname; }
    public String getGeneration() { return generation; }
    public String getImageUrl() { return imageUrl; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setGeneration(String generation) { this.generation = generation; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
