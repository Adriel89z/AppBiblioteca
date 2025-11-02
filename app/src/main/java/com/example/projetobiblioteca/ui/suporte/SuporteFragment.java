package com.example.projetobiblioteca.ui.suporte;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetobiblioteca.R;

public class SuporteFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suporte, container, false);

        TextView txtSite = view.findViewById(R.id.txt_site);
        TextView txtEmail = view.findViewById(R.id.txt_email);
        TextView txtContato = view.findViewById(R.id.txt_contato);

        // vai abrir o site
        txtSite.setOnClickListener(v -> {
            String url = "https://portal.unisepe.com.br/univr/biblioteca/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // vai copiar o email
        txtEmail.setOnClickListener(v -> {
            String email = "biblioteca@scelisul.com.br";
            ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("email", email);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(requireContext(), "E-mail copiado!", Toast.LENGTH_SHORT).show();
        });

        // a ideia é q abra o wts
        txtContato.setOnClickListener(v -> {
            String phoneNumber = "+551338282840";
            String url = "https://wa.me/" + phoneNumber;
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(requireContext(), "Não foi possível abrir o WhatsApp", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
