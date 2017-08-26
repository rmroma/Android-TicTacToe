package com.example.asi.tictactoe;

/**
 * <h1>Player</h1><p>
 * This class represent a player  </br>
 *
 * @author Asi Belachow
 * @version 1.0
 * @since 04-09-2017
 */
public class Player {

    /**The player name**/
    String name;
    /**Hold the number of winnings**/
    int score;
    /**Define the player turn**/
    boolean turn;
    /**The value of the player X or O**/
    String ch;


    /**
     *<h1>Player</h1><p>
     * <i><ul>Player()<i><p>
     * Initialize a new player - default CTOR
     */
    public Player() {
        this.name = null;
        this.score = 0;
        this.turn = false;
        this.ch = null;


    }

    /**
     * <h1>getCh</h1><p>
     * <i><ul>String getCh()<i><p>
     * Get the player value.
     * @return String ch - the player value X or O
     */
    public String getCh() {
        return ch;
    }


    /**
     * <h1>setCh</h1><p>
     * <i><ul>void setCh(String ch)<i><p>
     * Set the player value.
     * @param ch - the player value X or O
     */
    public void setCh(String ch) {
        this.ch = ch;
    }

    /**
     * <h1>getName</h1><p>
     * <i><ul>String getName()<i><p>
     * Get the player name.
     * @return String name - the player name
     */
    public String getName() {
        return name;
    }

    /**
     * <h1>setName</h1><p>
     * <i><ul>setName(String name)<i><p>
     * Set the player name.
     * @param name  - the player name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * <h1>getScore</h1><p>
     * <i><ul>int getScore()<i><p>
     * Get the player score.
     * @return int score - the player score
     */
    public int getScore() {
        return score;
    }

    /**
     * <h1>setScore</h1><p>
     * <i><ul>void setScore(int score)<i><p>
     * Set the player score.
     * @param score - the player score
     */
    public void setScore(int score) {
        this.score = score;
    }


    /**
     * <h1>isTurn(</h1><p>
     * <i><ul>booean isTurn()<i><p>
     * Check if the player turn to play.
     * @return boolean turn - true- if yes, else false
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * <h1>setTurn</h1><p>
     * <i><ul>void setTurn(boolean turn)<i><p>
     * Set the turn of the player.
     * @param  turn - true- if yes, else false
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }


}
