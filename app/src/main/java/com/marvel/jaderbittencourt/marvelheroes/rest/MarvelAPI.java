package com.marvel.jaderbittencourt.marvelheroes.rest;

import com.marvel.jaderbittencourt.marvelheroes.rest.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MarvelAPI {

    @Headers({"Accept: application/json","Content-type: application/json"})
    @GET("characters")
    Call<APIResponse> getHero(
            @Query("offset") Integer offset,
            @Query("limit") Integer limit,
            @Query("ts") String ts,
            @Query("apikey") String apiKey,
            @Query("hash") String hash);
}
