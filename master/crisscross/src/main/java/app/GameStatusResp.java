package app;

/**
 * Created by dm on 11.02.17..
 */
public class GameStatusResp {

    private Integer gameId;
    private EGameStatus status;
    private GameCell[][] game;
    private EGameMode gameMode;

    public GameStatusResp(Integer gameId, EGameStatus status, GameCell[][] game, EGameMode gameMode) {
        this.gameId = gameId;
        this.status = status;
        this.game   = game;
        this.gameMode = gameMode;
    }

    public Integer getGameId() {
        return gameId;
    }

    public EGameStatus getStatus() {
        return status;
    }

    public GameCell[] getGame() {
        GameCell[] oneDArray = new GameCell[game.length * game.length];
        for(int i = 0; i < game.length; i ++)
        {
            for(int s = 0; s < game.length; s ++)
            {
                oneDArray[(i * game.length) + s] = game[i][s];
            }
        }

        return oneDArray;
    }

    public EGameMode getGameMode() {
        return gameMode;
    }
}
