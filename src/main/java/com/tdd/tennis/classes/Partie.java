package com.tdd.tennis.classes;

import com.tdd.tennis.Enumerations.GameStatus;
import com.tdd.tennis.exceptions.NullPlayerException;

import java.util.Random;

public class Partie {

    Player player1;
    Player player2;

    public  Partie(Player player1, Player player2) throws NullPlayerException {
        if(player1==null||player2==null)throw new NullPlayerException();
        this.player1 = player1;
        this.player2 = player2;
    }

    public int getScoreGameOfPlayer1() {
        return player1.getScore();
    }

    public int getScoreGameOfPlayer2() {
        return player2.getScore();
    }

    public int getScoreGamesOfPlayer1() {
        return player1.getNbGames();
    }

    public int getScoreGamesOfPlayer2() {
        return player2.getNbGames();
    }

    public int getScoreSetOfPlayer1() {
        return player1.getNbSets();
    }

    public int getScoreSetOfPlayer2() {
        return player2.getNbSets();
    }

    public int getScorePointsOfPlayer1() {
        return player1.getPoints();
    }

    public int getScorePointsOfPlayer2() {
        return player2.getPoints();
    }
    public void setScorePlayer1(int score) {
        player1.setPoints(1);
        player1.setScore(score);
    }

    public void setScorePlayer2(int score) {
        player2.setPoints(1);
        player2.setScore(score);
    }
    public void setScoreGamesPlayer1(int nbGames) {
        player1.setScore(nbGames);
    }

    public void setScoreGamesPlayer2(int nbGames) {
        player2.setScore(nbGames);
    }

    public void setScoreSetPlayer1(int nbSets) {
        player1.setScore(nbSets);
    }

    public void setScoreSetPlayer2(int nbSets) {
        player2.setScore(nbSets);
    }

    public void setScorePointsPlayer1(int points) {
        player1.setScore(points);
    }

    public void setScorePointsPlayer2(int points) {
        player2.setScore(points);
    }

    public GameStatus checkGameStatus() {
        if (player1.getNbSets() == 2)
            return GameStatus.PLAYER1WIN;
        else if (player2.getNbSets() == 2) {
            return GameStatus.PLAYER2WIN;
        }
        return GameStatus.PROGRESS;
    }

    public Player getExchangeWinner(){
        Random random = new Random();
        int playerNumber = random.nextInt(2) + 1;
        if (playerNumber == 1) {
            player1.setPoints(1);
            return player1;
        }else {
            player2.setPoints(1);
            return player2;
        }
    }

    public void resetScores(){
        player1.resetScore();
        player2.resetScore();
    }
}