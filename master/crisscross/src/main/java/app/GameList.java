package app;

import java.util.ArrayList;

/**
 * Created by dm on 11.02.17..
 */
public class GameList {
    private ArrayList<Game>  games;

    public GameList() {
        games = new ArrayList<Game>();
    }

    public int addGame(Game g){
        games.add(g);
        return games.size()-1;
    }

    public Game getGame(int i){
        return games.get(i) ;
    }

}
