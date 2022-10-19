package pl.first.firstjava;

/**
 * Klasa główna z metodą main.
 *
 * @author Marcin Kwapisz
 */
public class App {

    public static void main(final String[] args) {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();
        board.print();

        System.out.println("  ");
        board.print();

    }
}
