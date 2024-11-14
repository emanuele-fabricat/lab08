package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {
    private DeathNoteImplementation diary;
    private String name;
    private String anotherName;
    private static final String DEFOULT_CAUSE = "heart attack";
    private static final String CAUSE = "karting accident";
    private static final String DEFOULT_DETAILS = "";
    private static final String DETAILS = "ran for too long";
    private static final int TIME_DEFOULT = 100;
    private static final long LONG_TIME_DEFOULT = TIME_DEFOULT + 6000;

    @BeforeEach
    void setUp(){
        diary = new DeathNoteImplementation();
        name = "Giacomo";
        anotherName = "Carlo";
    }

    @Test
    void testNumberRule(){
        for(int i = 1; i > -4; i--){
            try {
                diary.getRule(i);
            } catch (IllegalArgumentException e) {
                assertNotNull(e.getMessage());
                assertFalse(e.getMessage().isBlank());
            }
        }
    }

    @Test
    void testEmptyRule(){
        for (String rule : diary.RULES) {
           assertNotNull(rule);
           assertFalse(rule.isBlank());
        }
    }

    @Test
    void testName(){
        assertFalse(diary.isNameWritten(name));
        diary.writeName(name);
        assertTrue(diary.isNameWritten(name));
        assertFalse(diary.isNameWritten(anotherName));
        assertFalse(diary.isNameWritten(""));
    }

    @Test
    void testCause(){
        try {
            diary.writeDeathCause("");
            Assertions.fail("Wrote the cause before write the name");
        } catch (IllegalArgumentException e) {
            assertFalse(e.getMessage().isBlank());
            assertNotNull(e.getMessage());
        }
        diary.writeName(anotherName);
        assertEquals(DEFOULT_CAUSE, diary.getDeathCause(anotherName));
        diary.writeName(name);
        assertTrue(diary.writeDeathCause(CAUSE));
        assertEquals(CAUSE, diary.getDeathCause(name));
        try {
            Thread.sleep(TIME_DEFOULT);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        diary.writeDeathCause(DEFOULT_CAUSE);
        assertEquals(CAUSE, diary.getDeathCause(name));
    }

    @Test
    void  testDetails(){
        try {
            diary.writeDetails("");
            Assertions.fail("Wrote the details before write the name");
        } catch (IllegalStateException e) {
            assertFalse(e.getMessage().isBlank());
            assertNotNull(e.getMessage());
        }
        diary.writeName(anotherName);
        assert(diary.getDeathDetails(anotherName).isEmpty());
        assert(diary.writeDetails(DETAILS));
        assertEquals(DETAILS, diary.getDeathDetails(anotherName));
        diary.writeName(name);
        try {
            Thread.sleep(LONG_TIME_DEFOULT);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        diary.writeDetails(DETAILS);
        assertEquals(DEFOULT_DETAILS, diary.getDeathDetails(name));
    }
}