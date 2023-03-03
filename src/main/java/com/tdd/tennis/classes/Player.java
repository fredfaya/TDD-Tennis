package com.tdd.tennis.classes;

import java.util.Objects;

public class Player {

    private int points;
    private int score;
    private int nbGames;
    private int nbSets;

    private boolean isAdvantaged;
    private int decisivePoints;

    public Player() {
        points = 0;
        score = 0;
        nbGames = 0;
        nbSets = 0;
        isAdvantaged = false;
        decisivePoints = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = (this.points + points) % 4;
    }

    public void resetScore(){
        if (!this.isAdvantaged){
            this.points = 0;
            this.score = 0;
            this.decisivePoints = 0;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (this.score < 40)
            this.score += score;
        else if (this.score == 40 && !this.isAdvantaged)
            this.score = 0;
    }

    public int getNbGames() {
        return nbGames;
    }

    public void setNbGames(int nbGames) {
        this.nbGames = (nbGames + this.getNbGames()) % 8;
    }

    public int getNbSets() {
        return nbSets;
    }

    public void setNbSets(int nbSets) {
        this.nbSets = (this.nbSets + nbSets) % 3;
    }

    public boolean isAdvantaged() {
        return isAdvantaged;
    }

    public void setAdvantaged(boolean advantaged) {
        isAdvantaged = advantaged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return points == player.points && score == player.score && nbGames == player.nbGames && nbSets == player.nbSets;
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, score, nbGames, nbSets);
    }

    public int getDecisivePoints() {
        return decisivePoints;
    }

    public void setDecisivePoints(int decisivePoints) {
        this.decisivePoints = (this.decisivePoints + decisivePoints) % 8;
    }
}
