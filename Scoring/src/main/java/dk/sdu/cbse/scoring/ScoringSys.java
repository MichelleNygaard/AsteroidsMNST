package dk.sdu.cbse.scoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringSys {
    private int playerScore = 0;

    public static void main(String[] args) {
    }

    @PutMapping("/addPoints")
    public void calcScore(@RequestParam(value = "points", defaultValue = "0") int points) {
        playerScore += points;
    }

    @GetMapping("/getScore")
    public int getPlayerScore() {
        return this.playerScore;
    }
}