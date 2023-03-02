package com.tdd.tennis;
import com.tdd.tennis.exceptions.*;
import com.tdd.tennis.classes.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.swing.*;

public class ArbitreTest {
    @Test
    public void givenNullPartieShouldThrowException(){
        //given

        //when
        Executable action =()->{
            Arbitre arbitre=new Arbitre(null);
        };
        //then
        Assertions.assertThrows(NullPartieException.class,action);

    }
    @Test
    public void givenNoNullPartieShouldNotThrowException(){
        //given

        //when
        Executable action =()->{
            Arbitre arbitre=new Arbitre(new Partie(new Player(),new Player()));
        };
        //then
        Assertions.assertDoesNotThrow(action);

    }
    @Test
    public void givenNoNullplayersShouldNotThrowException(){
        //given

        //when
        Executable action =()->{
         Partie partie=new Partie(new Player(),new Player());
        };
        //then
        Assertions.assertDoesNotThrow(action);

    }

    @Test
    public void updateScoreShouldAdd15ToPlayerWhenPlayerWinsFirstPoint() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        Arbitre arbitre = new Arbitre(partie);
        //when
        Player winner  = partie.getExchangeWinner();
        arbitre.updateScore(winner, player2);
        //then
        Assertions.assertEquals(15, winner.getScore());
    }

    @Test
    public void updateScoreShouldAdd15ToPlayerWhenPlayerWinsSecondPoint() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScorePlayer1(15);
        partie.setScorePlayer2(15);
        Arbitre arbitre = new Arbitre(partie);
        //when
        Player winner  = partie.getExchangeWinner();
        arbitre.updateScore(winner, player2);
        //then
        Assertions.assertEquals(30, winner.getScore());
    }

    @Test
    public void updateScoreShouldAdd10ToPlayerWhenPlayerWinsThirdPoint() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScorePlayer1(15);
        partie.setScorePlayer2(15);
        partie.setScorePlayer1(15);
        partie.setScorePlayer2(15);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(40, player1.getScore());
    }

    @Test
    public void updateScoreShouldMakePlayerWinAGameWhenPlayerWinsHaving40PointsAndOtherPlayerLessThan40() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        simulate2EqualExchange(partie);
        Arbitre arbitre = new Arbitre(partie);
        //when
        partie.setScorePlayer1(10);
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(1, player1.getNbGames());
    }

    @Test
    public void updateScoreShouldMakeBothPlayerScoresToZeroWhenPlayerWins() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        simulate2EqualExchange(partie);
        Arbitre arbitre = new Arbitre(partie);
        //when
        partie.setScorePlayer1(10);
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(0, partie.getScorePointsOfPlayer1());
        Assertions.assertEquals(0, partie.getScorePointsOfPlayer2());
        Assertions.assertEquals(0, partie.getScoreGameOfPlayer1());
        Assertions.assertEquals(0, partie.getScoreGameOfPlayer2());
    }

    @Test
    public void updateScoreShouldMakePlayerWinAdvantageWhenPlayerWinsHaving40PointsAndOtherPlayerAlso40() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        simulate3EqualExchange(partie);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertTrue(player1.isAdvantaged());
    }

    @Test
    public void updateScoreShouldNotMakePlayerWinAdvantageWhenBothPlayersHaveLessThan40Points() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScorePlayer1(15);
        partie.setScorePlayer2(15);
        Arbitre arbitre = new Arbitre(partie);
        //when
        partie.setScorePlayer1(15);
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertFalse(player1.isAdvantaged());
    }

    public void simulate2EqualExchange(Partie partie){
        partie.setScorePlayer1(15);
        partie.setScorePlayer2(15);
        partie.setScorePlayer1(15);
        partie.setScorePlayer2(15);
    }

    public void simulate3EqualExchange(Partie partie){
        partie.setScorePlayer1(15);
        partie.setScorePlayer2(15);
        partie.setScorePlayer1(15);
        partie.setScorePlayer2(15);
        partie.setScorePlayer1(10);
        partie.setScorePlayer2(10);
    }
}
