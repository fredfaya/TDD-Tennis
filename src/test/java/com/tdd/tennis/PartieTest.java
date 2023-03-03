package com.tdd.tennis;

import com.tdd.tennis.Enumerations.GameStatus;
import com.tdd.tennis.classes.Partie;
import com.tdd.tennis.classes.Player;
import com.tdd.tennis.exceptions.NullPlayerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PartieTest {
    @Test
    public void givenNullPlayersShouldThrowException(){
        //given

        //when
        Executable action =()->{
            Partie partie=new Partie(null,null);
        };
        //then
        Assertions.assertThrows(NullPlayerException.class,action);

    }
    @Test
    public void givenNullPlayer1ShouldThrowException(){
        //given

        //when
        Executable action =()->{
            Partie partie=new Partie(null,new Player());

        };
        //then
        Assertions.assertThrows(NullPlayerException.class,action);

    }
    @Test
    public void givenNullPlayer2ShouldThrowException(){
        //given

        //when
        Executable action =()->{
            Partie partie=new Partie(new Player(),null);

        };
        //then
        Assertions.assertThrows(NullPlayerException.class,action);

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
    public void givenShouldScoreBeZeroWhenPartieInitialised() throws NullPlayerException {
        //given
        Partie partie = new Partie(new Player(), new Player());
        //when
        //then
       assertThat(partie.getScoreGameOfPlayer1()).isEqualTo(0);
       assertThat(partie.getScoreGameOfPlayer2()).isEqualTo(0);
    }
    @Test
    public void givenShouldNumberOfGamesBeZeroWhenPartieInitialised() throws NullPlayerException {
        //given
        Partie partie = new Partie(new Player(), new Player());
        //when
        //then
        assertThat(partie.getScoreGamesOfPlayer1()).isEqualTo(0);
        assertThat(partie.getScoreGamesOfPlayer2()).isEqualTo(0);
    }
    @Test
    public void givenShouldNumberOfSetBeZeroWhenPartieInitialised() throws NullPlayerException {
        //given
        Partie partie = new Partie(new Player(), new Player());
        //when
        //then
        assertThat(partie.getScoreSetOfPlayer1()).isEqualTo(0);
        assertThat(partie.getScoreSetOfPlayer2()).isEqualTo(0);
    }

    @Test
    public void checkGameStateShouldReturnPlayer1WinsWhenOnePlayer1Has2Sets() throws NullPlayerException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        //when
        player1.setNbSets(2);
        GameStatus gameStatus = partie.checkGameStatus();
        //then
        Assertions.assertEquals(GameStatus.PLAYER1WIN, gameStatus);
    }

    @Test
    public void checkGameStateShouldReturnPlayer2WinsWhenOnePlayer2Has2Sets() throws NullPlayerException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        //when
        player2.setNbSets(2);
        GameStatus gameStatus = partie.checkGameStatus();
        //then
        Assertions.assertEquals(GameStatus.PLAYER2WIN, gameStatus);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void checkGameStateShouldReturnProgressWhenPlayersHasLessThan2Sets(int nbSet) throws NullPlayerException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        //when
        player1.setNbSets(nbSet);
        player2.setNbSets(nbSet);
        GameStatus gameStatus = partie.checkGameStatus();
        //then
        Assertions.assertEquals(GameStatus.PROGRESS, gameStatus);
    }

    @Test
    public void getExchangeWinnerShouldReturnPlayerWithScorePlus1WhenPlayerWins() throws NullPlayerException {
        //given
        Player player1 = new Player();
        Player player2 = new Player();
        Partie partie = new Partie(player1, player2);
        //when
        Object result = partie.getExchangeWinner();
        //then
        assertTrue(result instanceof Player);
        Assertions.assertEquals(1,((Player) result).getPoints());
    }

}
