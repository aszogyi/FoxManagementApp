package hu.szabolcs.fox.scheduler;

import hu.szabolcs.fox.domain.Fox;
import hu.szabolcs.fox.service.FoxService;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Singleton
@Startup
public class FoxImageScheduler {

    private static final String RANDOM_FOX_API_URL = "https://randomfox.ca/floof/";

    @EJB
    private FoxService foxService;

    @Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
    public void updateFoxesWithoutImage() {
        Fox fox = foxService.findOneWithoutImage();

        if (fox == null) {
            return;
        }

        String imageUrl = fetchRandomFoxImageUrl();

        if (imageUrl != null) {
            fox.setImageUrl(imageUrl);
            foxService.update(fox);
        }
    }

    private String fetchRandomFoxImageUrl() {
        try {
            URL url = new URL(RANDOM_FOX_API_URL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            try (InputStream inputStream = connection.getInputStream();
                 JsonReader jsonReader = Json.createReader(inputStream)) {

                JsonObject jsonObject = jsonReader.readObject();
                return jsonObject.getString("image", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
