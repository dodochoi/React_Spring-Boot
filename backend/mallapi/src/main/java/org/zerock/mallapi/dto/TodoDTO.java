package org.zerock.mallapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat; //Json 데이터 직렬화 및 역직렬화에서 날짜 시간 형식 지정
import lombok.AllArgsConstructor; //모든 필드 인자로 받아 초기화하는 생성자 자동생성
import lombok.Builder; // 빌더 쉽게 구현
import lombok.Data;
import lombok.NoArgsConstructor; //파라미터없이 객체 생성

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long tno;

    private String title;

    private String writer;

    private boolean complete;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
}
