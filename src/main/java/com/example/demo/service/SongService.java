package com.example.demo.service;

import com.example.demo.model.Song;
import com.example.demo.model.User;
import com.example.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    public Song findById(Long id) {
        return songRepository.findById(id).get();
    }

    public void create(Song object) {
        songRepository.save(object);
    }

    public void update(Song object) {
        songRepository.save(object);
    }

    public void delete(Long id) {
        songRepository.deleteById(id);
    }
}
