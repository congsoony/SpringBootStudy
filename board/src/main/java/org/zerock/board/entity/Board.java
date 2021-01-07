package org.zerock.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")//writer 변수의 toString을 제외하겠다라는뜻
public class Board extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;

	private String title;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY) //FK설정
	private Member writer;//연관관계 지정

	public void changeTitle(String title){
		this.title = title;
	}

	public void changeContent(String content){
		this.content = content;
	}
}
