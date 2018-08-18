package com.lemelo.controle.cliente;

import com.lemelo.controle.util.Gerador;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class ClienteNode {
    private TextField nomeTextField;
    private TableView<Cliente> tableView;

    public Node executar(Tab clienteTab) throws SQLException {
        atualizaTab(clienteTab);

        GridPane formularioGridPane = geraFormularioGridPane();
        GridPane botoesGridPane = geraBotoesGridPane();
        GridPane tableViewGridPane = geraTableViewGridPane();
        return new Gerador().node(formularioGridPane, botoesGridPane, tableViewGridPane);
    }

    private void atualizaTab(Tab clienteTab) {
        clienteTab.setOnSelectionChanged(event -> {
            if (clienteTab.isSelected()) {
                Platform.runLater(() -> {
                    try {
                        geraTableView();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    nomeTextField.requestFocus();
                });
            }
        });
    }

    private GridPane geraTableViewGridPane() {
        GridPane gridPane = new Gerador().cabecalhoGridPane();
        tableView = new TableView<>();
        tableView.setPrefWidth(5000);
        tableView.setPrefHeight(5000);
        gridPane.add(tableView,0,0);

        return gridPane;
    }

    private void geraTableView() throws SQLException {
        TableColumn<Cliente, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Cliente, String> nome = new TableColumn<>("Nome");
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        tableView.getColumns().clear();
        tableView.setItems(new ClienteDao().buscaClientes());
        tableView.getColumns().add(id);
        tableView.getColumns().add(nome);
    }

    private GridPane geraBotoesGridPane() {
        GridPane gridPane = new Gerador().cabecalhoGridPane();

        Button salvarButton = new Button("Salvar");
        salvarButton.setStyle("-fx-font: normal bold 15px 'verdana' ");
        salvarButton.setDefaultButton(true);

        salvarButton.setOnAction(event -> {
            String nomeStr = nomeTextField.getText();

            Cliente cliente = new Cliente();
            cliente.setNome(nomeStr);

            try {
                new ClienteDao().insert(cliente);
                limpaFormulario();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                geraTableView();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        gridPane.add(salvarButton,0,1);
        return gridPane;
    }

    private void limpaFormulario() {
        nomeTextField.clear();
    }

    private GridPane geraFormularioGridPane() {
        GridPane gridPane = new Gerador().cabecalhoGridPane();
        gridPane.add(criaLabel("Nome: "), 0, 0);
        gridPane.add(nomeTextField = criaTextField(5000), 0, 1);
        return gridPane;
    }

    private TextField criaTextField(double tamanho) {
        TextField textField = new TextField();
        textField.setPrefWidth(tamanho);
        return textField;
    }

    private Text criaLabel(String str) {
        Text text = new Text(str);
        text.setStyle("-fx-font: normal bold 15px 'verdana' ");
        return text;
    }
}
