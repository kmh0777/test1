package com.example.demo.mappers;

import com.example.demo.model.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Test1Mapper {

    @Select("select * from tbl_board")
    @Result(property = "priKey", column = "pri_key")
    List<Board> selectBoard();

    @Insert("insert into tbl_board(title, writer) values(#{board.title}, #{board.writer})")
    @Options(useGeneratedKeys = true, keyProperty = "pri_key")
    int insertBoard(@Param("board") Board board);

    @Select("select * from tbl_board where pri_key=#{pri_key}")
    @Results({
            @Result(property = "priKey", column = "pri_key"),
            @Result(property = "content", column = "pri_key", many = @Many(select = "com.example.demo.mappers.DetailMapper.getDetailByBoardId"))
    })
    Board GetById(@Param("pri_key") Long pri_key);

    @Update("update tbl_board set title=#{board.title}, writer=#{board.writer} where pri_key=#{board.priKey}")
    int updateBoard(@Param("board") Board board);
}
