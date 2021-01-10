package com.example.mreivew.repository;

import com.example.mreivew.entity.Member;
import com.example.mreivew.entity.Movie;
import com.example.mreivew.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

	@Autowired
	private ReviewRepository reviewRepository;

	@Test
	public void insertMoviewReviews(){

		//200개 리뷰등록
		IntStream.rangeClosed(1, 200).forEach(i->{
			//영호번호
			Long mno =(long)(Math.random()*100)+1;
			//리뷰번호
			Long mid = ((long)(Math.random()*100)+1);
			Member member = Member.builder().mid(mid).build();

			Review movieReview = Review.builder()
					.member(member)
					.movie(Movie.builder().mno(mno).build())
					.grade((int)(Math.random()*5)+1)
					.text("이 영화에 대한 느낌..."+i)
					.build();

			reviewRepository.save(movieReview);
		});

	}
}
