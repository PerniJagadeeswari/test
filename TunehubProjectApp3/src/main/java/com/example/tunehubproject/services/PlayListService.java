package com.example.tunehubproject.services;

import java.util.List;

import com.example.tunehubproject.entities.PlayList;

public interface PlayListService {
	public void addPlaylist(PlayList playlist);
	public List<PlayList>fetchPlaylists();

}
