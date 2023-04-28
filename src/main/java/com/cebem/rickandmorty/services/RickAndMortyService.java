package com.cebem.rickandmorty.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebem.rickandmorty.controllers.utils.Utils;
import com.cebem.rickandmorty.models.CharacterModel;
import com.cebem.rickandmorty.models.CharactersModel;

@Service
public class RickAndMortyService {
    @Autowired
    RestTemplate restTemplate;
    final static String BASE_API = "https://rickandmortyapi.com/api";

    public CharacterModel getCharacterRandom() {
        final int TOTAL_CHARACTERS = 826;
        int random = Utils.getRandomValue(TOTAL_CHARACTERS - 1) + 1;
        String url = "/character/" + random;

        CharacterModel datos = restTemplate.getForObject(BASE_API + url, CharacterModel.class);
        return datos;
    }

    public CharactersModel getAllCharacters() {
        String url = "/character/";
        CharactersModel datos = restTemplate.getForObject(BASE_API + url, CharactersModel.class);
        return datos;
    }

    public int getCharactersCount() {
        String url = "/character/";
        CharactersModel datos = restTemplate.getForObject(BASE_API + url, CharactersModel.class);
        return datos.info.count;
    }
}
