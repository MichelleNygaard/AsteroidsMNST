package dk.sdu.cbse.scoring;

import org.springframework.boot.SpringApplication;


@SpringBootApplication
@RestController
public class ScoringSys {
    private int playerScore = 0;

    public static void main(String[] args) {
    }

    @PutMapping("/addPoints")
    public void calcScore(@RequestParam(value = "points", defaultView = "0") int points) {
        playerScore += points;
    }

    @GetMapping("/getScore")
    public int getPlayerScore() {
        return this.playerScore;
    }
}