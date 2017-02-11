package app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    public static final String COMPUTER = "computer";
    private final PlayerList plist = new PlayerList();
    private final GameList glist = new GameList();


    @RequestMapping(method= RequestMethod.GET, path="/game/new")
    public GameNewResp gameNew(@RequestParam(value = "player1", defaultValue = "computer") String player1
            , @RequestParam(value = "player2", defaultValue = "computer") String player2 ) throws GameErrorException {
        if(player1.equals(COMPUTER)  &&  player2.equals(COMPUTER) ){
            throw new GameErrorException();
        }
        Game g = new Game(plist.putPlayerInList(player1),plist.putPlayerInList(player2));


        return new GameNewResp(glist.addGame(g),g.getMode());
    }

    @RequestMapping(method= RequestMethod.GET, path="/game/play")
    public GameNewResp gamePlay(@RequestParam(value = "gameId") Integer gameId
            , @RequestParam(value = "row") Byte row
            , @RequestParam(value = "column") Byte column  ) throws GameErrorException {

        Game g = glist.getGame(gameId);
        g.playerPlays((byte)(row-1),(byte) (column-1));
        return new GameNewResp(gameId,g.getMode());


    }

    @RequestMapping(method= RequestMethod.GET, path="/game/status")
    public GameStatusResp gameStatus(@RequestParam(value = "gameId") Integer gameId){
        Game g = glist.getGame(gameId);
        return new GameStatusResp(gameId,g.getStatus(),g.getBoard(),g.getMode());


    }


    @RequestMapping(method= RequestMethod.GET, path="/game/stats")
    public PlayerStatsResp gameStats() throws GameErrorException {

        return  plist.getPlayerStats();
    }
}
