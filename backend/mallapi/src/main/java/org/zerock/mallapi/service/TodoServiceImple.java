package org.zerock.mallapi.service;

import org.glassfish.jaxb.core.annotation.OverrideAnnotationOf;
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

    //등록 기능
    @Override
    public Long register(TodoDTO todoDTO) {

        log.info("..........");

        Todo todo = modelMapper.map(todoDTO, Todo.class);

        Todo saveTodo = todoRepository.save(todo);

        return saveTodo.getTno();
        
    }

    //조회 기능
    @Override
    public  TodoDTO get(Long tno) {

        java.util.Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        TodoDTO dto = modelMapper.map(todo, TodoDTO.class);

        return dto;
    }

    //수정 기능
    @Override
    public void modify(TodoDTO todoDTO) {

        java.util.Optional<Todo>result = todoRepository.findById(todoDTO.getTno());

        Todo todo = result.orElseThrow();

        todo.changeTitle(todoDTO.getTitle());
        todo.changeDueDate(todoDTO.getDueDate());
        todo.changeComplete(todoDTO.isComplete());

        todoRepository.save(todo);

    }

    //삭제 기능
    @Override
    public void remove(Long tno) {

        todoRepository.deleteById(tno);
        
    }
}
