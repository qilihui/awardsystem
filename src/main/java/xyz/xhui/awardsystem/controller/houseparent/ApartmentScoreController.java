package xyz.xhui.awardsystem.controller.houseparent;

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
import xyz.xhui.awardsystem.model.dto.ApartmentScoreDto;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.dto.ScoreDto;
import xyz.xhui.awardsystem.model.entity.ApartmentScore;
import xyz.xhui.awardsystem.service.ApartmentScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
@Api("宿舍成绩控制器")
@RequestMapping("/apartment/score")
@RolesAllowed("HOUSEPARENT")
public class ApartmentScoreController {
    @Autowired
    private ApartmentScoreService apartmentScoreService;

    @GetMapping("")
    @ResponseBody
    @ApiOperation("分页查询")
    public Result<List<ApartmentScore>> findAllByPage(@RequestParam("page") Integer pagenum, @RequestParam("limit") Integer pagesize) {
        PageDto<List<ApartmentScore>> pageDto = null;
        try {
            pageDto = apartmentScoreService.findAll(pagenum - 1, pagesize);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(pageDto.getCount(), pageDto.getObj());
    }

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation("添加")
    public Result<String> save(ApartmentScore apartmentScore) {
        log.info(apartmentScore.toString());
        try {
            apartmentScoreService.save(apartmentScore);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @DeleteMapping("/delete")
    @ApiOperation("批量删除")
    @ResponseBody
    public Result<String> deletes(@RequestParam("ids[]") Integer[] ids) {
        try {
            apartmentScoreService.deletes(ids);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @PostMapping("/adds")
    @ApiOperation("批量添加")
    @ResponseBody
    public Result<String> saves(@RequestBody ApartmentScoreDto[] apartmentScoreDtos) {
        log.info(Arrays.toString(apartmentScoreDtos));
        try {
            apartmentScoreService.saves(apartmentScoreDtos);
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
            scores = apartmentScoreService.findOneByStuId();
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(scores);
    }
}
