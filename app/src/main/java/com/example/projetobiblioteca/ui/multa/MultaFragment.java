package com.example.projetobiblioteca.ui.multa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.projetobiblioteca.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MultaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multa, container, false);

        Button downloadButton = view.findViewById(R.id.bt_multa);
        downloadButton.setOnClickListener(v -> abrirOuSalvarPdf(view.getContext()));

        return view;
    }

    private void abrirOuSalvarPdf(Context context) {
        try {
            // puxa o pdf do app "no assets" para memoria temporaria
            File pdfFile = new File(context.getCacheDir(), "regulamento_da_biblioteca.pdf");

            if (!pdfFile.exists()) {
                try (InputStream in = context.getAssets().open("regulamento_da_biblioteca.pdf");
                     FileOutputStream out = new FileOutputStream(pdfFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer);
                    }
                }
            }

            Uri pdfUri = FileProvider.getUriForFile(
                    context,
                    context.getPackageName() + ".fileprovider",
                    pdfFile
            );

            // não vai baixar o pdf, vai abrir direto
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(pdfUri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            context.startActivity(intent);

        //mensagem caso role algo
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erro ao abrir o PDF", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Nenhum leitor de PDF encontrado.", Toast.LENGTH_LONG).show();
        }
    }
}
