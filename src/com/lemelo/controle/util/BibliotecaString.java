package com.lemelo.controle.util;

public class BibliotecaString {
    public String getUrlBanco() {
        //Como conectar no banco.
        //java -classpath hsqldb.jar org.hsqldb.util.DatabaseManager
        //Standalone
        //jdbc:hsqldb:file:/home/leocij/aula/local-controle-hsqldb-v3/database_v1/db
        //jdbc:hsqldb:file:E:/Leoci/Projetos Intellij/local-controle-hsqldb-v2/database/db
        //jdbc:hsqldb:file:D:/ProjetosIntellij/local-controle-hsqldb-v2/database/db
        return "jdbc:hsqldb:file:database_v6/db";
    }

    public String createCliente() {
        return "CREATE TABLE IF NOT EXISTS CLIENTE (\n" +
                "    ID INTEGER IDENTITY NOT NULL,\n" +
                "    NOME VARCHAR(200),\n" +
                "    CONSTRAINT PK_CLIENTE_ID PRIMARY KEY (ID)\n" +
                ");";
    }

    public String createGanho() {
        return "CREATE TABLE IF NOT EXISTS GANHO (\n" +
                "  ID INTEGER IDENTITY NOT NULL,\n" +
                "  DATA DATE NOT NULL,\n" +
                "  CLIENTE_ID INTEGER NOT NULL,\n" +
                "  CONSTRAINT PK_GANHO_ID PRIMARY KEY (ID),\n" +
                "  CONSTRAINT FK_GANHO_CLIENTE_ID FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE (ID)\n" +
                ");";
    }
}
