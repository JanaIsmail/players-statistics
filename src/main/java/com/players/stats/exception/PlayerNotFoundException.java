package com.players.stats.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(long id) {
        super("Could not find player with id " + id);
    }

    public String getMessage() {
        return super.getMessage();
    }
}
