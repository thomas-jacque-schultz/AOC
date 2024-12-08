package fr.schultz.inputing;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpConnector {

        public String get(int year, int day, String sessionCookie) throws IOException, InterruptedException {

            // create a client
            var client = HttpClient.newHttpClient();

            // create a requestBuilder
            var requestBuilder = HttpRequest.newBuilder(
                    URI.create(String.format("https://adventofcode.com/%d/day/%d/input", year, day)));
            if (sessionCookie != null) {
                requestBuilder = requestBuilder.header("Cookie", "session=" + sessionCookie);
            }
            var request = requestBuilder.build();

            // use the client to send the request
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // the response:
            return response.body();
        }


}
