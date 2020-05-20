package com.example.videogameclient;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import javassist.compiler.SyntaxError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideogameService implements IService<VideoGame> {

    @Autowired
    public VideogameRepository _repository;

    @Override
    public VideoGame Add(VideoGame item) throws CustomException {
        SanityChecks(item);
        return _repository.save(item);
    }

    @Override
    public void DeleteById(Integer id) throws CustomException {
        VideoGame existing = _repository.findById(id).orElseThrow(() -> new CustomException("No item was found by such id."));
        if (existing.isDeleted()) throw new CustomException("No item with such id was found. Cant really delete it.");
        existing.setDeleted(true);
        _repository.save(existing);
    }

    @Override
    public VideoGame Update(VideoGame item) throws CustomException {
        VideoGame b = _repository.findById(item.getId()).orElseThrow(() -> new CustomException("No item was found by such id."));
        if (b.isDeleted()) throw new CustomException("No item with such id was found.");
        SanityChecks(item);

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

    private void SanityChecks(VideoGame item) throws CustomException {
        if (item.getCategory() == null || item.getDescription() == null || item.getDeveloper() == null ||
                item.getInitialReleaseDate() == null || item.getName() == null || item.getPrice() == 0.0 ||
                item.getPublisher() == null || item.getWriter() == null)
            throw new CustomException("Umm check the types!");
    }

    @Override
    public ResponseModel<List<VideoGame>> GetAll(String id) {
        List<VideoGame> list = new ArrayList<>();
        _repository.findAll().forEach(list::add);

        List<VideoGame> filtered = new ArrayList<>();
        for (VideoGame item : list) {
            if (!item.isDeleted()) {
                filtered.add(item);
            }
        }
        return new ResponseModel<>(filtered, id);
    }

    @Override
    public ResponseModel<VideoGame> GetById(Integer id, String str) throws CustomException {
        VideoGame res = _repository.findById(id).orElseThrow(() -> new CustomException("No item was found by such id."));
        if (res == null | res.isDeleted()) throw new CustomException("The item was deleted.");
        return new ResponseModel<>(res, str);
    }
}