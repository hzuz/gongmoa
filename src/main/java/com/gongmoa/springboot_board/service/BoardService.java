package com.gongmoa.springboot_board.service;

import com.gongmoa.springboot_board.dto.BoardDTO;
import com.gongmoa.springboot_board.dto.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.gongmoa.springboot_board.repository.BoardRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardDTO> findAll() {
        return boardRepository.findAll();
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    public void edit(BoardDTO boardDTO) throws IOException{
        boardRepository.edit(boardDTO);

        MultipartFile boardFile=boardDTO.getBoardFile();

        if(boardFile!=null && !boardFile.isEmpty()){
            BoardFileDTO oldFile=boardRepository.findFile(boardDTO.getId());
            if(oldFile!=null){
                File file=new File("D:/board_tistory/springboot-board/springboot-board/src/main/resources/upload_files/"+oldFile.getStoredFileName());
                if(file.exists()){
                    file.delete();
                }
                boardRepository.deleteFile(boardDTO.getId());
            }

            String originalFilename = boardFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis()+"_"+originalFilename;

            BoardFileDTO boardFileDTO = new BoardFileDTO();
            boardFileDTO.setOriginalFileName(originalFilename);
            boardFileDTO.setStoredFileName(storedFileName);
            boardFileDTO.setBoardId(boardDTO.getId());

            String savePath = "D:/board_tistory/springboot-board/springboot-board/src/main/resources/upload_files/"+storedFileName;
            boardFile.transferTo(new File(savePath));
            boardRepository.saveFile(boardFileDTO);
        }
    }

    public void post(BoardDTO boardDTO) throws IOException {
        MultipartFile boardFile=boardDTO.getBoardFile();

        if(boardFile.isEmpty()){
            boardDTO.setFileAttached(0);
            boardRepository.post(boardDTO);
        }else{
            boardDTO.setFileAttached(1);
            BoardDTO savedBoard = boardRepository.post(boardDTO);

            String originalFilename = boardFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis()+"_"+originalFilename;

            BoardFileDTO boardFileDTO = new BoardFileDTO();
            boardFileDTO.setOriginalFileName(originalFilename);
            boardFileDTO.setStoredFileName(storedFileName);
            boardFileDTO.setBoardId(savedBoard.getId());

            String savePath = "D:/board_tistory/springboot-board/springboot-board/src/main/resources/upload_files/"+storedFileName;
            boardFile.transferTo(new File(savePath));
            boardRepository.saveFile(boardFileDTO);
        }
    }

    public BoardFileDTO findFile(Long id)
    {
        return boardRepository.findFile(id);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }
}
