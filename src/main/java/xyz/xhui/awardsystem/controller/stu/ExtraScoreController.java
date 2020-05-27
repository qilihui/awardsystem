package xyz.xhui.awardsystem.controller.stu;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.config.utils.MyTimeUtils;
import xyz.xhui.awardsystem.model.entity.ExtraTime;
import xyz.xhui.awardsystem.service.ExtraTimeService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/extraScore")
@Api("学生加分项的管理")
public class ExtraScoreController {
    @Autowired
    private ExtraTimeService extraTimeService;

    @RolesAllowed("STU")
    @ResponseBody
    @GetMapping("")
    public String extraScorePage() {
        return null;
    }

    @RolesAllowed("STU")
    @PostMapping("/add")
    @ResponseBody
    public String addExtraScore() {
        return null;
    }

//    @RolesAllowed("TUTOR")
//    @GetMapping("/time")
//    @ResponseBody
//    public String extraScoreTimePage(){
//        log.info("time");
//        return null;
//    }

    @RolesAllowed("TUTOR")
    @GetMapping("/time")
    @ResponseBody
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
    public Result<String> addExtraTime(String name, String beginTime, String endTime, Integer termId) {
        ExtraTime extraTime = null;
        try {
            extraTime = new ExtraTime(name, termId, MyTimeUtils.getTimeMillis(beginTime), MyTimeUtils.getTimeMillis(endTime));
            extraTimeService.save(extraTime);
        } catch (EntityFieldException | UnknownException e) {
            return ResultFactory.buildFailResult();
        }
        log.info(extraTime.toString());
        return ResultFactory.buildSuccessResult();
    }

    @RolesAllowed("TUTOR")
    @DeleteMapping("/time")
    @ResponseBody
    public Result<String> deeteBuId(@RequestParam("id") Integer id) {
        return ResultFactory.buildSuccessResult(extraTimeService.deleteById(id), null);
    }
}
