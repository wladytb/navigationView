package com.wladytb.otro_deber;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.security.acl.Group;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.wladytb.otro_deber.R.color.marron_my;
import static com.wladytb.otro_deber.R.color.verde_my;
import static org.jetbrains.anko.ToastsKt.longToast;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView navView;
    ActionBarDrawerToggle toggle;
    TextView txtRol, txtNombre;
    CircleImageView img;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        navView = findViewById(R.id.nav_view);

        txtNombre = (TextView) navView.getHeaderView(0).findViewById(R.id.txtnombre);
        txtRol = (TextView) navView.getHeaderView(0).findViewById(R.id.txtRol);
        img = (CircleImageView) navView.getHeaderView(0).findViewById(R.id.imageView);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView mMenu = findViewById(R.id.nav_view);
        Bundle parametro = this.getIntent().getExtras();
        if (parametro != null) {
            txtNombre.setText(parametro.getString("nombre"));
            txtRol.setText("Rol: " + parametro.getString("rol"));
            Picasso.get().load(parametro.getString("photo")).into(img);
            if (parametro.getString("rol").equals("admin")) {
                mMenu.getMenu().add(R.id.grupoItem, mMenu.getMenu().size(), mMenu.getMenu().size(), "Productos").setIcon(R.mipmap.producto_foreground);
                mMenu.getMenu().add(R.id.grupoItem, mMenu.getMenu().size(), mMenu.getMenu().size(), "Reportes").setIcon(R.mipmap.reporte_foreground);
                mMenu.getMenu().add(R.id.grupoItem, mMenu.getMenu().size(), mMenu.getMenu().size(), "Cajeras").setIcon(R.mipmap.cajera_foreground);
                mMenu.getMenu().add(R.id.grupoItem, mMenu.getMenu().size(), mMenu.getMenu().size(), "Configuraciones").setIcon(R.mipmap.admin_foreground);
            } else {
                mMenu.getMenu().add(R.id.grupoItem, mMenu.getMenu().size(), mMenu.getMenu().size(), "Productos").setIcon(R.mipmap.producto_foreground);
                mMenu.getMenu().add(R.id.grupoItem, mMenu.getMenu().size(), mMenu.getMenu().size(), "Compras").setIcon(R.mipmap.compras_foreground);
                mMenu.getMenu().add(R.id.grupoItem, mMenu.getMenu().size(), mMenu.getMenu().size(), "Pagos").setIcon(R.mipmap.pagos_foreground);
                mMenu.getMenu().add(R.id.grupoItem, mMenu.getMenu().size(), mMenu.getMenu().size(), "Otros").setIcon(R.mipmap.otros_foreground);
            }
        }
        mMenu.getMenu().add(R.id.redes, mMenu.getMenu().size(), mMenu.getMenu().size(), "Compartir").setIcon(R.mipmap.compartir_foreground);
        mMenu.getMenu().add(R.id.redes, mMenu.getMenu().size(), mMenu.getMenu().size(), "Contactos").setIcon(R.mipmap.contacto_foreground);
        setupDrawerContent(navView);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        for (int i = 0; i < navView.getMenu().size(); i++) {
                            MenuItem item = navView.getMenu().getItem(i);
                            if (item.isChecked())
                                item.setChecked(false);
                        }
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    @SuppressLint("ResourceAsColor")
    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getTitle().toString()) {
            case "Productos":
                fragmentClass = fragmento_item_producto.class;
                break;
            case "Reportes":
                fragmentClass = fragmento_reporte.class;
                break;
            case "Cajeras":
                fragmentClass = fragmento_cajera.class;
                break;
            case "Configuraciones":
                fragmentClass = fragmento_configuracion.class;
                break;
            case "Compras":
                fragmentClass = fragmento_compra.class;
                break;
            case "Pagos":
                fragmentClass = fragmentos_pagos.class;
                break;
            case "Otros":
                fragmentClass = fragmento_otro.class;
                break;
            default:
                fragmentClass = fragmento_defaul.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        menuItem.setChecked(true);
        Toast.makeText(getApplicationContext(),"selecionaste: "+menuItem.getTitle(),Toast.LENGTH_LONG).show();
        drawer.closeDrawers();
    }
}