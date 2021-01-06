package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.BoardRepository;
import org.zerock.board.repository.ReplyRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{
	private final BoardRepository repository; //자동주입 fianl
	private final ReplyRepository replyRepository;

	@Override
	public Long register(BoardDTO dto) {
		log.info(dto);

		Board board = dtoToEntity(dto);

		repository.save(board);

		return board.getBno();
	}

	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		log.info(pageRequestDTO);
		Function<Object[], BoardDTO> fn =(en ->entityToDTO((Board)en[0],(Member)en[1],(Long)en[2]));
		Page<Object[]> result = repository.getBoardWithReplyCount(
				pageRequestDTO.getPageable(Sort.by("bno").descending())
		);
		return new PageResultDTO<>(result, fn);
	}

	@Override
	public BoardDTO get(Long bno) {
		Object result = repository.getBoardByBno(bno);

		Object[] arr = (Object[])result;

		return entityToDTO((Board)arr[0],(Member)arr[1],(Long)arr[2]);
	}

	@Transactional
	@Override
	public void removeWithReplies(Long bno) {
		//게시판을 지우기위해서 댓글이 있으면 댓글먼저 지우고 그다음 게시판을 지우기
		//상황에 따라 달리할수있지만 댓글있다면 댓글을 유지하기위해 게시판을 삭제 못하게 할수도 있음
		// 결국엔 어떻게 처리하는건 개발자 마음임

		replyRepository.deleteByBno(bno);
		repository.deleteById(bno);
	}

}
