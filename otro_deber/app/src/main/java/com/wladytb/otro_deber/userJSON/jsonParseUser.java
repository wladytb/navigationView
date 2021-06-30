package com.wladytb.otro_deber.userJSON;

import com.wladytb.otro_deber.modelo.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface jsonParseUser {
    @GET("ws/usuario")
    Call<List<user>> validarUser(@Query("userName") String userName, @Query("password") String password);
}
