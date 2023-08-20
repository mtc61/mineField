import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class minefield implements MouseListener {
    JFrame frame;
    button[][] board = new button[10][10];
    int openButton;

    public minefield() {
        openButton = 0;
        frame = new JFrame("Mine Field");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 10));

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                button b = new button(row, column);
                frame.add(b);
                b.addMouseListener(this);
                board[row][column] = b;
            }
        }

        generateMine();
        updateCount();

        printMine();

        frame.setVisible(true);
    }

    public void generateMine() {
        int i = 0;
        while (i < 10) {
            int randomRow = (int) (Math.random() * board.length);
            int randomColumn = (int) (Math.random() * board[0].length);
            while (board[randomRow][randomColumn].isMine()) {
                randomRow = (int) (Math.random() * board.length);
                randomColumn = (int) (Math.random() * board[0].length);
            }
            board[randomRow][randomColumn].setMine(true);
            i++;
        }
    }

    public void print() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column].isMine()) {
                    board[row][column].setIcon(new ImageIcon("mine.png"));
                } else {
                    board[row][column].setText(board[row][column].getCount() + ""); // +"" string'e çevirir.
                    board[row][column].setEnabled(false);
                }
            }
        }
    }

    public void printMine() {
        for (int row = 0; row < board[0].length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column].isMine()) {
                    board[row][column].setIcon(new ImageIcon("mine.png"));
                }
            }
        }

    }

    public void updateCount() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column].isMine()) {
                    counting(row, column);
                }
            }
        }
    }

    public void counting(int row, int column) {
        for (int i = row - 1; i <= +1; i++) {
            for (int k = column - 1; k <= column + 1; k++) {
                try {
                    int value = board[i][k].getCount();
                    board[i][k].setCount(++value);
                } catch (Exception e) {

                }
            }

        }

    }

    // recursive function yazıldı.
    public void open(int row, int column) { //alan kontrolü kutunun sağında solunda bi kutu daha yoksa (olası yanlış case'ler)
        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length ||
                board[row][column].getText().length() > 0 || board[row][column].isEnabled() == false) {
            return;
        } else if (board[row][column].getCount() != 0) {
            board[row][column].setText(board[row][column].getCount() + "");
            board[row][column].setEnabled(false);
            openButton++;
        } else {
            openButton++;
            board[row][column].setEnabled(false);
            open(row - 1, column);
            open(row + 1, column);
            open(row, column - 1);
            open(row, column + 1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        button b = (button) e.getComponent();
        if (e.getButton() == 1) {
            System.out.println("left click");
            if (b.isMine()) {
                JOptionPane.showMessageDialog(frame, "You stepped on a mine, Game Over!!!");
                print();
            } else {
                open(b.getRow(), b.getColumn());
                if (openButton == (board.length * board[0].length) - 10) {
                    JOptionPane.showMessageDialog(frame, "Congratulations, You Won!!!");
                    print();
                }
            }
        } else if (e.getButton() == 3) {
            System.out.println("right click");
            if (!b.isFlag()) {
                b.setIcon(new ImageIcon("flag.png"));
                b.setFlag(true);
            } else {
                b.setIcon(null);
                b.setFlag(false);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

