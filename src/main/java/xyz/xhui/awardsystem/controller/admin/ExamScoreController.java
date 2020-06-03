package xyz.xhui.awardsystem.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.dto.ExamScoreDto;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.ExamScore;
import xyz.xhui.awardsystem.model.vo.ExamScoreVo;
import xyz.xhui.awardsystem.service.ExamScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/examScore")
@Slf4j
@RolesAllowed("ADMIN")
@Api("期末成绩管理接口")
public class ExamScoreController {
    @Autowired
    private ExamScoreService examScoreService;

    @GetMapping("/add")
    @ApiOperation("添加")
    public String add(Integer termId, Model model) {
//        log.info(termId.toString());
        model.addAttribute("termId", termId);
        return "admin/exam/score-add";
    }

    @PostMapping("/adds")
    @ResponseBody
    @ApiOperation("批量添加")
    public Result<String> adds(@RequestBody ExamScoreVo examScoreVo) {
        System.out.println(examScoreVo);
        try {
            examScoreService.saves(examScoreVo);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @GetMapping("/all")
    @ResponseBody
    @ApiOperation("分页查询")
    public Result<List<ExamScoreDto>> findAll(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize, @RequestParam("termId") Integer termId) {
        PageDto<List<ExamScoreDto>> all = null;
        try {
            all = examScoreService.findAll(pageNum - 1, pageSize, termId);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(all.getCount(), all.getObj());
    }

    @DeleteMapping("/del")
    @ResponseBody
    @ApiOperation("批量删除")
    public Result<String> deletes(@RequestParam("ids[]") Integer[] ids, @RequestParam("termId") Integer termId) {
        try {
            examScoreService.deletes(ids, termId);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @RolesAllowed("TUTOR")
    @GetMapping("/tutor")
    @ResponseBody
    public Result<List<ExamScoreDto>> getByTutor(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize, @RequestParam("termId") Integer termId) {
        PageDto<List<ExamScoreDto>> all = null;
        try {
            all = examScoreService.findAllByTutor(pageNum - 1, pageSize, termId);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(all.getCount(), all.getObj());
    }

    @RolesAllowed("STU")
    @GetMapping("/stu")
    @ResponseBody
    public Result<List<ExamScoreDto>> getByStu(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize, @RequestParam("termId") Integer termId) {
        PageDto<List<ExamScoreDto>> all = null;
        try {
            all = examScoreService.findAllByStu(pageNum - 1, pageSize, termId);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(all.getCount(), all.getObj());
    }
}
