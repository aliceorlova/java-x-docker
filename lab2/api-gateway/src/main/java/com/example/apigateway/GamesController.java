package com.example.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/games")
public class GamesController {

    @Autowired
    VideoGameClient _client;

    @RequestMapping(path = "", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseModel<List<VideoGame>> GetAll() {
        return _client.GetAll();
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
    public ResponseEntity<?> Add(@RequestBody VideoGame game) {
        try {
            return _client.Add(game);
        } catch (Exception e) {
            return new ResponseEntity<>("Check the types.", HttpStatus.BAD_REQUEST);
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
    ResponseEntity<?> Update(@RequestBody VideoGame game, @PathVariable int id) {
        try {
            return _client.Update(game, id);
        } catch (Exception e) {
            return new ResponseEntity<>("Whoa dude check the types", HttpStatus.BAD_REQUEST);
        }
    }

}
