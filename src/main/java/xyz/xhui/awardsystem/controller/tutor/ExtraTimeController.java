package xyz.xhui.awardsystem.controller.tutor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.config.utils.MyTimeUtils;
import xyz.xhui.awardsystem.model.entity.ExtraScore;
import xyz.xhui.awardsystem.model.entity.ExtraTime;
import xyz.xhui.awardsystem.model.vo.ExtraScoreVo;
import xyz.xhui.awardsystem.model.vo.StuExtraTime;
import xyz.xhui.awardsystem.service.ExtraScoreService;
import xyz.xhui.awardsystem.service.ExtraTimeService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/extraTime")
@Api("辅导员加分项事件设定")
public class ExtraTimeController {
    @Autowired
    private ExtraTimeService extraTimeService;

    @RolesAllowed("STU")
    @ResponseBody
    @GetMapping("/stu/time")
    @ApiOperation("学生查看提交时间接口")
    public Result<List<StuExtraTime>> getAllextraScoreTimeByStu(@RequestParam("termId") Integer termId) {
        List<StuExtraTime> stuextraTimeList = null;
        try {
            stuextraTimeList = extraTimeService.findByStu(termId);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(stuextraTimeList.size(), stuextraTimeList);
    }

    @RolesAllowed("TUTOR")
    @GetMapping("/time")
    @ResponseBody
    @ApiOperation("辅导员查看提交时间接口")
    public Result<List<ExtraTime>> getAllextraScoreTime(@RequestParam("termId") Integer termId) {
        log.info("time all");
        List<ExtraTime> extraTimeList = null;
        try {
            extraTimeList = extraTimeService.findAll(termId);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(extraTimeList.size(), extraTimeList);
    }

    @RolesAllowed("TUTOR")
    @PostMapping("/time")
    @ResponseBody
    @ApiOperation("辅导员添加提交时间接口")
    public Result<String> addExtraTime(String name, String beginTime, String endTime, Integer termId) {
        ExtraTime extraTime = null;
        try {
            extraTime = new ExtraTime(name, termId, MyTimeUtils.getTimeMillis(beginTime), MyTimeUtils.getTimeMillis(endTime));
            if (extraTime.getBeginTime() > extraTime.getEndTime()) {
                throw new EntityFieldException("开始时间应小于结束时间");
            }
            extraTimeService.save(extraTime);
        } catch (EntityFieldException | UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        log.info(extraTime.toString());
        return ResultFactory.buildSuccessResult();
    }

    @RolesAllowed("TUTOR")
    @DeleteMapping("/time")
    @ResponseBody
    @ApiOperation("辅导员删除提交时间接口")
    public Result<String> deeteBuId(@RequestParam("id") Integer id) {
        return ResultFactory.buildSuccessResult(extraTimeService.deleteById(id), null);
    }
}
