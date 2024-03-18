package com.example.tunehubproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tunehubproject.entities.PlayList;
import com.example.tunehubproject.repository.PlayListRepository;

@Service
public class PlayListServiceImplementation implements PlayListService {
   @Autowired
   PlayListRepository prepo;

@Override
public void addPlaylist(PlayList playlist) {
	prepo.save(playlist);
	
}

@Override
public List<PlayList> fetchPlaylists() {
	
	return prepo.findAll();
}
}
