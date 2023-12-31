import javax.swing.*;

public class button extends JButton {
    private int row, column, count;
    private boolean mine, flag;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public button(int row, int column) {
        super();
        this.row = row;
        this.column = column;
        this.count = 0;
        this.flag = false;
        this.mine = false;
    }
}
