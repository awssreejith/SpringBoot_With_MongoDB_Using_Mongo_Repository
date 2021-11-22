package com.sreejith.mongodbspringboot;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PlayerRepository extends MongoRepository<Player, String> {

    //0) get all players who belong a certain country
    @Query("{country :?0}")
    List<Player> getPlayersFromACountry(String country);

    //1) get all details about all players who belongs to a certain age range
    @Query("{'age' : { $gte: ?0, $lte: ?1 } }")
    List<Player> getPlayersBetweenAge(int min, int max);

    //1) get all details about all players who were born between certain data range
    @Query("{'dob' : { $gte: ?0, $lte: ?1 } }")
    List<Player> getPlayersBetweenDate(LocalDateTime min, LocalDateTime max);


}
