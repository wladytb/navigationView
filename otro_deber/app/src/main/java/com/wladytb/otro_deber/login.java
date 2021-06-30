package com.wladytb.otro_deber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wladytb.otro_deber.modelo.user;
import com.wladytb.otro_deber.userJSON.jsonParseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    Button btnLogin;
    EditText txtuserName, txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtuserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata(txtuserName.getText().toString(), txtPassword.getText().toString());
            }
        });
    }
    public void getdata(String userName, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mis-deberes.herokuapp.com/").
                        addConverterFactory(GsonConverterFactory.create()).build();

        jsonParseUser jsn = retrofit.create(jsonParseUser.class);
        Call<List<user>> call = jsn.validarUser(userName, password);
        call.enqueue(new Callback<List<user>>() {
            @Override
            public void onResponse(Call<List<user>> call, Response<List<user>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<user> lista = response.body();
                if (lista.size() > 0) {
                    Intent intent = new Intent(login.this, MainActivity.class);
                    intent.putExtra("nombre",lista.get(0).getFullName());
                    intent.putExtra("rol",lista.get(0).getRol());
                    intent.putExtra("photo",lista.get(0).getPhoto());
                    login.this.startActivity(intent);
                }else{
                    Context context = getApplicationContext();
                    Toast.makeText(context, "datos incorrectos!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<user>> call, Throwable t) {
                Context context = getApplicationContext();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}