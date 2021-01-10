package com.example.mreivew.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie")
public class MovieImage extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inum;

	private String uuid;
	private String imgName;

	private String pate;

	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;

}
