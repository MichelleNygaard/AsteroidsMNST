package dk.sdu.cbse.scoringsysplug;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.*;

public class ScoringSysPlug implements IGamePluginService, IScoringSystem{
    private final String baseURL;
    private Text playerScoreText;
    private final HttpClient client;

    public ScoringSysPlug() {
        this.baseURL = "http://localhost:8080";
        this.client = HttpClient.newHttpClient();
    }

    @Override
    public void start(GameData gameData, World world) {
        this.playerScoreText = new Text(10, 20, "Player score: 0");
        playerScoreText.setFill(Color.WHITE);
        playerScoreText.setId("playerScoreText");
        playerScoreText.toFront();

        gameData.getGameWindow().getChildren().add(playerScoreText);
        playerScoreText.toFront();
    }

    @Override
    public void stop(GameData gameData, World world) {
    }

    @Override
    public void updateScore(GameData gameData) throws URISyntaxException, IOException, InterruptedException {
        for (Node node : gameData.getGameWindow().getChildren()) {
            String nodeId = node.getId();
            if ("playerScoreText".equals(nodeId)) {
                Text currentWeaponText = (Text) node;
                currentWeaponText.setText("Player Score: " + getPlayerScore());
            }
        }
    }

    public int getPlayerScore() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "/getScore"))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Integer.parseInt(response.body());
    }

    @Override
    public void addScore(int addPoints) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "/addPoints?points=" + addPoints))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .header("Content-Type", "application/json")
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
