package com.gongmoa.springboot_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApplicationDTO {
    private Long id;
    private String memberId;
    private Long boardId;
    private String boardTitle;
    private String applicantName;
    private String univName;
    private String department;
    private String univEmail;
    private String info;
}
