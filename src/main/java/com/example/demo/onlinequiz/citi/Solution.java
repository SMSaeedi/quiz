package com.example.demo.onlinequiz.citi;/*
We are building the back-end for an online gaming platform. The system tracks
players and their match history. Each match result records the outcome from
that player's perspective.

Definitions:
* A "player" has: playerId, username.
* A "match result" has: playerId, opponentId, outcome, score, timestamp.
* Outcome is one of: WIN, LOSS, DRAW.
* "GameManager" manages players and match results and provides player statistics.

To begin with, we present you with two tasks:
1-1) Read through and understand the code below. Feel free to run it.
1-2) The test for GameManager is not passing due to a bug in the code.
     Make the necessary changes to GameManager to fix the bug.
*/
/*
We are extending the platform to support recording match results
and computing per-player score statistics.

Each MatchResult represents one player's experience of a single match:
- playerId      : the player this record belongs to
- opponentId    : the opponent in that match
- outcome        : one of WIN, LOSS, DRAW
- score          : the player's score in that match
- timestamp      : when the match was played

To implement these changes, we need to add two functions to the GameManager class:

2.1) The addMatchResult function should be used to store a match result.
     Only the playerId is validated: if it does not refer to a known
     player in GameManager, the match result should be ignored. A match
     result with a known playerId must still be stored even when the
     opponentId does not refer to a known player.

2.2) The getAverageScoreByOutcome function should return a dictionary
     mapping each outcome (WIN, LOSS, DRAW) to the player's average score
     for that outcome. Only outcomes the player has at least one result
     for should appear in the dictionary. If the player has no match
     results at all, return an empty dictionary.

To assist you in testing these new functions, we have provided the
testAddMatchResult and testGetAverageScoreByOutcome tests.
*/
import java.util.*;

enum Outcome {
    WIN, LOSS, DRAW
}

class Player {
    public int playerId;
    public String username;

    public Player(int playerId, String username) {
        this.playerId = playerId;
        this.username = username;
    }
}

class MatchResult {
    public int playerId;
    public int opponentId;
    public Outcome outcome;
    public int score;
    public int timestamp;

    public MatchResult(int playerId, int opponentId, Outcome outcome, int score, int timestamp) {
        this.playerId = playerId;
        this.opponentId = opponentId;
        this.outcome = outcome;
        this.score = score;
        this.timestamp = timestamp;
    }
}

class PlayerStats {
    public int totalMatches;
    public int wins;
    public double winRate;

    public PlayerStats(int totalMatches, int wins, double winRate) {
        this.totalMatches = totalMatches;
        this.wins = wins;
        this.winRate = winRate;
    }
}

class GameManager {
    public Map<Integer, Player> players;
    public List<MatchResult> matchResults;

    public GameManager() {
        players = new HashMap<>();
        matchResults = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.put(player.playerId, player);
    }

    public PlayerStats getPlayerStatistics(int playerId) {
        List<MatchResult> playerMatches = new ArrayList<>();
        for (MatchResult m : matchResults) {
            if (m.playerId == playerId) {
                playerMatches.add(m);
            }
        }

        int totalMatches = 0;
        for (MatchResult m : playerMatches) {
            if (m.outcome == Outcome.WIN || m.outcome == Outcome.LOSS || m.outcome ==Outcome.DRAW) {
                totalMatches++;
            }
        }

        int wins = 0;
        for (MatchResult m : playerMatches) {
            if (m.outcome == Outcome.WIN) {
                wins++;
            }
        }

        double winRate=0.0;
        if (totalMatches > 0) {
            winRate = (double) wins / totalMatches;
        }

        return new PlayerStats(totalMatches, wins, winRate);
    }
    
    public void addMatchResult(MatchResult matchResult){
      if(players.containsKey(matchResult.playerId)) matchResults.add(matchResult);
    }
    
    public Map<Outcome, Double>  getAverageScoreByOutcome(int playerId){
      Map<Outcome, Integer> scoreSum=new HashMap<>();
      Map<Outcome, Integer> outcomeCounts=new HashMap<>();
      
      for(MatchResult m : matchResults){
        if(m.playerId==playerId){
          Outcome outcome=m.outcome;
          scoreSum.put(outcome, scoreSum.getOrDefault(outcome, 0)+m.score);
          outcomeCounts.put(outcome, outcomeCounts.getOrDefault(outcome, 0)+1);
        }
      }
        Map<Outcome, Double> averages=new HashMap<>();
        for(Outcome outcome : outcomeCounts.keySet()){
          int totalscore=scoreSum.get(outcome);
          int count=outcomeCounts.get(outcome);
          
          averages.put(outcome,(double) totalscore/count);
        }
        return averages;

    } 
}

public class Solution {
    public static void main(String[] args) {
        testGetPlayerStatistics();
        testAddMatchResult();
        testGetAverageScoreByOutcome();
        System.out.println("All Tests Passed!");
    }

    public static void testGetPlayerStatistics() {
        System.out.println("Running testGetPlayerStatistics");
        GameManager gm = new GameManager();
        gm.addPlayer(new Player(1, "player1"));
        gm.addPlayer(new Player(2, "player2"));

        gm.matchResults.add(new MatchResult(1, 2, Outcome.WIN,  80, 1000));
        gm.matchResults.add(new MatchResult(1, 2, Outcome.LOSS, 50, 2000));
        gm.matchResults.add(new MatchResult(1, 2, Outcome.DRAW, 60, 3000));
        gm.matchResults.add(new MatchResult(1, 2, Outcome.WIN,  90, 4000));

        PlayerStats stats = gm.getPlayerStatistics(1);
        assert stats.totalMatches == 4 :
            "totalMatches should be 4, was " + stats.totalMatches;
        assert stats.wins == 2 :
            "wins should be 2, was " + stats.wins;
        assert Math.abs(stats.winRate - 0.5) < 1e-4 :
            "winRate should be 0.5, was " + stats.winRate;

        gm.matchResults.add(new MatchResult(2, 1, Outcome.DRAW, 60, 1000));
        gm.matchResults.add(new MatchResult(2, 1, Outcome.DRAW, 60, 2000));

        PlayerStats stats2 = gm.getPlayerStatistics(2);
        assert stats2.totalMatches == 2 :
            "totalMatches should be 2, was " + stats2.totalMatches;
        assert stats2.wins == 0 :
            "wins should be 0, was " + stats2.wins;
        assert Math.abs(stats2.winRate - 0.0) < 1e-4 :
            "winRate should be 0.0, was " + stats2.winRate;
    }
    static void testAddMatchResult() {
        System.out.println("Running testAddMatchResult");
        GameManager gm = new GameManager();
        gm.addPlayer(new Player(1, "player1"));
        gm.addPlayer(new Player(2, "player2"));

        gm.addMatchResult(new MatchResult(1, 2, Outcome.WIN, 80, 1000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.LOSS, 50, 1000));

        // unknown player ignored
        gm.addMatchResult(new MatchResult(99, 1, Outcome.WIN, 100, 2000));

        // known player, unregistered opponent -> still stored
        gm.addMatchResult(new MatchResult(1, 99, Outcome.WIN, 70, 3000));

        assert gm.matchResults.size() == 3 : "Expected 3 match results";
    }

    static void testGetAverageScoreByOutcome() {
        System.out.println("Running testGetAverageScoreByOutcome");
        GameManager gm = new GameManager();
        gm.addPlayer(new Player(1, "player1"));
        gm.addPlayer(new Player(2, "player2"));

        // match 1 - player1 wins
        gm.addMatchResult(new MatchResult(1, 2, Outcome.WIN,  80, 1000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.LOSS, 50, 1000));

        // match 2 - player1 wins again
        gm.addMatchResult(new MatchResult(1, 2, Outcome.WIN,  91, 2000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.LOSS, 61, 2000));

        // match 3 - draw
        gm.addMatchResult(new MatchResult(1, 2, Outcome.DRAW, 70, 3000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.DRAW, 70, 3000));

        // match 4 - another draw
        gm.addMatchResult(new MatchResult(1, 2, Outcome.DRAW, 75, 4000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.DRAW, 75, 4000));

        Map<Outcome, Double> avg1 = gm.getAverageScoreByOutcome(1);
        assert Math.abs(85.5 - avg1.get(Outcome.WIN))  < 1e-4 : "Expected 85.5 for WIN";   // (80+91)/2
        assert Math.abs(72.5 - avg1.get(Outcome.DRAW)) < 1e-4 : "Expected 72.5 for DRAW";  // (70+75)/2
        assert !avg1.containsKey(Outcome.LOSS) : "player1 has no losses";                   // player1 has no losses

        Map<Outcome, Double> avg2 = gm.getAverageScoreByOutcome(2);
        assert Math.abs(55.5 - avg2.get(Outcome.LOSS)) < 1e-4 : "Expected 55.5 for LOSS";  // (50+61)/2
        assert Math.abs(72.5 - avg2.get(Outcome.DRAW)) < 1e-4 : "Expected 72.5 for DRAW";  // (70+75)/2
        assert !avg2.containsKey(Outcome.WIN) : "player2 has no wins";                      // player2 has no wins

        // player with no match results
        gm.addPlayer(new Player(3, "player3"));
        assert gm.getAverageScoreByOutcome(3).isEmpty() : "Expected empty map for player3";
    }
}