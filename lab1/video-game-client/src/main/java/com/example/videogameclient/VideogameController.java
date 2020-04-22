package com.example.videogameclient;
import org.aspectj.lang.annotation.DeclareWarning;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;

@RestController
@RequestMapping(path = "/games")
public class VideogameController {
    @Autowired
    VideogameService _service;

    @RequestMapping(path="/", method = RequestMethod.GET)
    Iterable<VideogameEntity> getAll() {
        return _service.GetAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    VideogameEntity GetById(@PathVariable Integer id){
        return _service.GetById(id);
    }

   @RequestMapping(path="/",method = RequestMethod.POST)
    VideogameEntity Create(@RequestBody VideogameEntity game){
        return _service.Add(game);
    }

    @RequestMapping(path="/{id}",method = RequestMethod.DELETE)
    void DeleteById(@PathVariable int id){
        _service.DeleteById(id);
    }

    @RequestMapping(path="/{id}",method = RequestMethod.PUT)
    VideogameEntity Update(@RequestBody VideogameEntity game, @PathVariable int id){
        game.setId(id);
        return _service.Update(game);
    }

}
