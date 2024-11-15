package it.unibo.deathnote.impl;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{

    List<DeathNoteImplementation.Death> aa = new ArrayList<DeathNoteImplementation.Death>();

    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber > 0 && ruleNumber <= RULES.size()){
            return RULES.get(ruleNumber - 1);
        }
        throw new IllegalArgumentException("The rule number" + ruleNumber + "dosen't exist");
    }

    @Override
    public void writeName(String name) {
        if(!name.isBlank() && !name.isEmpty() && name!=null){
            aa.add(new Death(name));
        }else{
            throw new NullPointerException("Isn't a person or the field is empty");
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(cause!=null && !aa.isEmpty()){
            return aa.getLast().writeDeathCause(cause);
        }else{
            throw new IllegalArgumentException("The cause is empty or the Death Note is empty");
        }
    }

    @Override
    public boolean writeDetails(String details) {
        if(!aa.isEmpty() && details!=null){
            return aa.getLast().writeDetails(details);
        }else{
            throw new IllegalArgumentException("The details are empty or the Death Note is empty");
        }
    }

    @Override
    public String getDeathCause(String name) {
        for (Death death : aa) {
            if(death.getName().equals(name)){
                return death.getCause();
            }
        }
        throw new IllegalArgumentException("The name isn't written in the Death Note");
    }

    @Override
    public String getDeathDetails(String name) {
        for (Death death : aa) {
            if(death.getName().equals(name)){
                return death.getDetails();
            }
        }
        throw new IllegalArgumentException("The name isn't written in the Death Note");
    }

    @Override
    public boolean isNameWritten(String name) {
        for (Death death : aa) {
            if(death.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private  class Death {
        private final String name;
        private String cause;
        private String details;
        private long time;

        private static final String DEFOULT_CAUSE = "heart attack";
        private static final String DEFOULT_DETAILS = "";
        private static final long TIME_TO_CAUSE = 40;
        private static final long TIME_TO_DETAILS = TIME_TO_CAUSE + 1000;

        public Death(String name) {
            this.name = name;
            this.cause = DEFOULT_CAUSE;
            this.details = DEFOULT_DETAILS;
            this.time = System.currentTimeMillis();
        }
        
        public String getName() {
            return name;
        }

        public String getCause() {
            return cause;
        }

        public String getDetails() {
            return details;
        }

        public boolean writeDeathCause (String cause){
            if(System.currentTimeMillis() < this.time + TIME_TO_CAUSE){
                this.cause = cause;
                this.time = System.currentTimeMillis();
                return true;
            }else{
                return false;
            }
        }

        public boolean writeDetails (String details){
            if(System.currentTimeMillis() < this.time + TIME_TO_DETAILS){
                this.details = details;
                this.time = System.currentTimeMillis();
                return true;
            }else{
                return false;
            }
        }
    }

}