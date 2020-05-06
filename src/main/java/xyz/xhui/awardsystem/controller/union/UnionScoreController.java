package xyz.xhui.awardsystem.controller.union;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.dto.ScoreDto;
import xyz.xhui.awardsystem.model.entity.UnionScore;
import xyz.xhui.awardsystem.service.UnionScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/union/score")
@Slf4j
@Api(tags = "学生会成绩管理接口")
@RolesAllowed({"UNION"})
public class UnionScoreController {
    @Autowired
    private UnionScoreService unionScoreService;

    @PostMapping("/add")
    @ApiOperation("添加")
    @ResponseBody
    public Result<String> save(ScoreDto scoreDto) {
        log.info(scoreDto.toString());
        try {
            unionScoreService.save(scoreDto);
        } catch (EntityFieldException | UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @PostMapping("/adds")
    @ApiOperation("批量添加")
    @ResponseBody
    public Result<String> saves(@RequestBody ScoreDto[] scoreDtos) {
        log.info(Arrays.toString(scoreDtos));
        try {
            unionScoreService.saves(scoreDtos);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @GetMapping(name = "")
    @ApiOperation("分页查询")
    @ResponseBody
    public Result<List<ScoreDto>> findAll(@RequestParam("page") Integer pagenum, @RequestParam("limit") Integer pagesize) {
        PageDto<List<ScoreDto>> pageDto = null;
        try {
            pageDto = unionScoreService.findAll(pagenum - 1, pagesize);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(pageDto.getCount(), pageDto.getObj());
    }

    @DeleteMapping("/delete")
    @ApiOperation("批量删除")
    @ResponseBody
    public Result<String> deletes(@RequestParam("ids[]") Integer[] ids) {
        try {
            unionScoreService.deletes(ids);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @GetMapping("/stu")
    @RolesAllowed({"STU"})
    @ResponseBody
    public Result<List<ScoreDto>> getStuScore(){
        List<ScoreDto> scores = null;
        try {
            scores = unionScoreService.findOneByStuId();
        } catch (EntityFieldException | UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(scores);
    }
}
