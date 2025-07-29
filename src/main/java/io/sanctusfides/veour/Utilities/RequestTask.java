package io.sanctusfides.veour.Utilities;

import javafx.concurrent.Task;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class RequestTask extends Task<HttpResponse<String>> {

    URI url;

    public RequestTask(URI url) {
        this.url = url;
    }

    @Override
    protected HttpResponse<String> call() throws Exception {
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(url)
                    .timeout(Duration.of(10, ChronoUnit.SECONDS))
                    .GET()
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
