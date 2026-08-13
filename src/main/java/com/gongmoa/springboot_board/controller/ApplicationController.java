package com.gongmoa.springboot_board.controller;

import com.gongmoa.springboot_board.dto.ApplicationDTO;
import org.springframework.ui.Model;
import com.gongmoa.springboot_board.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/apply/{boardId:[0-9]+}")
    public String apply(@PathVariable Long boardId, @ModelAttribute ApplicationDTO applicationDTO) {
        applicationDTO.setBoardId(boardId);
        applicationService.apply(applicationDTO);
        return "redirect:/" + boardId;
    }

    @GetMapping("/mypage")
    public String mypage(){
        return "mypage";
    }

    @GetMapping("/mypage-iflog")
    public String findApplicants(Model model, @CookieValue(value = "memberId", required = false) String memberId){
        List<ApplicationDTO> applicationDTOList = applicationService.findApplicants(memberId);
        model.addAttribute("applicationList", applicationDTOList);
        return "mypage-iflog";
    }

    @PostMapping("/cancel/{id:[0-9]+}")
    public String cancel(@PathVariable("id") Long id){
        applicationService.cancel(id);
        return "redirect:/list";
    }

}
