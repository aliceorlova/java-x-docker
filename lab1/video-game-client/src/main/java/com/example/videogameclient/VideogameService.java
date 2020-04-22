package com.example.videogameclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideogameService implements IService<VideogameEntity> {

    @Autowired
    public VideogameRepository _repository;

    @Override
    public VideogameEntity Add(VideogameEntity item) {
        return _repository.save(item);
    }

    @Override
    public void DeleteById(Integer id) {
       VideogameEntity existing = _repository.findById(id).orElse(null);
       if(existing == null) throw new EntityNotFoundException("No item with this id was found");
       existing.setDeleted(true);
       _repository.save(existing);
    }

    @Override
    public VideogameEntity Update(VideogameEntity item) {
        VideogameEntity b = _repository.findById(item.getId()).orElseThrow(() -> new EntityNotFoundException("No such id."));
        b.setName(item.getName());
        b.setPrice(item.getPrice());
        b.setCategory(item.getCategory());
        b.setDescription(item.getDescription());
        b.setDeveloper(item.getDeveloper());
        b.setInitialReleaseDate(item.getInitialReleaseDate());
        b.setPublisher(item.getPublisher());
        b.setWriter(item.getWriter());
        _repository.save(b);
        return b;
    }
    @Override
    public List<VideogameEntity> GetAll()  {
        List<VideogameEntity> list = new ArrayList<>();
        _repository.findAll().forEach(list::add);
        List<VideogameEntity> filtered = new ArrayList<>();
        for (VideogameEntity item: list) {
            if(!item.isDeleted()){
                filtered.add(item);
            }
        }
        return filtered;
    }
    @Override
    public VideogameEntity GetById(Integer id){
        VideogameEntity res =  _repository.findById(id).orElseThrow(()->new EntityNotFoundException("No item was found."));
        if(res == null | res.isDeleted()) return null;
        else return res;
    }
}
