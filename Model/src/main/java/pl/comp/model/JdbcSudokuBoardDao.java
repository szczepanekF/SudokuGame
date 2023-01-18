package pl.comp.model;

import java.sql.*;

import pl.comp.model.exceptions.FileException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {



    private static final  String db_Name = "sudokuName";
    private static final  String db_Fields = "sudokuFields";

    private final String sudokuName;

    public JdbcSudokuBoardDao(String sudokuName) {
        this.sudokuName = sudokuName;
    }

    private String url = "jdbc:sqlite:db/test.db";

    public static Connection connect(String jdbcURrl) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(jdbcURrl);
        } catch (SQLException e) {

        }
        return conn;
    }

    public static void main(String[] args) {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        board.print();

        String select = " select * from " + db_Name + " where name = ?";
        ResultSet resultSet;
        String insertData = "insert into " + db_Fields + "(sudoku_id,sudoku_X,sudoku_Y,sudoku_value) values (?,?,?,?);";
        Connection conn = connect("jdbc:sqlite:db/test.db");
        try (conn;
            PreparedStatement ask =  conn.prepareStatement(select)) {
//            conn.setAutoCommit(false);
//            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            ask.setString(1, "sudoku2");
            resultSet = ask.executeQuery();

            if (resultSet.getString(1) != null) {
                System.out.println("siusiak");
                try (PreparedStatement delete = conn.prepareStatement("DELETE FROM " +db_Fields + " " +
                        "WHERE sudoku_id=(SELECT sudoku_id FROM " + db_Name+ " WHERE name=" + "'sudoku2'" + ");")){
                    delete.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            int sudoku_id;

            try (PreparedStatement id = conn.prepareStatement("SELECT sudoku_id FROM " + db_Name+ " " +
                    "WHERE name=" + "'sudoku2'" + ";");
                 PreparedStatement preparedStatement = conn.prepareStatement(insertData)) {
                resultSet = id.executeQuery();
                sudoku_id = resultSet.getInt(1);

                preparedStatement.setString(1, Integer.toString(sudoku_id));
                for (int i = 0; i < 9; i++ ) {
                    for(int j = 0; j < 9; j++) {
                        System.out.println(Integer.toString(board.get(i,j)));
                        preparedStatement.setString(2, Integer.toString(i));
                        preparedStatement.setString(3, Integer.toString(j));
                        preparedStatement.setString(4, Integer.toString(board.get(i,j)));
                        preparedStatement.executeUpdate();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }





            resultSet.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(SudokuBoard obj) throws FileException {

        String select = " select * from " + db_Name + " where name = ?";
        ResultSet resultSet;
        String insertData = "insert into " + db_Name + "(Field1) values (?,?,?);";
        Connection conn = connect(url);
        try (conn;
             PreparedStatement ask =  conn.prepareStatement(select);
             PreparedStatement preparedStatement = conn.prepareStatement(insertData)) {
//            conn.setAutoCommit(false);
//            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            resultSet = ask.executeQuery();
            ask.setString(1, "2");
            if (resultSet.getString(1) == "null") {
                System.out.println("siusiak");
            }
            preparedStatement.setString(1, "55");
            preparedStatement.setString(2, "b");
            preparedStatement.setString(3, "c");
            preparedStatement.executeUpdate();
            }
        catch(Exception e) {

        }

    }

    @Override
    public SudokuBoard read() throws FileException {


        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        String receivedData;

        String selectData = "select * from " + db_Name + " where Field1=?;";
        Connection conn = connect(url);
        try (conn;PreparedStatement preparedStatement = conn.prepareStatement(selectData)) {
//            conn.setAutoCommit(false);
//            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement.setString(1, "2");


            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                receivedData = resultSet.getString(1);
                System.out.println(receivedData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return board;
    }

    @Override
    public void close() throws Exception {

    }
}
