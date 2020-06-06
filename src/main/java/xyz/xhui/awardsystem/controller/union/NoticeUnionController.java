package xyz.xhui.awardsystem.controller.union;

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
import xyz.xhui.awardsystem.model.entity.NoticeUnion;
import xyz.xhui.awardsystem.service.NoticeUnionService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/noticeUnion")
@Slf4j
@RolesAllowed("UNION")
@Api("学生会公告管理")
public class NoticeUnionController {
    @Autowired
    private NoticeUnionService noticeUnionService;

    @GetMapping("")
    @ResponseBody
    public Result<List<NoticeUnion>> findAll(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize) {
        PageDto<List<NoticeUnion>> unionList = null;
        try {
            unionList = noticeUnionService.findAll(pageNum - 1, pageSize);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(unionList.getCount(), unionList.getObj());
    }

    @GetMapping("/edit")
    public String findById(@RequestParam("id") Integer id, Model model) {
        NoticeUnion noticeUnion = null;
        try {
            noticeUnion = noticeUnionService.findById(id);
        } catch (UnknownException e) {
            model.addAttribute("exception", e.getMessage());
            return "exception";
        }
        log.info(noticeUnion.toString());
        model.addAttribute("notice", noticeUnion);
        return "union/notice-edit";
    }

    @PutMapping("")
    @ResponseBody
    public Result<String> update(NoticeUnion noticeUnion) {
        try {
            noticeUnionService.update(noticeUnion);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @PostMapping("")
    @ResponseBody
    public Result<String> save(NoticeUnion noticeUnion) {
        try {
            noticeUnionService.save(noticeUnion);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @DeleteMapping("")
    @ResponseBody
    public Result<String> delete(@RequestParam("id") Integer id) {
        Integer integer = noticeUnionService.deleteById(id);
        if (integer > 0) {
            return ResultFactory.buildSuccessResult();
        } else {
            return ResultFactory.buildFailResult("id不存在");
        }
    }
}
