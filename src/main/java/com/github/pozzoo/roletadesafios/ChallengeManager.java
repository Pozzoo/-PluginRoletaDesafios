package com.github.pozzoo.roletadesafios;

import java.util.List;
import java.util.Random;

public class ChallengeManager {
    private List<String> challengeList;
    private String currentChallenge = null;
    private int currentChallengeIndex = -1;
    private final Random random;

    public ChallengeManager() {
        random = new Random();
    }

    public void loadChallenges(List<String> strings) {
       this.challengeList = strings;
    }

    public void randomChallenge() {
        int index = random.nextInt(challengeList.size());
        currentChallenge = challengeList.get(index);
        currentChallengeIndex = index;
    }

    public String getCurrentChallenge() {
        return currentChallenge;
    }

    public int getCurrentChallengeIndex() {
        return currentChallengeIndex;
    }

    public List<String> getChallengeList() {
        return this.challengeList;
    }

    public void clearCurrentChallenge() {
        currentChallenge = null;
        currentChallengeIndex = -1;
    }
}
