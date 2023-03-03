package com.tdd.tennis.classes;

import com.tdd.tennis.exceptions.NullPartieException;

public class Arbitre {
    private Partie partie;
    public Arbitre(Partie partie) throws NullPartieException {
        if(partie==null) throw new NullPartieException();
        this.partie = partie;
    }

    public Player updateScore(Player winner, Player looser) {

        if (!partie.isDecisive()){
            boolean skipIncrementWinnerNbGames = incrementWinnerScore(winner, looser);

            incrementWinnerNbGamesAndSets(winner, looser , skipIncrementWinnerNbGames);
        }else if ((winner.getDecisivePoints() - looser.getDecisivePoints() < 2) && winner.getDecisivePoints() != 6 && looser.getDecisivePoints() != 6){
            if (winner.equals(partie.player1)) {
                partie.setDecisiveScorePlayer1(1);
            } else {
                partie.setDecisiveScorePlayer2(1);
            }
        }else {
            winner.setNbSets(1);
            partie.resetScores();
        }

        return winner;
    }

    boolean incrementWinnerScore(Player winner, Player looser) {
        if (winner.getScore() == 40 && looser.getScore() == 40 && !looser.isAdvantaged()) {
            if (winner.equals(partie.player1)) {
                partie.setAdvantagePlayerOne(true);
            } else {
                partie.setAdvantagePlayerTwo(true);
            }
        } else {
            if (winner.equals(partie.player1)) {
                partie.setAdvantagePlayerOne(false);
            } else {
                partie.setAdvantagePlayerTwo(false);
            }
        }

        if (winner.getScore() == 0) {
            if (winner.equals(partie.player1)) {
                partie.setScorePlayer1(15);
            } else {
                partie.setScorePlayer2(15);
            }
        }else if (winner.getScore() == 15) {
            if (winner.equals(partie.player1)) {
                partie.setScorePlayer1(15);
            } else {
                partie.setScorePlayer2(15);
            }
        }else if (winner.getScore() == 30) {
            if (winner.equals(partie.player1)) {
                partie.setScorePlayer1(10);
            } else {
                partie.setScorePlayer2(10);
            }
            return true;
        }
        return false;
    }

    void incrementWinnerNbGamesAndSets(Player winner, Player looser, boolean skip){
        if ((winner.getScore() == 40 || winner.isAdvantaged()) && !skip){
            // This line is just to reset the scores from 40 to 0
            if (winner.equals(partie.player1)){
                partie.setScorePlayer1(15);
            }else{
                partie.setScorePlayer2(15);
            }
            winner.setNbGames(1);
            partie.resetScores();
        }

        if ((winner.getNbGames() == 5 && looser.getNbGames() < 5) || (winner.getNbGames() == 6 && looser.getNbGames() == 5)){
            winner.setNbSets(1);
            partie.resetScores();
        }

        if (winner.getNbGames() == 5 && looser.getNbGames() == 6){
            winner.setNbGames(1); // now both are 6 so decisive game is started
            partie.resetScores();
        }

    }

}
