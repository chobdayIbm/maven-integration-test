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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AppTest 
{
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
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("https://httpbin.org/get"))
            .setHeader("User-Agent", "Java 11 HttpClient Bot")
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        
        
        JSONObject json = (JSONObject) parser.parse(response.body());
        assertEquals("218.212.245.238", json.get("origin"));
    }

    @Test
    @DisplayName("Test Post")
    void testPost() throws Exception {
        String testUrl = "https://httpbin.org/post";
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(testUrl))
            .setHeader("User-Agent", "Java 11 HttpClient Bot")
            .POST(BodyPublishers.ofString(""))
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        
        JSONObject json = (JSONObject) parser.parse(response.body());
        assertEquals("218.212.245.238", json.get("origin"));
    }

}
