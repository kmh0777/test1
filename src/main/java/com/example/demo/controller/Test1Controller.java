package com.example.demo.controller;

import com.example.demo.Service.BoardService;
import com.example.demo.mappers.DetailMapper;
import com.example.demo.mappers.Test1Mapper;
import com.example.demo.model.Board;
import com.example.demo.model.Detail;
import javax.validation.Valid;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class Test1Controller {

    @Autowired
    private Test1Mapper mapper;

    @Autowired
    private DetailMapper detailMapper;

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String select(Model model) {
        List<Board> boards = mapper.selectBoard();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/{id}")
    public Board selectId(@PathVariable("id") Long id) {
        return mapper.GetById(id);
    }

    @PostMapping("")
    public Board post(@RequestBody Board board) {
        //mapper.insertBoard(board);
        boardService.addBoardDetail(board);

        return board;
    }

    @GetMapping("/detail/{id}")
    public String selectBoardId(@PathVariable("id") Long id) {
        return detailMapper.getDetailByBoardId(id);
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long pri_key) {
        if (pri_key == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = mapper.GetById(pri_key);
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String boardSubmit(@Valid Board board, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        if (board.getPriKey() == null) {
            boardService.addBoardDetail(board);
        } else {
            boardService.editBoardDetail(board);
        }

        return "redirect:/board/list";
    }

    @GetMapping("/delete")
    public String deleteId(@RequestParam("pri_key") Long pri_key) {
        boardService.deleteBoard(pri_key);

        return "redirect:/board/list";
    }

}
