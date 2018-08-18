package com.lemelo.controle;

import com.lemelo.controle.cliente.ClienteNode;
import com.lemelo.controle.ganho.GanhoNode;
import com.lemelo.controle.util.FabricaConexao;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Principal extends Application {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        new FabricaConexao().createTables();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.BOTTOM);

        Tab ganhoTab = createTab("Ganhos");
        ganhoTab.setContent(new GanhoNode().executar(ganhoTab));

        Tab clienteTab = createTab("Clientes");
        clienteTab.setContent(new ClienteNode().executar(clienteTab));

        tabPane.getTabs().addAll(clienteTab);

        int WIDTH_TAM = 940;
        int HEIGHT_TAM = 680;
        Scene scene = new Scene(tabPane, WIDTH_TAM, HEIGHT_TAM, Color.GRAY);
        primaryStage.setMinWidth(WIDTH_TAM);
        primaryStage.setMinHeight(HEIGHT_TAM);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Controle local de gastos");

        //primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private Tab createTab(String str) {
        Tab tab = new Tab();
        tab.setText(str);
        tab.setStyle("-fx-font: normal bold 15px 'verdana' ");
        tab.setClosable(false);
        return tab;
    }
}
