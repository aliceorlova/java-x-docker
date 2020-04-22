package com.example.videogameclient;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import javassist.compiler.SyntaxError;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IService<T> {
    T Add(T item) throws CustomException;

    void DeleteById(Integer id) throws CustomException;

    T Update(T item) throws CustomException;

    ResponseModel<List<VideoGame>> GetAll(String id) throws CustomException;

    ResponseModel<VideoGame> GetById(Integer id, String str) throws CustomException;
}
