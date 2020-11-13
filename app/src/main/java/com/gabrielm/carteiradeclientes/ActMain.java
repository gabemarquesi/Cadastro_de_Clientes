package com.gabrielm.carteiradeclientes;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.gabrielm.carteiradeclientes.database.DadosOpenHelper;
import com.gabrielm.carteiradeclientes.dominio.ClienteAdapter;
import com.gabrielm.carteiradeclientes.dominio.entidades.Cliente;
import com.gabrielm.carteiradeclientes.dominio.repositorio.ClienteRepositorio;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.sql.SQLData;
import java.util.List;

public class ActMain extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView lstDados;
    private ConstraintLayout layoutContentMain;

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;

    private ClienteAdapter clienteAdapter;

    private ClienteRepositorio clienteRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        lstDados = (RecyclerView) findViewById(R.id.lstDados);
        layoutContentMain = (ConstraintLayout) findViewById(R.id.layoutContentMain);

        criarConexao();

        lstDados.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);

        clienteRepositorio = new ClienteRepositorio(conexao);

        List<Cliente> dados = clienteRepositorio.buscarTodos();

        clienteAdapter = new ClienteAdapter(dados);

        lstDados.setAdapter(clienteAdapter);
    }

    private void criarConexao() {

        try {

            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            // Snackbar.make(layoutContentMain, R.string.msg_conexao_sucesso, Snackbar.LENGTH_SHORT)
            //        .setAction(R.string.action_lbl_ok, null).show();

        } catch (SQLException exception) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.lbl_erro);
            dlg.setMessage(exception.getMessage());
            dlg.setNeutralButton(R.string.action_lbl_ok, null);
            dlg.show();
        }
    }

    public void cadastrar(View view) {
        Intent it = new Intent(ActMain.this, ActCadCliente.class);
        startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Cliente> dados = clienteRepositorio.buscarTodos();
        clienteAdapter = new ClienteAdapter(dados);
        lstDados.setAdapter(clienteAdapter);
    }
}