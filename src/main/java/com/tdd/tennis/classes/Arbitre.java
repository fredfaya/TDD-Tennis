package com.tdd.tennis.classes;

import com.tdd.tennis.exceptions.NullPartieException;

public class Arbitre {
    private Partie partie;
    public Arbitre(Partie partie) throws NullPartieException {
        if(partie==null) throw new NullPartieException();
        this.partie = partie;
    }

    public Player updateScore(Player winner, Player looser) {

        boolean skipIncrementWinnerNbGames = incrementWinnerScore(winner, looser);

        incrementWinnerNbGames(winner, looser , skipIncrementWinnerNbGames);

        return winner;
    }

    boolean incrementWinnerScore(Player winner, Player looser) {
        if (winner.getScore() == 40 && looser.getScore() == 40) {
            winner.setAdvantaged(true);
        } else {
            winner.setAdvantaged(false);
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

    void incrementWinnerNbGames(Player winner, Player looser, boolean skip){
        if (winner.getScore() == 40 && !skip){
            // This line is just to reset the scores from 40 to 0
            if (winner.equals(partie.player1)){
                partie.setScorePlayer1(15);
            }else{
                partie.setScorePlayer2(15);
            }
            winner.setNbGames(1);
            partie.resetScores();
        }
    }

}
