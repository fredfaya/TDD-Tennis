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

}
