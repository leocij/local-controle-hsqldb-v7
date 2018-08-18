package com.lemelo.controle.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Gerador {
    public GridPane cabecalhoGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 2, 0, 2));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.TOP_LEFT);
        return gridPane;
    }

    public Node node(GridPane formularioGridPane, GridPane botoesGridPane, GridPane tableViewGridPane) {
        List<GridPane> list = new ArrayList<>();
        list.add(formularioGridPane);
        list.add(botoesGridPane);
        list.add(tableViewGridPane);
        GridPane principalGridPane = geraPrincipalGridPane(list);
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(0, 2, 0, 2));
        vBox.getChildren().addAll(principalGridPane);
        return vBox;
    }

    private GridPane geraPrincipalGridPane(List<GridPane> list) {
        GridPane gridPane = cabecalhoGridPane();
        for(int i=0; i<list.size(); i++) {
            gridPane.add(list.get(i),0, i+1);
        }
        return gridPane;
    }
}
