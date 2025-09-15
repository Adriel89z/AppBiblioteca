package com.example.projetobiblioteca.ui.acervo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetobiblioteca.R;

public class AcervoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acervo, container, false);
        setupClickListener(view);
        return view;
    }

    private void setupClickListener(View view) {
        view.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://biblioteca1.unisep.com.br/pergamum/biblioteca/index.php?id_biblioteca=5"));
            startActivity(browserIntent);
        });
    }
}