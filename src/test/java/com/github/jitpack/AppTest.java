package com.github.jitpack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AppTest 
{
    // one instance, reuse
    private final HttpClient httpClient = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2)
    .build();

    App app = new App();

    @Test
    public void testApp()
    {
        assertEquals("Hello world", app.greet("world"));
    }

    @Test
    public void testTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testApi()
    {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://httpbin.org/get"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(200, response.statusCode());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
