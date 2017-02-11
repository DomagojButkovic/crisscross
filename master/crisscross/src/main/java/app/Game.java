package app;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dm on 11.02.17..
 */
public class Game {
    private Player player1;
    private Player player2;
    private GameCell[][] board;
    private EGameStatus status;
    private EGameMode mode;
    private ArrayList<GameCell> emptyCells;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        this.mode = player1.isComputer() ? player2.gameMode() : player1.gameMode();

        this.status = EGameStatus.inProgress;
        this.emptyCells = new ArrayList<GameCell>();
        this.board = new GameCell[3][3];
        for (byte i = 0; i < 3; i++) {
            for (byte j = 0; j < 3; j++) {
                this.board[i][j] = new GameCell((byte) (i + 1), (byte) (j + 1));
                this.emptyCells.add(this.board[i][j]);
            }
        }

    }

    public void playerPlays(byte i, byte j) throws GameErrorException {
        if (this.status == EGameStatus.inProgress) {
            if (!player1.isComputer()) {
                player1PutsX(i, j,true);
            } else {
                player2PutsO(i, j,true);
            }
        }

        checkForEndGame();

        if (this.status == EGameStatus.inProgress) {
            computerPlays();
        }

    }

    private void computerPlays() throws GameErrorException {
        if (this.mode == EGameMode.easy) {
            easyModeComputerPlay();
        } else {
            hardModeComputerPlay();
        }

        checkForEndGame();
    }

    private void hardModeComputerPlay() throws GameErrorException {
        //time to cheat
        GameCell cell = null;
        Player winner;

        //prevent loss
        if (emptyCells != null) {

            for (GameCell g : emptyCells) {

                if (!player1.isComputer()) {
                    player1PutsX(g,false);
                } else {
                    player2PutsO(g,false);
                }

                winner = checkForWinner();

                g.resetCell();

                if (winner != null) {
                    cell = g;
                }
            }

        }

        //check for victory
        if (cell == null  && emptyCells != null) {
            for (GameCell g : emptyCells) {

                if (player1.isComputer()) {
                    player1PutsX(g,false);
                } else {
                    player2PutsO(g,false);
                }

                winner = checkForWinner();

                g.resetCell();

                if (winner != null) {
                    cell = g;
                }
            }
        }

        //optimal generic strat
        if (cell == null) {
            if (this.board[1][1].cellEmpty()) {
                cell = this.board[1][1];
            } else if (this.board[0][0].cellEmpty()) {
                cell = this.board[0][0];

            } else if (this.board[0][2].cellEmpty()) {
                cell = this.board[0][2];
            } else if (this.board[2][2].cellEmpty()) {
                cell = this.board[2][0];
            } else if (this.board[2][0].cellEmpty()) {
                cell = this.board[1][1];
            }
        }


        if (player1.isComputer()) {
            player1PutsX(cell,true);
        } else {
            player2PutsO(cell,true);
        }


    }

    private void easyModeComputerPlay() throws GameErrorException {


        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(emptyCells.size());
        GameCell cell = emptyCells.get(index);

        if (player1.isComputer()) {
            player1PutsX(cell,true);
        } else {
            player2PutsO(cell,true);
        }

    }

    private void player1PutsX(byte i, byte j,boolean remove) throws GameErrorException {
        player1PutsX(this.board[i][j],remove);

    }

    private void player2PutsO(byte i, byte j, boolean remove) throws GameErrorException {
        player2PutsO(this.board[i][j],remove);

    }

    private void player1PutsX(GameCell c, boolean remove) throws GameErrorException {
        if (c.cellEmpty()) {
            c.setValue(EGameCell.X);
            if(remove) emptyCells.remove(c);
        } else {
            throw new GameErrorException();
        }

    }

    private void player2PutsO(GameCell c, boolean remove) throws GameErrorException {
        if (c.cellEmpty()) {
            c.setValue(EGameCell.O);
            if(remove) emptyCells.remove(c);
        } else {
            throw new GameErrorException();
        }

    }


    private void checkForEndGame() {
        Player winner = checkForWinner();
        if (winner != null) {
            Player loser = winner.equals(player1) ? player2 : player1;
            winner.victory();
            loser.loss();
            this.status = EGameStatus.finished;
            return;

        }

        if (checkForDraw()) {
            player1.draw();
            player2.draw();
            this.status = EGameStatus.finished;
        }

    }

    private boolean checkForDraw() {
        if (emptyCells.size() == 0) {
            return true;
        } else {
            return false;
        }
    }


    private Player checkForWinner() {
        //check rows
        for (byte i = 0; i < 3; i++) {
            if (this.board[i][0].getValue() == EGameCell.X && this.board[i][1].getValue() == EGameCell.X && this.board[i][2].getValue() == EGameCell.X) {
                return player1;
            }

            if (this.board[i][0].getValue() == EGameCell.O && this.board[i][1].getValue() == EGameCell.O && this.board[i][2].getValue() == EGameCell.O) {
                return player2;
            }

        }

        //check columns
        for (byte j = 0; j < 3; j++) {
            if (this.board[0][j].getValue() == EGameCell.X && this.board[1][j].getValue() == EGameCell.X && this.board[2][j].getValue() == EGameCell.X) {
                return player1;
            }

            if (this.board[0][j].getValue() == EGameCell.O && this.board[1][j].getValue() == EGameCell.O && this.board[2][j].getValue() == EGameCell.O) {
                return player2;
            }

        }

        // check diagonals

        if (this.board[0][0].getValue() == EGameCell.X && this.board[1][1].getValue() == EGameCell.X && this.board[2][2].getValue() == EGameCell.X) {
            return player1;
        }

        if (this.board[0][0].getValue() == EGameCell.O && this.board[1][1].getValue() == EGameCell.O && this.board[2][2].getValue() == EGameCell.O) {
            return player2;
        }


        if (this.board[0][2].getValue() == EGameCell.X && this.board[1][1].getValue() == EGameCell.X && this.board[2][0].getValue() == EGameCell.X) {
            return player1;
        }

        if (this.board[0][2].getValue() == EGameCell.O && this.board[1][1].getValue() == EGameCell.O && this.board[2][0].getValue() == EGameCell.O) {
            return player2;
        }


        return null;

    }

    public GameCell[][] getBoard() {
        return board;
    }

    public EGameStatus getStatus() {
        return status;
    }

    public EGameMode getMode() {
        return mode;
    }
}
