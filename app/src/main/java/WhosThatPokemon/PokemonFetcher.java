package WhosThatPokemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class PokemonFetcher {

    private final String basePath = "https://pokeapi.co/api/v2";

    private String request(String apiParameter) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(basePath + apiParameter);
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader;
            if (connection.getResponseCode() > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            String s;
            StringBuffer responseContent = new StringBuffer();
            while ((s = reader.readLine()) != null) {
                responseContent.append(s);
            }
            reader.close();
            return responseContent.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
        }
        return null;
    }

    public Pokemon fetchPokemon(int id) {
        String responseContent = request("/pokemon/" + id);
        JSONObject json = new JSONObject(responseContent);
        int jsonId = json.getInt("id");
        String jsonLowcaseName = json.getString("name");
        String jsonName = jsonLowcaseName.substring(0,1).toUpperCase() + jsonLowcaseName.substring(1).toLowerCase();
        String jsonSprite = json.getJSONObject("sprites")
            .getJSONObject("other")
            .getJSONObject("official-artwork")
            .getString("front_default");
        return new Pokemon(jsonId, jsonName, jsonSprite);
    }

    public Pokemon fetchRandomPokemon() {
        int randomId = (int) Math.round((Math.random() * 897) + 1);
        return fetchPokemon(randomId);
    }

}
