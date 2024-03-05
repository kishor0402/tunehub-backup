package com.kodnest.tunehub.serviceimpl;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.SongRepository;
import com.kodnest.tunehub.service.SongService;
@Service
public class SongServiceImpl implements SongService {
	@Autowired
	SongRepository songrepository;
	@Override
	public String addSong(Song song) {
		songrepository.save(song);
		return "Song added sucessfully";
	}
	@Override
	public boolean songExists(String name) {
		Song song = songrepository.findByname(name);
		if(song == null) {
			return false;
		}else {
			return true;

		}
	}
	public List<Song> fetchAllSong()
	{
		List<Song>song=songrepository.findAll();
		return song;
	}
	





@Override
public void updateSong(Song s) {
	songrepository.save(s);
	// TODO Auto-generated method stub
	
}}



