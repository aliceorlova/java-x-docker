package com.example.videogameclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping(path = "/games")
public class VideogameController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Autowired
    VideogameService _service;

    @RequestMapping(path = "", method = RequestMethod.GET)
    ResponseModel<List<VideoGame>> GetAll() {
        try {
            return _service.GetAll(instanceId);
        } catch (Exception e) {
            return new ResponseModel<>(new ArrayList<>(), instanceId);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    ResponseEntity<?> GetById(@PathVariable Integer id) {
        try {
            ResponseModel<VideoGame> res = _service.GetById(id, instanceId);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<String>(e.mes, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    ResponseEntity<?> Add(@RequestBody VideoGame game) {
        try {
            return new ResponseEntity<>(_service.Add(game), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.mes, HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<>("Whoa check the types please",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> DeleteById(@PathVariable int id) {
        try {
            _service.DeleteById(id);
            return new ResponseEntity<>("Item was deleted", HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.mes, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> Update(@RequestBody VideoGame game, @PathVariable int id) {
        try {
            game.setId(id);
            VideoGame res = _service.Update(game);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.mes, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Whoa dude check the types", HttpStatus.BAD_REQUEST);
        }
    }

}
