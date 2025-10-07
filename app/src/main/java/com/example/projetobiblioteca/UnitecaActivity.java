package com.example.projetobiblioteca;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.projetobiblioteca.databinding.ActivityUnitecaBinding;
import com.google.android.material.navigation.NavigationView;

public class UnitecaActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityUnitecaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUnitecaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // config da toolbar
        setSupportActionBar(binding.appBarUniteca.toolbar);

        // Ainda pensando oq vou fazer aqui
        binding.appBarUniteca.fab.setOnClickListener(view -> {
            // Snackbar.make(view, "não sei oq vou fazer aqui, vamos com calmaaaaaaaaaaaa", Snackbar.LENGTH_SHORT).show();
            binding.appBarUniteca.fab.setVisibility(View.GONE);
        });

        // Configuração do Drawer e NavigationView
        DrawerLayout drawerLayout = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // quais fragments estão disponiveis para navegar
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio,
                R.id.nav_acervo_fisico,
                R.id.nav_emprestimo,
                R.id.nav_reserva,
                R.id.nav_renovacao,
                R.id.nav_multa,
                R.id.nav_carteirinha,
                R.id.nav_base_de_dados
        )
                .setOpenableLayout(drawerLayout)
                .build();

        // Controlador de navegação
        NavController navController = Navigation.findNavController(
                this, R.id.nav_host_fragment_content_uniteca
        );

        // Integra o NavigationUI com a Toolbar e o Drawer
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // muda o titulo la em cima
        navController.addOnDestinationChangedListener((controller, destination, arguments) ->
                setTitle(destination.getLabel())
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.uniteca, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(
                this, R.id.nav_host_fragment_content_uniteca
        );
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = binding.drawerLayout;
        if (drawer.isDrawerOpen(androidx.core.view.GravityCompat.START)) {
            drawer.closeDrawer(androidx.core.view.GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
