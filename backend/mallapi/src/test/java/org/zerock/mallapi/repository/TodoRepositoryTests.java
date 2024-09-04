package org.zerock.mallapi.repository;

import java.time.LocalDate; //날짜 다룰 때

import org.junit.jupiter.api.Test; //테스트 실행 시 자동으로 실행
import org.springframework.beans.factory.annotation.Autowired; //빈 자동 주입
import org.springframework.boot.test.context.SpringBootTest; //스프링 부트 테스트 수행
//테스트페이징 관련 import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.zerock.mallapi.domain.Todo; //Todo에 있는 엔티티 사용

import lombok.extern.log4j.Log4j2; //Log4j2 로거를 생성 코드 간단 로깅

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired //주입
    private TodoRepository todoRepository;
    //데이터 생성
    @Test
    public void testInsert() {

        for (int i = 1; i <= 100; i++) {

            Todo todo = Todo.builder()
            .title("Title..." + i)
            .dueDate(LocalDate.of(2023,12,31))
            .writer("user00")
            .build();

            todoRepository.save(todo);
        }
        
    }

    @Test
    public void testRead() {

        //존재하는 번호로 확인 (tno번호의 데이터 검색)
        Long tno = 33L;

        java.util.Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow(); //꺼내줘

        log.info(todo);
    }

    @Test
    public void testModify() {
        //33번의 데이터 행 내용 수정
        Long tno = 33L;

        java.util.Optional<Todo> result = todoRepository.findById(tno); //java.util 패키지의 Optional

        Todo todo = result.orElseThrow();
        todo.changeTitle("Modified 33...");
        todo.changeComplete(true);
        todo.changeDueDate(LocalDate.of(2023,10,10));

        todoRepository.save(todo);

    }

    @Test
    public void testDelete() {
        Long tno = 1L;

        todoRepository.deleteById(tno);

    }

    @Test
    public void testPaging() {
        //페이지 번호가 0부터 시작
        //import org.springframwork.data.domain.Pageable;

        Pageable pageable = PageRequest.of(0,10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result.getTotalElements());

        result.getContent().stream().forEach(todo -> log.info(todo));

    }

}
