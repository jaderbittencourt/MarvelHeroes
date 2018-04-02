package com.marvel.jaderbittencourt.marvelheroes.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    public static final String PUBLIC_KEY = "2eb31e82bd37d3c2ec901ab5b4fb2a7c";
    private static final String PRIVATE_KEY = "2e14bb1daf0ca272d9fe5dd775d1811e3d6930ae";
    private static final String BASE_URL = "https://gateway.marvel.com:443/v1/public/";

    private static MarvelAPI apiService;
    private static RestClient instance;

    protected RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(MarvelAPI.class);
    }

    private MarvelAPI getApiService() {
        return apiService;
    }

    public static MarvelAPI getApi() {
        if (instance == null)
            instance = new RestClient();
        return instance.getApiService();
    }

    public static String getTs() {
        return Long.toString(new Timestamp(System.currentTimeMillis()).getTime());
    }

    public static String generateHash(String ts) {
        return md5(ts + PRIVATE_KEY + PUBLIC_KEY);
    }

    private static String md5(String s) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}