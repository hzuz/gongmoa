package com.gongmoa.springboot_board.repository;

import com.gongmoa.springboot_board.dto.ApplicationDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ApplicationRepository {
    private final SqlSessionTemplate sql;

    public ApplicationDTO apply(ApplicationDTO applicationDTO) {
        sql.insert("Application.apply", applicationDTO);
        return applicationDTO;
    }
    public List<ApplicationDTO> findByBoardId(Long id) {
        return sql.selectList("Application.findByBoardId", id);
    }
    public ApplicationDTO findByBoardIdAndMemberId(Long boardId, String memberId) {
        Map<String, Object> params = new HashMap<>();
        params.put("boardId", boardId);
        params.put("memberId", memberId);
        return sql.selectOne("Application.findByBoardIdAndMemberId", params);
    }

    public boolean isApplied(Long boardId, String memberId){
        ApplicationDTO dto=new ApplicationDTO();
        dto.setBoardId(boardId);
        dto.setMemberId(memberId);
        int count=sql.selectOne("Application.isApplied",dto);
        return count>0;
    }

    public List<ApplicationDTO> findApplicants(String boardWriter){
        return sql.selectList("Application.findApplicants", boardWriter);

    }

    public void cancel(Long id) {
        sql.delete("Application.cancel", id);
    }
}
