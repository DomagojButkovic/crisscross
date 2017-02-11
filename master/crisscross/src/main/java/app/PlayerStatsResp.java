package app;

/**
 * Created by dm on 11.02.17..
 */
public class PlayerStatsResp {

    private Player[] stats;

    public PlayerStatsResp(Player[] stats) {
        this.stats = stats;
    }

    public Player[] getStats() {
        return stats;
    }

    public void setStats(Player[] stats) {
        this.stats = stats;
    }
}
