package com.tdd.tennis;
import com.tdd.tennis.Enumerations.GameStatus;
import com.tdd.tennis.exceptions.*;
import com.tdd.tennis.classes.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        simulate2EqualExchange(partie);
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
    public void updateScoreShouldMakePlayerWinAdvantageWhenPlayerWinsHaving40PointsAndOtherPlayerAlso40WithoutAdvantage() throws NullPlayerException, NullPartieException {
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

    @Test
    public void updateScoreShouldNotMakePlayerWinAdvantageWhenTheLoserHasAdvantage() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        simulate3EqualExchange(partie);
        partie.setAdvantagePlayerTwo(true);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertFalse(player1.isAdvantaged());
    }

    @Test
    public void updateScoreShouldMakePlayerWinGameWhenPlayerHasAdvantage() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        simulate3EqualExchange(partie);
        partie.setAdvantagePlayerTwo(true);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertFalse(player1.isAdvantaged());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void updateScoreShouldMakePlayerWinSecondGameWhenPlayerWinsTheGameHavingOneGameWon(int nbGamesWon) throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScoreGamesPlayer1(nbGamesWon);
        partie.setScoreGamesPlayer2(nbGamesWon);
        simulate2EqualExchange(partie);
        Arbitre arbitre = new Arbitre(partie);
        //when
        partie.setScorePlayer1(10);
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(nbGamesWon + 1, player1.getNbGames());
    }

    @Test
    public void updateScoreShouldMakePlayerWinSetWhenPlayerWinsHaving6GamesAndOtherLessThan5() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScoreGamesPlayer1(5);
        partie.setScoreGamesPlayer2(4);
        simulate2EqualExchange(partie);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(1, player1.getNbSets());
    }

    @Test
    public void updateScoreShouldNotMakePlayerWinSetWhenPlayerWinsHaving6GamesAndOtherHave5() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScoreGamesPlayer1(5); // the updateScore will make it go from 5 to 6
        partie.setScoreGamesPlayer2(5);
        simulate2EqualExchange(partie);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(0, player1.getNbSets());
    }

    @Test
    public void updateScoreShouldMakePlayerWinSetWhenPlayerWinsHaving7GamesAndOtherHave5() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScoreGamesPlayer1(6);
        partie.setScoreGamesPlayer2(5);
        simulate2EqualExchange(partie);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(1, player1.getNbSets());
    }

    @Test
    public void updateScoreShouldEnableDecisiveGameWhenBothPlayersHave6GamesWon() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScoreGamesPlayer1(5);
        partie.setScoreGamesPlayer2(6);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertTrue(partie.isDecisive());
    }

    @Test
    public void updateScoreShouldNotEnableDecisiveGameWhenBothPlayersHaveNot6GamesWon() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScoreGamesPlayer1(3);
        partie.setScoreGamesPlayer2(6);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertFalse(partie.isDecisive());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    public void updateScoreShouldGivePlayer1PointWhenDecisiveGameEnabled(int decisivePoint) throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScoreGamesPlayer1(6);
        partie.setScoreGamesPlayer2(6); //make the game decisive
        partie.setDecisiveScorePlayer1(decisivePoint);
        partie.setDecisiveScorePlayer2(decisivePoint);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(decisivePoint + 1, player1.getDecisivePoints());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6})
    public void updateScoreShouldMakePlayerWinSetWhenDecisiveGameEnabledAndHavingDifferenceOf2BetweenPlayers(int decisivePoint) throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScoreGamesPlayer1(6);
        partie.setScoreGamesPlayer2(6); //make the game decisive
        partie.setDecisiveScorePlayer1(decisivePoint);
        partie.setDecisiveScorePlayer2(decisivePoint - 2);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(1, player1.getNbSets());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void updateScoreShouldNotMakePlayerWinSetWhenDecisiveGameEnabledAndHavingDifferenceOf1BetweenPlayers(int decisivePoint) throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScoreGamesPlayer1(6);
        partie.setScoreGamesPlayer2(6); //make the game decisive
        partie.setDecisiveScorePlayer1(decisivePoint);
        partie.setDecisiveScorePlayer2(decisivePoint - 1);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(0, player1.getNbSets());
    }

    @Test
    public void updateScoreShouldMakePlayerWinSetWhenDecisiveGameEnabledAndHaving7DecisivePointsAndOther6() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScoreGamesPlayer1(6);
        partie.setScoreGamesPlayer2(6); //make the game decisive
        partie.setDecisiveScorePlayer1(6);
        partie.setDecisiveScorePlayer2(6);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(1, player1.getNbSets());
    }

    @Test
    public void updateScoreShouldMakePlayerWinWholeGameWhenPlayerHave2SetsWon() throws NullPlayerException, NullPartieException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        partie.setScoreSetOfPlayer1(1);
        partie.setScoreSetOfPlayer2(1);
        partie.setScoreGamesPlayer1(6);
        partie.setScoreGamesPlayer2(6); //make the game decisive
        partie.setDecisiveScorePlayer1(6);
        partie.setDecisiveScorePlayer2(6);
        Arbitre arbitre = new Arbitre(partie);
        //when
        arbitre.updateScore(player1, player2);
        //then
        Assertions.assertEquals(GameStatus.PLAYER1WIN, partie.checkGameStatus());
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
