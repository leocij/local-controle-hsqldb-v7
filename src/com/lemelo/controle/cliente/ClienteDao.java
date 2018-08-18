package com.lemelo.controle.cliente;

import com.lemelo.controle.util.BibliotecaString;
import com.lemelo.controle.util.FabricaConexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ClienteDao {
    void insert(Cliente cliente) throws SQLException {
        String nomeStr = cliente.getNome();
        List<Object> list = new ArrayList<>();
        list.add(nomeStr);
        String insertSql = "INSERT INTO CLIENTE (NOME) VALUES (?)";
        new FabricaConexao().insert(list, insertSql);
    }

    ObservableList<Cliente> buscaClientes() throws SQLException {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        String sqlSelect = "SELECT * FROM CLIENTE";

        connection = DriverManager.getConnection(new BibliotecaString().getUrlBanco(),"SA","");
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlSelect);

        while (resultSet.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(resultSet.getLong("id"));
            cliente.setNome(resultSet.getString("nome"));
            clientes.add(cliente);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return clientes;
    }
}
