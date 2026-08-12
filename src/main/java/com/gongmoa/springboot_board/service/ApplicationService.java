package com.gongmoa.springboot_board.service;

import com.gongmoa.springboot_board.dto.ApplicationDTO;
import com.gongmoa.springboot_board.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationDTO apply(ApplicationDTO applicationDTO){
        return applicationRepository.apply(applicationDTO);
    }

    public List<ApplicationDTO> findByBoardId(Long id) {
        return applicationRepository.findByBoardId(id);
    }
    public ApplicationDTO findByBoardIdAndMemberId(Long boardId, String memberId) {
        return applicationRepository.findByBoardIdAndMemberId(boardId, memberId);
    }

    public boolean isApplied(Long boardId, String memberId){
        return  applicationRepository.isApplied(boardId,memberId);
    }

    public List<ApplicationDTO> findApplicants(String boardWriter){return applicationRepository.findApplicants(boardWriter);}

    public void cancel(Long id) {
        applicationRepository.cancel(id);
    }

}
