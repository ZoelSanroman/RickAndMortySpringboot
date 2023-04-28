package com.cebem.rickandmorty.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.services.RickAndMortyService;

@Controller
public class WebController {
    @Autowired
    RickAndMortyService rickAndMortyService;

    @RequestMapping("/rickandmorty/allTemplate")
    public String charactersTemplate(Model modelo) {
        CharactersModel charactersModel = rickAndMortyService.getAllCharacters();
        modelo.addAttribute("creator", "Creado por Zoel");
        modelo.addAttribute("characters", charactersModel.results);

        return "RickAndMortyAll";
    }

    @RequestMapping("/rickandmorty/count")
    public String CharactersCountTemplate(Model modelo){
        int count = rickAndMortyService.getCharactersCount();
        modelo.addAttribute("count", count);
        return "rickandmortyCount";
    } 
}
