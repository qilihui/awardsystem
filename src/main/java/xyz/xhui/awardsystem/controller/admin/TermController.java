package xyz.xhui.awardsystem.controller.admin;

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
import xyz.xhui.awardsystem.config.utils.MyTimeUtils;
import xyz.xhui.awardsystem.model.entity.SysTerm;
import xyz.xhui.awardsystem.service.TermService;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/term")
@Api(tags = "学期管理")
@RolesAllowed({"ADMIN"})
@Slf4j
public class TermController {
    @Autowired
    private TermService termService;

    @ResponseBody
    @ApiOperation("查询所有")
    @GetMapping("")
    @RolesAllowed({"ADMIN", "UNION", "HOUSEPARENT", "STU", "TUTOR"})
    public Result<List<SysTerm>> getAll() {
        return ResultFactory.buildSuccessResult(termService.findAll());
    }

    @PostMapping("/add")
    @ApiOperation("添加")
    @ResponseBody
    public Result<String> add(String name, String beginTime, String endTime) {
        log.info(name + beginTime + endTime);
        try {
            SysTerm sysTerm = new SysTerm(null, name, MyTimeUtils.getTimeMillis(beginTime), MyTimeUtils.getTimeMillis(endTime));
            termService.sava(sysTerm);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @DeleteMapping("")
    @ApiOperation("根据id删除系部")
    @ResponseBody
    public Result<String> deleteById(@RequestParam("ids[]") Integer[] ids) {
        try {
            termService.deletes(ids);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        log.info(Arrays.toString(ids));
        return ResultFactory.buildSuccessResult();
    }

    @GetMapping("/week")
    @ApiOperation("获取当前周")
    @ResponseBody
    @RolesAllowed({"ADMIN", "UNION", "HOUSEPARENT", "STU", "TUTOR"})
    public Result<Integer> getWeek(){
        Integer nowWeek = 0;
        try {
            nowWeek = termService.findNowWeek();
        } catch ( UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(nowWeek);
    }
}
