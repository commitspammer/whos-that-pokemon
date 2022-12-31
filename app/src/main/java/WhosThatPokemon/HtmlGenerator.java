package WhosThatPokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class HtmlGenerator {

    private List<String> getHeadAndScript() {
        return List.of(
                "<!DOCTYPE html>",
                "<html>",
                "<head>",
                "<title>Who's That Pokemon?</title>",
                "<meta name='viewport' content='width=device-width, initial-scale=1'>",
                "<link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>",
                "<link href='http://fonts.cdnfonts.com/css/pokemon-solid' rel='stylesheet'>",
                "<style>",
                "#silhouette { -webkit-filter: grayscale(100) brightness(0); filter: grayscale(100) brightness(0); opacity: 1; }",
                ".pokemon-font { font-family: Pokemon Solid, sans-serif; }",
                ".stroke { -webkit-text-stroke: 1px blue; text-stroke: 1px blue; }",
                "</style>",
                "</head>",
                "<script>",
                "function revealPokemon() { document.getElementById('silhouette').style.display = 'none'; document.getElementById('original').style.display = 'inline'; document.getElementById('answer').style.visibility = 'visible'; }",
                "</script>"
        );
    }

    private List<String> getButtons(PokemonFetcher fetcher, String answer) {
        List<String> buttons = new ArrayList<>();
        Function<String, String> button = n ->
        "<button class='w3-button we-ripple w3-border w3-border-blue w3-yellow w3-text-black' onclick='revealPokemon();'><b>" + n + "</b></button>";
        buttons.add(button.apply(answer));
        for (int i = 1; i < 4; i++) {
            buttons.add(button.apply(fetcher.fetchRandomPokemon().name));
        }
        Collections.shuffle(buttons);
        return buttons;
    }

    private List<String> getBody(PokemonFetcher fetcher, Pokemon pokemon) {
        List<String> body = new ArrayList<>();
        List.of(
                "<body class='w3-light-blue'>",
                "<div class='w3-display-container w3-red w3-border w3-border-black w3-round-xxlarge' style='margin-left:10vw;margin-right:10vw;margin-top:5vh;margin-bottom:5vh;height:90vh'>",

                "<div class='w3-display-topmiddle w3-center w3-padding' style='width:100%'>",
                "<h1 class='pokemon-font stroke w3-text-yellow' style='text-align:center'>Who's That Pokemon?</h1>",
                "</div>",

                "<div class='w3-display-middle w3-center w3-padding' style='width:100%'>",
                "<img src='" + pokemon.sprite + "' id='silhouette' style='display:inline;width:100%;max-width:400px'>",
                "<img src='" + pokemon.sprite + "' id='original' style='display:none;width:100%;max-width:400px'>",
                "<h3 id='answer' class='pokemon-font stroke w3-text-yellow' style='text-align:center;visibility:hidden'>It's " + pokemon.name + "!</h3>",
                "</div>",

                "<div class='w3-display-bottommiddle w3-center w3-padding' style='width:100%'>"
        ).forEach(body::add);
        getButtons(fetcher, pokemon.name).forEach(body::add);
        List.of(
                "</div>",
                "</div>",
                "</body>"
        ).forEach(body::add);
        return body;
    }

    public String generateHtml() {
        PokemonFetcher fetcher = new PokemonFetcher();
        Pokemon pokemon = fetcher.fetchRandomPokemon();
        StringBuilder builder = new StringBuilder();
        getHeadAndScript().forEach(builder::append);
        getBody(fetcher, pokemon).forEach(builder::append);
        return builder.toString();
    }

    public String generate() {
        PokemonFetcher pokemonFetcher = new PokemonFetcher();
        Pokemon pokemon = pokemonFetcher.fetchRandomPokemon();
        List<String> randomNames = new ArrayList<>();
        randomNames.add(pokemon.name);
        for (int i = 1; i < 5; i++) {
            randomNames.add(pokemonFetcher.fetchRandomPokemon().name);
        }
        Collections.shuffle(randomNames);
        StringBuilder builder = new StringBuilder();
        String[] arr = {
                "<!DOCTYPE html>",
                "<html>",
                "<head>",
                "<title>Who's That Pokemon?</title>",
                "<meta name='viewport' content='width=device-width, initial-scale=1'>",
                "<link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>",
                "<link href='http://fonts.cdnfonts.com/css/pokemon-solid' rel='stylesheet'>",
                "<style>",
                "#silhouette { -webkit-filter: grayscale(100) brightness(0); filter: grayscale(100) brightness(0); opacity: 1; }",
                ".pokemon-font { font-family: Pokemon Solid, sans-serif; color: yellow; }",
                "</style>",
                "</head>",
                "<body class='w3-light-grey'>",
                "<div class='w3-row'>",
                "<div class='w3-col l9 m6 w3-red w3-center'>",

                "<img src='" + pokemon.sprite
                        + "' id='silhouette' style='display:inline;width:45%'>",
                "<img src='" + pokemon.sprite + "' id='original' style='display:none;width:45%'>",
                "<img src='https://img5.ugamezone.com/201901/2019/0423/65/f/693236/original.png' alt='Who's That Pokemon?' style=';width:45%'>",
                "<h1 class='pokemon-font'>Who's That Pokemon?</h1>",

                "</div>",
                "<div class='w3-col l3 m6 w3-light-blue w3-center'>",
                "<button class='w3-btn w3-mobile' onclick='revealPokemon()'>Bulbasaur</button>",
                "<button class='w3-btn w3-mobile' onclick='revealPokemon()'>Charmander</button>",
                "<button class='w3-btn w3-mobile' onclick='revealPokemon()'>Squirtle</button>",
                "<button class='w3-btn w3-mobile' onclick='revealPokemon()'>Mew</button>",
                "<button class='w3-btn w3-mobile' onclick='revealPokemon()'>Mewtwo</button>",
                "</div>",
                "</div>",
                "</body>",
                "<script>",
                "function revealPokemon() { document.getElementById('silhouette').style.display = 'none'; document.getElementById('original').style.display = 'inline'; }",
                "</script>",
                // "<!DOCTYPE html>",
                // "<html>",
                // "<head>",
                // "<title>Who's That Pokemon?</title>",
                // "<meta name='viewport' content='width=device-width, initial-scale=1'>",
                // "<link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>",
                // "<link href='http://fonts.cdnfonts.com/css/pokemon-solid' rel='stylesheet'>",
                // "<style>",
                // "#silhouette { -webkit-filter: grayscale(100) brightness(0); filter: grayscale(100) brightness(0); opacity: 1; }",
                // ".pokemon-font { font-family: Pokemon Solid, sans-serif; color: yellow; }",
                // "</style>",
                // "</head>",
                // "<body class='w3-light-blue'>",
                // "<div class='w3-row'>",
                // "<div class='w3-col l3 m2 s1 w3-light-blue'>A</div>",
                // "<div class='w3-col l6 m8 s10 w3-red w3-center'>",

                // "<img src='" + pokemonImage + "' id='silhouette' style='display:inline'>",
                // "<img src='" + pokemonImage + "' id='original' style='display:none'>",
                // "<img src='https://img5.ugamezone.com/201901/2019/0423/65/f/693236/original.png' alt='Who's That Pokemon?'>",
                // "<h1 class='pokemon-font'>Who's That Pokemon?</h1>",
                // "<button class='w3-btn w3-mobile' onclick='revealPokemon()'>Bulbasaur</button>",
                // "<button class='w3-btn w3-mobile' onclick='revealPokemon()'>Charmander</button>",
                // "<button class='w3-btn w3-mobile' onclick='revealPokemon()'>Squirtle</button>",
                // "<button class='w3-btn w3-mobile' onclick='revealPokemon()'>Mew</button>",
                // "<button class='w3-btn w3-mobile' onclick='revealPokemon()'>Mewtwo</button>",

                // "</div>",
                // "<div class='w3-col l3 m2 s1 w3-light-blue'>B</div>",
                // "</div>",
                // "</body>",
                // "<script>",
                // "function revealPokemon() { document.getElementById('silhouette').style.display = 'none'; document.getElementById('original').style.display = 'inline'; }",
                // "</script>",
        };
        for (String s : arr) {
            builder.append(s);
        }
        //return "<h1>Hello, World!<h1>";
        return builder.toString();
    }

}
