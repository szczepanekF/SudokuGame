package pl.comp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import pl.comp.model.exceptions.JdbcException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {



    private static final  String db_Name = "sudokuName";
    private static final  String db_Fields = "sudokuFields";
    private final String sudokuName;
    private String url = "jdbc:sqlite:db/test.db";

    public JdbcSudokuBoardDao(String sudokuName) {
        this.sudokuName = sudokuName;
    }

    public Connection connect() throws JdbcException {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            this.url = "jdbc:sqlite:../db/test.db";
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e2) {
                throw new JdbcException(e2);
            }
        }
        return conn;
    }


    @Override
    public void write(SudokuBoard obj) throws JdbcException {

        String select = " select name from " + db_Name + " where name = ?";
        String insertData = "insert into " + db_Fields
                + " (sudoku_id,sudoku_X,sudoku_Y,sudoku_value) values (?,?,?,?)";
        String insertName = "insert into " + db_Name + " (name) values ('" + sudokuName + "')";
        String idQuerry = "SELECT sudoku_id FROM " + db_Name + " WHERE name='" + sudokuName + "'";
        String deleteRows = "DELETE FROM " + db_Fields + " WHERE sudoku_id=( " + idQuerry + ")";
        ResultSet rs;
        ResultSet rs2;
        int sudokuId;

        try (Connection conn = connect();
             PreparedStatement ask =  conn.prepareStatement(select)) {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            ask.setString(1, sudokuName);
            rs = ask.executeQuery();

            if (rs.getString(1) != null) {

                try (Statement delete = conn.createStatement()) {
                    delete.executeUpdate(deleteRows);
                } catch (SQLException e) {
                    throw new JdbcException(e);
                }
            } else {
                try (Statement addName = conn.createStatement()) {
                    addName.executeUpdate(insertName);
                } catch (SQLException e) {
                    throw new JdbcException(e);
                }
            }

            try (PreparedStatement id = conn.prepareStatement(idQuerry);
                 PreparedStatement preparedStatement = conn.prepareStatement(insertData)) {
                rs2 = id.executeQuery();
                sudokuId = rs2.getInt(1);

                preparedStatement.setInt(1, sudokuId);
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        preparedStatement.setInt(2, i);
                        preparedStatement.setInt(3, j);
                        preparedStatement.setInt(4, obj.get(i,j));
                        preparedStatement.executeUpdate();
                    }
                }

            } catch (SQLException e) {
                throw new JdbcException(e);
            }
            conn.commit();
        } catch (SQLException | JdbcException e) {
            throw new JdbcException(e);
        }
    }



    @Override
    public SudokuBoard read() throws JdbcException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        String receivedData;
        ResultSet rs1;
        ResultSet rs2;
        String selectData = "select sudoku_X,sudoku_Y,sudoku_value from "
                + db_Fields + " where sudoku_id=?;";
        String selectId = "select sudoku_id from " + db_Name + " where name='" + sudokuName + "';";

        try (Connection conn = connect();
             Statement preparedStatement = conn.createStatement()) {

            rs1 = preparedStatement.executeQuery(selectId);
            receivedData = rs1.getString(1);

            if (receivedData == null) {
                throw new JdbcException(ResourceBundle.getBundle("pl.comp.model.Exceptions")
                        .getObject("!wrong_sudoku_name").toString());
            }

            try (PreparedStatement read = conn.prepareStatement(selectData)) {
                read.setString(1,receivedData);

                rs2 = read.executeQuery();

                while (rs2.next()) {
                    board.set(rs2.getInt(1),rs2.getInt(2),rs2.getInt(3));
                }
            }
        }  catch (SQLException e) {
            throw new JdbcException(e);
        } catch (JdbcException e) {
            throw e;
        }

        return board;
    }

    @Override
    public void close() {

    }



}
