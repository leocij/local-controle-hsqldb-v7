package com.lemelo.controle.util;

import java.sql.*;
import java.util.List;

public class FabricaConexao {
    public void createTables() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(new BibliotecaString().getUrlBanco(),"SA","");
            statement = connection.createStatement();
            statement.executeUpdate(new BibliotecaString().createCliente());
            statement.executeUpdate(new BibliotecaString().createGanho());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
            connection.close();
        }
    }

    public void insert(List<Object> list, String insertSql) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(new BibliotecaString().getUrlBanco(), "SA", "");
            preparedStatement = connection.prepareStatement(insertSql);
            for(int i=0; i<list.size(); i++) {
                if (list.get(i) instanceof Timestamp) {
                    preparedStatement.setTimestamp(i+1, (Timestamp) list.get(i));
                } else if (list.get(i) instanceof String) {
                    preparedStatement.setString(i+1, (String) list.get(i));
                }

            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
        }
    }
}
