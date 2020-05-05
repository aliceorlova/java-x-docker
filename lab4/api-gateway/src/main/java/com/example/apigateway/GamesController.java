package com.example.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping(path = "/games")
public class GamesController {

    @Autowired
    ProxyService _client;

    @Autowired
    ConfigClientAppConfiguration config;

    @RequestMapping(path = "/configs", method = RequestMethod.GET)
    public ResponseEntity<?> GetConfig() {
        HashMap<String, String> map = new HashMap<>();
        map.put("video-game-client property1", config.getProperty1());
        map.put("video-game-client property2", config.getProperty2());
        return ResponseEntity.ok(map);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<?> GetAll() {
        try {
            return _client.GetAll();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> GetById(@PathVariable Integer id) {
        try {
            return _client.GetById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("The item does not exist.", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<?> Add(@RequestBody VideoGame game) throws CustomException {
        try {
            return _client.Add(game);
        } catch (CustomException e) {
            return new ResponseEntity<>("Check the types!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> DeleteById(@PathVariable int id) {
        try {
            return _client.DeleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("The item does not exist.", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> Update(@RequestBody VideoGame game, @PathVariable int id) throws CustomException {
        try {
            return _client.Update(game, id);
        } catch (CustomException e) {
            return new ResponseEntity<>("Whoa dude check the types", HttpStatus.BAD_REQUEST);
        }
    }

}
