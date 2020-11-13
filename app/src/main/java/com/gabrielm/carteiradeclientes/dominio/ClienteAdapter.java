package com.gabrielm.carteiradeclientes.dominio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.gabrielm.carteiradeclientes.ActCadCliente;
import com.gabrielm.carteiradeclientes.R;
import com.gabrielm.carteiradeclientes.dominio.entidades.Cliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolderCliente> {

    private List<Cliente> dados;

    public ClienteAdapter(List<Cliente> dados) {
        this.dados = dados;
    }

    @Override
    public ClienteAdapter.ViewHolderCliente onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_clientes, parent, false);

        ViewHolderCliente holderCliente = new ViewHolderCliente(view, parent.getContext());

        return holderCliente;
    }

    @Override
    public void onBindViewHolder(ClienteAdapter.ViewHolderCliente holder, int position) {

        if (dados != null && dados.size() > 0) {
            Cliente cliente = dados.get(position);

            holder.txtRecyclerNome.setText(cliente.nome);
            holder.txtRecyclerTelefone.setText(cliente.telefone);
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder {

        private TextView txtRecyclerNome;
        private TextView txtRecyclerTelefone;

        public ViewHolderCliente(View itemView, final Context context) {
            super(itemView);

            txtRecyclerNome = (TextView) itemView.findViewById(R.id.txtRecyclerNome);
            txtRecyclerTelefone = (TextView) itemView.findViewById(R.id.txtRecyclerTelefone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (dados.size() > 0) {

                        Cliente cliente = dados.get(getLayoutPosition());
                        Toast.makeText(context, "cliente: " + cliente.nome, Toast.LENGTH_SHORT).show();

                        Intent it = new Intent(context, ActCadCliente.class);
                        it.putExtra("CLIENTE", cliente);

                        ((AppCompatActivity) context).startActivityForResult(it, 0);
                    }
                }
            });
        }
    }
}
