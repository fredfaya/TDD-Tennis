package com.tdd.tennis.classes;

import com.tdd.tennis.exceptions.NullPartieException;

public class Arbitre {
    public Arbitre(Partie partie) throws NullPartieException {
        if(partie==null) throw new NullPartieException();
    }
}
