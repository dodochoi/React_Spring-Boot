package org.zerock.mallapi.service;

import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;

public interface TodoService {

    Long register(TodoDTO todoDTO); //등록기능(TDO 번호)
    
    TodoDTO get(Long tno); //조회기능

    void modify(TodoDTO todoDTO); //수정/삭제 기능

    void remove(Long tno);

    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
