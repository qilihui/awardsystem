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
import xyz.xhui.awardsystem.model.entity.SysDept;
import xyz.xhui.awardsystem.service.DeptService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/dept")
@Api(tags = "系部管理接口")
@RolesAllowed({"ADMIN"})
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("")
    @ResponseBody
    public Result<List<SysDept>> findAllRetJson() {
        List<SysDept> sysDeptList = deptService.findAll();
        return ResultFactory.buildSuccessResult(sysDeptList.size(), sysDeptList);
    }

    @PostMapping("/edit")
    @ResponseBody
    public Result<String> updateDept(SysDept dept) {
        log.info(dept.toString());
        try {
            deptService.updateDept(dept);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @PostMapping("/add")
    @ApiOperation("添加系部")
    @ResponseBody
    public Result<String> save(@RequestParam String name) {
        log.info(name);
        try {
            deptService.save(name);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

//    @GetMapping(name = "")
//    @ApiOperation("查询所有系部")
//    public Result findAll() {
//        return ResultFactory.buildSuccessResult(deptService.findAll(), "查询成功");
//    }
//
//    @GetMapping(value = "/{id}")
//    @ApiOperation("根据id查询系部")
//    public Result findOne(@PathVariable Integer id) {
//        return ResultFactory.buildSuccessResult(deptService.findById(id).orElse(null), "查询成功");
//    }

    @DeleteMapping("")
    @ApiOperation("根据id删除系部")
    @ResponseBody
    public Result<String> deleteById(@RequestParam("ids[]") Integer[] ids) {
        try {
            deptService.deleteDepts(ids);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }
}
