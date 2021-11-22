package com.sreejith.mongodbspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class MyController {

    @Autowired
    private PlayerRepository playerRepository;

    //Get details about player when playerID is given
    @GetMapping("/id/{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable("playerId") String playerId)
    {
        Optional<Player> player = playerRepository.findById(playerId);
        Player playerRet = null;
        if (player.isPresent())
        {
            playerRet = player.get();
        }

        return ResponseEntity.status(HttpStatus.OK).body(playerRet);
    }

    //get all details about all players who belongs to a certain country
    @GetMapping("/country/{countryName}")
    public ResponseEntity<List<Player>> getPlayersInaCountry(@PathVariable("countryName") String country)
    {
        List<Player> players = playerRepository.getPlayersFromACountry(country);
        return ResponseEntity.status(HttpStatus.OK).body(players);
    }

    //get all details about all players who belongs to a certain age range
    //send the query as http://localhost:8080/player/age?min=20&max=35
    @GetMapping("/age")
    public ResponseEntity<?> getPlayersBetweenAge(@RequestParam(name = "min") int min, @RequestParam(name = "max") int max) {
        List<Player> players = playerRepository.getPlayersBetweenAge(min,max);
        return ResponseEntity.status(HttpStatus.OK).body(players);
    }

    //get all details about all players who belongs to a range of date of birth
    //send the query as http://localhost:8080/player/dob?min=1993-01-01T12:00:00&max=1999-01-31T12:00:00
    @GetMapping("/dob")
    public ResponseEntity<?> getPlayersBetweenAge(@RequestParam(name = "min") String min, @RequestParam(name = "max") String max) {

        List<Player> players = playerRepository.getPlayersBetweenDate( LocalDateTime.parse(min), LocalDateTime.parse(max));
        return ResponseEntity.status(HttpStatus.OK).body(players);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addPlayer(@RequestBody Player player)
    {
        playerRepository.insert(player);
        return ResponseEntity.status(HttpStatus.OK).body("Player added succesfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removePlayer(@PathVariable("id") String id)
    {
        playerRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Player removed succesfully");
    }

    //delete all players who belongs to a range of date of birth
    //send the query as http://localhost:8080/player/delete?min=1993-01-01T12:00:00&max=1999-01-31T12:00:00
    @DeleteMapping("/delete")
    public ResponseEntity<String> removePlayerBetweenAge(@RequestParam(name = "min") String min, @RequestParam(name = "max") String max)
    {
        List<Player> players = playerRepository.getPlayersBetweenDate( LocalDateTime.parse(min), LocalDateTime.parse(max));
        players.forEach(p -> playerRepository.deleteById(p.getPlayerId()));
        return ResponseEntity.status(HttpStatus.OK).body("Player removed succesfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getPlayers()
    {
        List<Player> players = playerRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(players);
    }
}
