package com.example.demo.mappers;

import com.example.demo.model.Detail;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DetailMapper {

    @Select("select content from tbl_detail where board_id=#{board_id}")
    String getDetailByBoardId(@Param("board_id") Long id);

    @Insert("insert into tbl_detail(board_id, content) values(#{detail.board_id}, #{detail.content})")
    @Options(useGeneratedKeys = true, keyProperty = "priKey")
    int insertDetail(@Param("detail") Detail detail);

    @Update("update tbl_detail set content=#{detail.content} where board_id=#{detail.board_id}")
    int updateDetail(@Param("detail") Detail detail);

    @Delete("delete from tbl_detail where board_id=#{board_id}")
    int deleteDetail(@Param("board_id") Long id);
}
