package com.cebem.rickandmorty.controllers.utils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cebem.rickandmorty.models.CharacterModel;
import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.services.RickAndMortyService;

@RestController
@CrossOrigin(origins = "*")
public class rickController {

    @GetMapping("/")
    public String saluda() {
        return "Bienvenid@ a mi api rest de rickAndMorty";

    }

    @GetMapping("/random")
    public String random() {
        return Math.round(Math.random() * 10) + "";
    }

    @GetMapping("/palindromo/{word}")
    public String palindromo(@PathVariable String word) {
        return Utils.isPalindrome(word) ? "Si es palindrome" : "No es palindrome";

    }

    // http://localhost:8080/add?n1=4&n2=5
    @GetMapping("/add")
    public String add(@RequestParam String n1, @RequestParam String n2) {
        float resultado = Float.parseFloat(n1) + Float.parseFloat(n2);
        Object params[] = { n1, n2, resultado };
        return MessageFormat.format("La suma de {0} mas {1} es igual a {2}", params);

    }

    @PostMapping("/saveOnDisk")
    public String saveOnDisk(@RequestParam Map<String, String> body) {
        String name = body.get("name");
        String price = body.get("price");
        String info = name + "" + price + "/n";

        try {
            Utils.writeOnDisk("datos.txt", info);
        } catch (IOException e) {
            return "Error al intentar escribir en el fichero";
        }

        return "Gracias por enviar el formulario, los datos se han guardado en el servidor";

    }

    @DeleteMapping("/deleteFromDisk")
    public String remove() {
        boolean resultado = Utils.deleteFromDisk("datos.txt");
        return resultado ? "Borrado correcto" : "Ne he podido borrar";

    }

    // Ejercicio 1
    @GetMapping("/cuadrado")
    public String cuadraDitto(@RequestParam String num) {
        float numero = Float.parseFloat(num);
        try {
            return numero * numero + " ";
        } catch (NumberFormatException ex) {
            return "El numero no es valido";
        }
    }

    // Ejercicio 2
    @DeleteMapping("/emptyFile")
    public String emptyFile() {
        try {
            Utils.emptyFile("datos.txt");
        } catch (IOException ex) {
            return "Error al intentar vaciar el fichero";
        }
        return "El fichero ha sido vaciado correctamente";
    }

    // Ejercicio 3
    @GetMapping("/info")
    public String obtenerInformacion() {
        try {
            String info = Utils.readFromDisk("datos.txt");
            return info;
        } catch (IOException ex) {
            return "Error al intentar leer el archivo";
        }
    }

    // Ejercicio 4
    // (http://localhost:8080/mayor?num1=3&num2=7&num3=2)
    @GetMapping("/mayor")
    public String mayor(@RequestParam String num1, @RequestParam String num2, @RequestParam String num3) {
        try {
            float n1 = Float.parseFloat(num1);
            float n2 = Float.parseFloat(num2);
            float n3 = Float.parseFloat(num3);

            return "El mayor de los números es: " + Utils.maxOfElements(n1, n2, n3);

        } catch (NumberFormatException ex) {
            return "ERROR";
        }
    }

    // Ejercicio 5
    @GetMapping("/cambiarLetra")
    public String cambio(@RequestParam String frase) {
        return Utils.capitalizar(frase);
    }

    // Ejercicio 6
    @GetMapping("/randomColors")
    public String randomColors() {
        final int COLOR_COUNT = 3;
        final String[] BASICCOLORS = { "negro", "azul", "marrón", "gris", "verde", "naranja", "rosa", "púrpura", "rojo",
                "blanco", "amarillo" };
        if (COLOR_COUNT > BASICCOLORS.length) {
            throw new RuntimeException("Limite de colores superado");
        }
        ArrayList<String> colores = new ArrayList<String>(Arrays.asList(BASICCOLORS));
        String finalColors = "";
        for (int i = 0; i < COLOR_COUNT; i++) {
            int random = Utils.getRandomValue(colores.size());
            finalColors += colores.remove(random) + " ";

        }

        return finalColors;
    }

    @Autowired
    RickAndMortyService rickAndMortyService;

    /**
     * Crear un endpoint que devuelva un personaje aleatorio de Rick and Morty
     */
    @GetMapping("/rickandmorty/random")
    public String randomCharacter() {
        CharacterModel characterModel = rickAndMortyService.getCharacterRandom();
        return characterModel.name + "<br> <img width='200' src='" + characterModel.image + "'/>";

    }

    @GetMapping("/rickandmorty/all")
    public String characters() {
        CharactersModel allCharactersModel = rickAndMortyService.getAllCharacters();
        String html = "<html>";
        for (CharacterModel characterModel : allCharactersModel.results) {
            html += characterModel.name;
            html += "<br/>";
            html += "<img width= '100px' src='" + characterModel.image + "'>";
            html += "<hr/>";
        }
        return html;
    }
}