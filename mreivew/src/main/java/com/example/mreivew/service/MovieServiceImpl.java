package com.example.mreivew.service;

import com.example.mreivew.dto.MovieDTO;
import com.example.mreivew.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
	private final MovieRepository movieRepository;

	@Override
	public Long register(MovieDTO movieDTO) {
		return null;
	}
}
