package org.chobday;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Map;
import java.util.LinkedHashMap;

public class AppTest 
{
    private final String BASE_URL = System.getenv("BASE_URL");
    // one instance, reuse
    private final HttpClient httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .build();

    private final JSONParser parser = new JSONParser();

    @Test
    @DisplayName("Canary Test")
    void testTrue()
    {
        assertTrue( true );
    }

    @Test
    @DisplayName("Test Get")
    void testGet() throws Exception
    {
        String testUrl = BASE_URL + "/echo/get/json";
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(testUrl))
            .build();
        
        HttpResponse<String> response = httpClient.send(
            request, HttpResponse.BodyHandlers.ofString());
        
        assertEquals(200, response.statusCode());        
        JSONObject json = (JSONObject) parser.parse(response.body());
        assertEquals("true", json.get("success"));
    }

    @Test
    @DisplayName("Test Post")
    void testPost() throws Exception {
        String testUrl = BASE_URL + "/echo/post/json";

        Map map = new LinkedHashMap<>();
        map.put("login", "colin");
        map.put("password", "test");
        Map details = new LinkedHashMap<>();
        details.put("firstName", "colin");
        details.put("prefix", "Mr.");
        map.put("details", details);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(testUrl))
            .setHeader("Accept", "application/json")
            .POST(BodyPublishers.ofString(JSONValue.toJSONString(map)))
            .build();


        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        assertEquals(200, response.statusCode()); 
        JSONObject json = (JSONObject) parser.parse(response.body());
        assertEquals("true", json.get("success"));
    }

}
