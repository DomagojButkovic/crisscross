package app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dm on 11.02.17..
 */
@JsonIgnoreProperties({"cellEmpty"})
public class GameCell {
    private byte row;
    private short column;
    private EGameCell value;

    public GameCell(byte row, byte column) {
        this.row = row;
        this.column = column;
        this.value = EGameCell.EMPTY;
    }

    public byte getRow() {
        return row;
    }

    public short getColumn() {
        return column;
    }

    public EGameCell getValue() {
        return value;
    }

    public void setValue(EGameCell value) {
        this.value = value;
    }

    public void resetCell() {
        this.value = EGameCell.EMPTY;
    }

    public boolean cellEmpty(){
        return this.value ==  EGameCell.EMPTY;
    }
}
