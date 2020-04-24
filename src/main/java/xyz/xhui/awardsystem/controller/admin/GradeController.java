package xyz.xhui.awardsystem.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.entity.SysGrade;
import xyz.xhui.awardsystem.service.GradeService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/grade")
@Api(tags = "年级管理接口")
@RolesAllowed({"ADMIN"})
@Slf4j
public class GradeController {
    @Autowired
    private GradeService gradeService;

//    @PostMapping(name = "")
//    @ApiOperation("添加年级")
//    public Result save(@RequestParam Integer grade) {
//        SysGrade sysGrade = new SysGrade();
//        sysGrade.setName(grade);
//        return ResultFactory.buildSuccessResult(gradeService.save(sysGrade), "保存成功");
//    }
//
    @GetMapping(name = "")
    @ApiOperation("查询所有年级")
    @ResponseBody
    public Result<List<SysGrade>> findAll() {
        List<SysGrade> gradeList = gradeService.findAll();
        return ResultFactory.buildSuccessResult(gradeList.size(), gradeList);
    }

    @PostMapping("/edit")
    @ApiOperation("修改年级信息")
    @ResponseBody
    public Result<String> updateGrade(SysGrade grade) {
        log.info(grade.toString());
        try {
            gradeService.updateGrade(grade);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @PostMapping("/add")
    @ApiOperation("添加年级")
    @ResponseBody
    public Result<String> save(@RequestParam Integer name) {
        log.info(name.toString());
        try {
            gradeService.save(name);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

//    @GetMapping(value = "/{id}")
//    @ApiOperation("根据id查询年级")
//    public Result findOne(@PathVariable Integer id) {
//        return ResultFactory.buildSuccessResult(gradeService.findById(id).orElse(null), "查询成功");
//    }

    @DeleteMapping("")
    @ApiOperation("根据id删除年级")
    @ResponseBody
    public Result<String> deleteById(@RequestParam("ids[]") Integer[] ids) {
        try {
            gradeService.deleteGrades(ids);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }
}
