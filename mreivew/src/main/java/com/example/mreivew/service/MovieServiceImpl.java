package com.example.mreivew.service;

import com.example.mreivew.dto.MovieDTO;
import com.example.mreivew.entity.Movie;
import com.example.mreivew.entity.MovieImage;
import com.example.mreivew.repository.MovieImageRepository;
import com.example.mreivew.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
	private final MovieRepository movieRepository;

	private final MovieImageRepository imageRepository;

	@Transactional
	@Override
	public Long register(MovieDTO movieDTO) {
		Map<String,Object> entityMap = dtoToEntity(movieDTO);
		Movie movie =(Movie) entityMap.get("movie");
		List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

		movieRepository.save(movie);

		movieImageList.forEach(movieImage -> {
			imageRepository.save(movieImage);
		});

		return movie.getMno();
	}


}
