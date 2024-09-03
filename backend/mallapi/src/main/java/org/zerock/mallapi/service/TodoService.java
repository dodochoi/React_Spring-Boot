package org.zerock.mallapi.service;

import org.zerock.mallapi.dto.TodoDTO;

public interface TodoService {

    Long register(TodoDTO todoDTO); //등록기능(TDO 번호)
    
}
