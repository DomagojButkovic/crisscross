package app;

import java.util.Random;

/**
 * Created by dm on 11.02.17..
 */
public class Player {
    private String name;
    private int wins;
    private int losses;
    private int draws;
    private int gamesPlayed;
    private boolean computer;


    public Player(String name) {
        this.name = name;
        if(this.name.equals(GameController.COMPUTER)){
            computer = true;
        }
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void victory() {

        wins++;
        gamesPlayed++;
    }

    public void loss() {

        losses++;
        gamesPlayed++;
    }

    public void draw() {
        draws++;
        gamesPlayed++;
    }

    public boolean isComputer() {
        return computer;
    }

    public EGameMode gameMode(){
        EGameMode mode;
        if(gamesPlayed ==0){
            mode = EGameMode.easy;
        }else if((double)((wins*1d)/ (gamesPlayed)) <= 0.3d){
            mode = EGameMode.easy;
        }else if((double)((wins*1d)/ gamesPlayed) >  0.9d){
            mode = EGameMode.hard;
        }else {
            Random random = new Random();
            if (random.nextBoolean()) {
                mode = EGameMode.easy;
            } else {
                mode = EGameMode.hard;
            }
        }
        return  mode;
    }
}
