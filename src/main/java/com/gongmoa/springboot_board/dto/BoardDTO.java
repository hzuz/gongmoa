package com.gongmoa.springboot_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@ToString

public class BoardDTO {
    private Long id; //게시물번호
    private String boardWriter; //게시자아이디
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private LocalDate deadline;
    private Integer dday;
    private int fileAttached;
    private MultipartFile boardFile;
    private String univName;
    private String department;
    private String info;
}
