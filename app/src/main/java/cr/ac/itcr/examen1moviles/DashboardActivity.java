package cr.ac.itcr.examen1moviles;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AboutFragment.OnFragmentInteractionListener,
        AgregarFragment.OnFragmentInteractionListener,
        Editar_ElimFragment.OnFragmentInteractionListener,
        InicioFragment.OnFragmentInteractionListener,
        GaleriaFragment.OnFragmentInteractionListener
  {


    public void cancelar(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Se instancia el navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Se carga el fragmento de inicio al Dashboard
        Fragment fragment = new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Aquí se programan las acciones cada una de las opciones del menu
        int id = item.getItemId();
        //Agergar flores
        if (id == R.id.nav_agregar) {
            Fragment fragment = new AgregarFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
            //Editar o eliminar tipos de flores
        } else if (id == R.id.nav_editar) {
            Fragment fragment = new Editar_ElimFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
            //Galería de infromación
        }  else if (id == R.id.nav_galeria) {
            Fragment fragment = new GaleriaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
            // Información sobre la app
        } else if (id == R.id.nav_about) {
            Fragment fragment = new AboutFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}