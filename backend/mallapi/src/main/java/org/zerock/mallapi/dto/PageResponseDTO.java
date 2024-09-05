package org.zerock.mallapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<E> {

    private List<E> dtoList; //데이터 목록(DTO객체들)

    private List<Integer> pageNumList; //현재 페이지 페이지 번호

    private PageRequestDTO pageRequestDTO; //요청된 정보

    private boolean prev, next; //이전페이지, 다음페이지 이동여부

    private int totalCount, prevPage, nextPage, totalPage, current;

    //페이지네이션 설정
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount) {

        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int)totalCount;

        int end = (int)(Math.ceil( pageRequestDTO.getPage() / 10.0 )) * 10; //페이지 끝 계산

        int start = end - 9;

        int last = (int)(Math.ceil((totalCount/(double)pageRequestDTO.getSize())));

        end = end > last ? last: end; //end 값이 last 값보다 크면, end 값을 last로 설정합니다

        this.prev = start > 1;

        this.next = totalCount > end * pageRequestDTO.getSize();

        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        if(prev) {
            this.prevPage = start - 1;
        }

        if(next) {
            this.nextPage = end + 1;
        }

        this.totalPage = this.pageNumList.size();

        this.current = pageRequestDTO.getPage();

    }
    
}
