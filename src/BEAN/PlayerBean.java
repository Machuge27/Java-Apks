/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

/**
 *
 * @author Mutai Hillary
 */
public class PlayerBean {
    private String name;
    private String position;
    private int goals;
    private int assists;

    // Constructor, getters and setters
    public PlayerBean(String name, String position) {
        this.name = name;
        this.position = position;
        this.goals = 0;
        this.assists = 0;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public void addGoal() {
        this.goals++;
    }

    public void addAssist() {
        this.assists++;
    }
}

