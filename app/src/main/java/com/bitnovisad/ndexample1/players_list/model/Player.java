package com.bitnovisad.ndexample1.players_list.model;

public class Player implements InterfacePlayer{

    private String playerName;
    private String playerPosition;
    private String playerImageUrl;

    public Player(String playerName, String playerPosition, String playerImageUrl) {
        this.playerName = playerName;
        this.playerPosition = playerPosition;
        this.playerImageUrl = playerImageUrl;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public String getPlayerImageUrl() {
        return playerImageUrl;
    }
}
