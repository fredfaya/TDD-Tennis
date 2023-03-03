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
        player1.setNbGames(nbGames);
    }

    public void setScoreGamesPlayer2(int nbGames) {
        player2.setNbGames(nbGames);
    }

    public void setDecisiveScorePlayer1(int nbPoints) {
        player1.setDecisivePoints(nbPoints);
    }

    public void setDecisiveScorePlayer2(int nbPoints) {
        player2.setDecisivePoints(nbPoints);
    }

    public void setAdvantagePlayerOne(boolean ad){
        player1.setAdvantaged(ad);
    }

    public void setAdvantagePlayerTwo(boolean ad){
        player2.setAdvantaged(ad);
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

    public boolean isDecisive() {
        if (player1.getNbGames() == 6 && player2.getNbGames() == 6) return true;
        return false;
    }

    public void setScoreSetOfPlayer1(int i) {
        player1.setNbSets(i);
    }

    public void setScoreSetOfPlayer2(int i) {
        player2.setNbSets(i);
    }
}