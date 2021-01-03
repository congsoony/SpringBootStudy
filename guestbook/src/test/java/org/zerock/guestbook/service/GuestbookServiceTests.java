package org.zerock.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.repository.GuestbookRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@SpringBootTest
public class GuestbookServiceTests {
	@Autowired
	private GuestbookService service;
	@Autowired
	private GuestbookRepository repository;

	@Test
	public void testRegister(){
		GuestbookDTO guestbookDTO = GuestbookDTO.builder()
				.title("Sample Title.....")
				.content("Sample Content.....")
				.writer("user0")
				.build();
		System.out.println(service.register(guestbookDTO));
	}

	@Test
	public void testList(){
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

		System.out.println("PREV: "+resultDTO.isPrev());
		System.out.println("NEXT: "+resultDTO.isNext());
		System.out.println("TOTAL: "+resultDTO.getTotalPage());

		System.out.println("-------------------------------------------");
		for ( GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
			System.out.println(guestbookDTO);
		}

		System.out.println("===================================");
		resultDTO.getPageList().forEach(i-> System.out.println(i));
	}

	@Test
	public void modify(){ //변경되는지 테스트
		Optional<Guestbook> result = repository.findById(300L);
		Guestbook guestbook = result.get();
		guestbook.changeContent("바꿨다ㅋㅋㅋ");
		guestbook.changeTitle("빠꿨다ㅋㅋㅋ");
		service.modify(service.entityToDto(guestbook));

	}
}
