package com.gabrielm.carteiradeclientes.dominio.repositorio;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.gabrielm.carteiradeclientes.dominio.entidades.Cliente;

import java.util.List;

public class ClienteRepositorio {

    private SQLiteDatabase conexao;

    public ClienteRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir(Cliente cliente) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", cliente.nome);
        contentValues.put("ENDERECO", cliente.endereco);
        contentValues.put("EMAIL", cliente.email);
        contentValues.put("TELEFONE", cliente.telefone);

        conexao.insertOrThrow("CLIENTE", null, contentValues);

    }

    public void excluir(int codigo) {

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        conexao.delete("CLIENTE", "CODIGO = ?", paramentros);


    }

    public void alterar(Cliente cliente) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", cliente.nome);
        contentValues.put("ENDERECO", cliente.endereco);
        contentValues.put("EMAIL", cliente.email);
        contentValues.put("TELEFONE", cliente.telefone);

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(cliente.codigo);

        conexao.update("CLIENTE", contentValues, "CODIGO = ?", paramentros);
    }

    public List<Cliente> buscarTodos() {

        return null;
    }

    public Cliente buscarCliente(int codigo) {

        return null;
    }
}
