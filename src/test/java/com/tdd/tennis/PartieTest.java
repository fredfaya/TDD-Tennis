package com.tdd.tennis;

import com.tdd.tennis.Enumerations.GameStatus;
import com.tdd.tennis.classes.Partie;
import com.tdd.tennis.classes.Player;
import com.tdd.tennis.exceptions.NullPlayerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.assertj.core.api.Assertions.assertThat;
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

}
