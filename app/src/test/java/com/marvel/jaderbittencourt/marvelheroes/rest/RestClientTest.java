package com.marvel.jaderbittencourt.marvelheroes.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class RestClientTest {

    @Test
    public void shouldReturnAPIInstance() {
        assertNotNull(RestClient.getApi());
    }

    @Test
    public void shouldGenerateTS() {
        assertNotNull(RestClient.getTs());
    }

    @Test
    public void shouldGenerateHash() {
        String hash = RestClient.generateHash(RestClient.getTs());
        assertNotNull(hash);
    }


}
