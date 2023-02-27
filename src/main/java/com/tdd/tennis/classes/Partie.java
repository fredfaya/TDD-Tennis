package com.tdd.tennis.classes;

import com.tdd.tennis.exceptions.NullPlayerException;

public class Partie {
    public  Partie(Player player1, Player player2) throws NullPlayerException {
        if(player1==null||player2==null)throw new NullPlayerException();
    }

    public int getScoreGameOfPlayer1() {
        return 0;
    }

    public int getScoreGameOfPlayer2() {
        return 0;
    }

    public int getScoreGamesOfPlayer1() {
        return 0;
    }

    public int getScoreGamesOfPlayer2() {
        return 0;
    }

    public int getScoreSetOfPlayer1() {
        return 0;
    }

    public int getScoreSetOfPlayer2() {
        return 0;
    }

    public void setScoreSetPlayer1(int i) {
    }
}