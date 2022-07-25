package com.example.demo.controller;

import com.example.demo.mapper.SongMapper;
import com.example.demo.model.Song;
import com.example.demo.model.dto.SongDto;
import com.example.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class SongController {
    @Autowired
    public SongService songService;
    @Autowired
    public SongMapper songMapper;

    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public ResponseEntity<List<SongDto>> getAll() {
        List<Song> objects = songService.findAll();
        if (objects.isEmpty()) {
            return new ResponseEntity<List<SongDto>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<SongDto>>(songMapper.mapSongsToDtos(objects), HttpStatus.OK);
    }

    @RequestMapping(value = "/songs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SongDto> getById(@PathVariable("id") Long id) {
        Song object = songService.findById(id);
        if (object == null) {
            return new ResponseEntity<SongDto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SongDto>(songMapper.mapSongToDto(object), HttpStatus.OK);
    }

    @RequestMapping(value = "/songs", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Song object, UriComponentsBuilder ucBuilder) {
        songService.update(object);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/songs/{id}").buildAndExpand(object.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/songs/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Song> update(@PathVariable("id") Long id, @RequestBody Song object) {
        Song oldObject = songService.findById(id);
        if (oldObject == null) {
            return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
        }
        oldObject = object;
        songService.update(oldObject);
        return new ResponseEntity<Song>(oldObject, HttpStatus.OK);
    }

    @RequestMapping(value = "/songs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Song> delete(@PathVariable("id") Long id) {
        Song object = songService.findById(id);
        if (object == null) {
            return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
        }
        songService.delete(id);
        return new ResponseEntity<Song>(HttpStatus.NO_CONTENT);
    }
}
