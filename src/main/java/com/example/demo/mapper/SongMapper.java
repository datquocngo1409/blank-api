package com.example.demo.mapper;

import com.example.demo.model.Song;
import com.example.demo.model.dto.SongDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongMapper {
    ModelMapper mapper = new ModelMapper();

    public SongDto mapSongToDto(Song song) {
        return mapper.map(song, SongDto.class);
    }

    public List<SongDto> mapSongsToDtos(List<Song> songs) {
        List<SongDto> result = new ArrayList<>();
        for (Song song : songs) {
            SongDto songDto = mapper.map(song, SongDto.class);
            result.add(songDto);
        }
        return result;
    }
}
