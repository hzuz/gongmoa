package com.gongmoa.springboot_board.controller;

import com.gongmoa.springboot_board.dto.ApplicationDTO;
import com.gongmoa.springboot_board.dto.BoardFileDTO;
import com.gongmoa.springboot_board.service.ApplicationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.gongmoa.springboot_board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.gongmoa.springboot_board.service.BoardService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final ApplicationService applicationService;

    @GetMapping("/post")
    public String post(){
        return "post";
    }

    @PostMapping("/post")
    public String post(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.post(boardDTO);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id:[0-9]+}")
    public String findById(@PathVariable("id") Long id, Model model,
                           @CookieValue(value = "memberId", required = false) String memberId) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);

        if(boardDTO.getFileAttached() == 1){
            BoardFileDTO boardFileDTO = boardService.findFile(id);
            model.addAttribute("boardFile", boardFileDTO);
        }

        boolean isApplied = false;
        if(memberId != null && !memberId.isEmpty()){
            isApplied = applicationService.isApplied(id, memberId);
            if (isApplied) {
                ApplicationDTO myApplication = applicationService.findByBoardIdAndMemberId(id, memberId);
                model.addAttribute("applicationDTO", myApplication);
            }
        }
        model.addAttribute("isApplied", isApplied);

        return "detail";
    }

    @GetMapping("/edit/{id:[0-9]+}")
    public String edit(@PathVariable("id") Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "edit";
    }

    @PostMapping("/edit/{id:[0-9]+}")
    public String edit(BoardDTO boardDTO) throws IOException{
        boardService.edit(boardDTO);
        return "redirect:/list";
    }
    @GetMapping("/delete/{id:[0-9]+}")
    public String delete(@PathVariable("id") Long id){
        boardService.delete(id);
        return "redirect:/list";
    }



}
