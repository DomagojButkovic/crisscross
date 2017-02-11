package app;

/**
 * Created by dm on 11.02.17..
 */
public class GameNewResp {
    private final int gameId;
    private final EGameMode gameMode;

    public GameNewResp(int gameId, EGameMode gameMode) {
        this.gameId = gameId;
        this.gameMode = gameMode;
    }

    public int getGameId() {
        return gameId;
    }

    public EGameMode getGameMode() {
        return gameMode;
    }
}
