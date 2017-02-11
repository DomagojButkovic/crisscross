package app;

import java.util.HashMap;

/**
 * Created by dm on 11.02.17..
 */
public class PlayerList {
    private HashMap<String,Player> list;

    public PlayerList() {
        this.list = new HashMap<String,Player>();
    }
    private Player findPlayer(String name){

        return this.list.get(name);
    }

    public Player putPlayerInList(String name){
        Player p = findPlayer(name);
        if(p==null){
           p=new Player(name);
           this.list.put(name, p);
        }
        return p;
    }

    public PlayerStatsResp getPlayerStats() throws GameErrorException {
        if(list.isEmpty()){
            throw  new GameErrorException();
        }

        Player[] stats;
        stats =this.list.values().toArray(new Player[this.list.size()]);
        return  new PlayerStatsResp(stats);
    }




}
