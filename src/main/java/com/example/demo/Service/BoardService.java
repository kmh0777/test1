package com.example.demo.Service;

import com.example.demo.mappers.DetailMapper;
import com.example.demo.mappers.Test1Mapper;
import com.example.demo.model.Board;
import com.example.demo.model.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired
    private Test1Mapper test1Mapper;

    @Autowired
    private DetailMapper detailMapper;

    @Transactional
    public Board addBoardDetail(Board board) {
        test1Mapper.insertBoard(board);

        Detail detail = new Detail();
        
        detail.setBoard_id(board.getPriKey());
        detail.setContent(board.getContent());

//        if (true) {
//            throw new RuntimeException("Exception");
//        }

        detailMapper.insertDetail(detail);

        return board;
    }

    @Transactional
    public Board editBoardDetail(Board board) {
        test1Mapper.updateBoard(board);

        Detail detail = new Detail();

        detail.setBoard_id(board.getPriKey());
        detail.setContent(board.getContent());

//        if (true) {
//            throw new RuntimeException("Exception");
//        }

        detailMapper.updateDetail(detail);

        return board;
    }
}
