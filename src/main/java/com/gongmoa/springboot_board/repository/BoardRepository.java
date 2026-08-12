package com.gongmoa.springboot_board.repository;

import com.gongmoa.springboot_board.dto.BoardDTO;
import com.gongmoa.springboot_board.dto.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql;

    public BoardDTO post(BoardDTO boardDTO) {
        sql.insert("Board.post", boardDTO);
        return boardDTO;
    }

    public List<BoardDTO> findAll() {
        System.out.println("findAll");
        return sql.selectList("Board.findAll");
    }

    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    public void edit(BoardDTO boardDTO) {
        sql.update("Board.edit", boardDTO);
    }

    public void saveFile(BoardFileDTO boardFileDTO) {
        sql.insert("Board.saveFile", boardFileDTO);
    }

    public BoardFileDTO findFile(Long id) {
        return sql.selectOne("Board.findFile", id);
    }

    public void deleteFile(Long id){
        sql.delete("Board.deleteFile", id);
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }
}