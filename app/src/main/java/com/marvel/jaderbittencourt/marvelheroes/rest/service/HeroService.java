package com.marvel.jaderbittencourt.marvelheroes.rest.service;

import com.marvel.jaderbittencourt.marvelheroes.rest.RestClient;
import com.marvel.jaderbittencourt.marvelheroes.rest.model.APIResponse;

import retrofit2.Call;

public class HeroService {

    public static Call<APIResponse> getHero(Integer offset, Integer limit, String ts, String apiKey, String hash) {
        return RestClient.getApi().getHero(offset, limit, ts, apiKey, hash);
    }
}
