package com.example.projetobiblioteca.ui.base_dados;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetobiblioteca.R;

public class BaseDadosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_dados, container, false);

        view.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://portal.unisepe.com.br/univr/biblioteca/base-de-dados/"));
            startActivity(browserIntent);
        });

        return view;
    }
}