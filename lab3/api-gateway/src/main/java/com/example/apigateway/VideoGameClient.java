package com.example.apigateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FeignClient(value = "video-game-client")
public interface VideoGameClient {

    @RequestMapping(path = "/games/configs", method = RequestMethod.GET)
    ResponseEntity<?> GetConfig();

    @RequestMapping(path="/games", method = RequestMethod.GET)
    @CrossOrigin
    ResponseModel<List<VideoGame>> GetAll();

    @RequestMapping(path="/games/{id}", method = RequestMethod.GET)
    ResponseEntity<?> GetById(@PathVariable Integer id);

    @RequestMapping(path="/games",method = RequestMethod.POST)
    ResponseEntity<?> Add(@RequestBody VideoGame game);

    @RequestMapping(path="/games/{id}",method = RequestMethod.DELETE)
    ResponseEntity<?> DeleteById(@PathVariable int id);

    @RequestMapping(path="/games/{id}",method = RequestMethod.PUT)
    ResponseEntity<?> Update(@RequestBody VideoGame game, @PathVariable int id);
}
