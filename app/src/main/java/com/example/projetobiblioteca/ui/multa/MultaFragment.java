package com.example.projetobiblioteca.ui.multa;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.projetobiblioteca.R;

public class MultaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multa, container, false);
        Button downloadButton = view.findViewById(R.id.bt_multa);
        downloadButton.setOnClickListener(v -> {
            iniciarDownload(view.getContext());
        });

        return view;
    }

    private void iniciarDownload(Context context) {
        try {
            String url = "https://example.com/Regulamento-Institucional-Biblioteca-Central-UNISEPE-pdf.pdf";

            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

            request.setTitle("Baixando PDF");
            request.setDescription("O arquivo está sendo baixado...");
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "regulamento_da_biblioteca.pdf");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            if (downloadManager != null) {
                downloadManager.enqueue(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Erro ao iniciar o download.", Toast.LENGTH_SHORT).show();
        }
    }
}