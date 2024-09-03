package org.zerock.mallapi.service;

import org.modelmapper.ModelMapper; //객체간 매핑 자동 수행
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.TodoDTO;
import org.zerock.mallapi.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor // 생성자 자동 주입
public class TodoServiceImple implements TodoService {//TodoService참조
    
    //자동주입 대상은 final로
    private final ModelMapper modelMapper;

    private final TodoRepository todoRepository;

    @Override
    public Long register(TodoDTO todoDTO) {

        log.info("..........");

        Todo todo = modelMapper.map(todoDTO, Todo.class);

        Todo saveTodo = todoRepository.save(todo);

        return saveTodo.getTno();
        
    }
}
