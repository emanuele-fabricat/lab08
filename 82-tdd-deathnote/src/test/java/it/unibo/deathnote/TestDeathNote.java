package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {
    private DeathNoteImplementation diairy;

    @BeforeEach
    void setUp(){
        diairy = new DeathNoteImplementation();
    }

    @Test
    void testNumberRule(){
        for(int i = 1; i > -4; i--){
            try {
                diairy.getRule(i);
            } catch (IllegalArgumentException e) {
                assert(e.getMessage() != null);
                assert(e.getMessage() != "");
                assert(e.getMessage() != " ");
            }
        }
    }

    @Test
    void testEmptyRule(){
        for (String rule : RULES) {
            
        }
    }
}