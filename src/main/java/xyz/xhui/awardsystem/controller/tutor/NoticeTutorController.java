package xyz.xhui.awardsystem.controller.tutor;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.NoticeTutor;
import xyz.xhui.awardsystem.service.NoticeTutorService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/noticeTutor")
@Slf4j
@RolesAllowed("TUTOR")
@Api("辅导员公告管理")
public class NoticeTutorController {
    @Autowired
    private NoticeTutorService noticeTutorService;

    @GetMapping("")
    @ResponseBody
    public Result<List<NoticeTutor>> findAll(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize) {
        PageDto<List<NoticeTutor>> pageDto = null;
        try {
            pageDto = noticeTutorService.findAll(pageNum - 1, pageSize);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(pageDto.getCount(), pageDto.getObj());
    }

    @GetMapping("/edit")
    public String findById(@RequestParam("id") Integer id, Model model) {
        NoticeTutor noticeTutor = null;
        try {
            noticeTutor = noticeTutorService.findById(id);
        } catch (UnknownException e) {
            model.addAttribute("exception", e.getMessage());
            return "exception";
        }
        log.info(noticeTutor.toString());
        model.addAttribute("notice", noticeTutor);
        return "tutor/notice-edit";
    }

    @PutMapping("")
    @ResponseBody
    public Result<String> update(NoticeTutor noticeTutor) {
        try {
            noticeTutorService.update(noticeTutor);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @PostMapping("")
    @ResponseBody
    public Result<String> save(NoticeTutor noticeTutor) {
        try {
            noticeTutorService.save(noticeTutor);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @DeleteMapping("")
    @ResponseBody
    public Result<String> delete(@RequestParam("id") Integer id) {
        Integer integer = noticeTutorService.deleteById(id);
        if (integer > 0) {
            return ResultFactory.buildSuccessResult();
        } else {
            return ResultFactory.buildFailResult("id不存在");
        }
    }
}
