package xyz.xhui.awardsystem.controller.stu;

import io.swagger.annotations.Api;
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
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/extraScore")
@Api("学生加分项的管理")
public class ExtraScoreController {
    @Autowired
    private ExtraTimeService extraTimeService;

    @Autowired
    private ExtraScoreService extraScoreService;

    @Value("${myConf.uploadPath}")
    private String realPath;

    @RolesAllowed("STU")
    @ResponseBody
    @GetMapping("/stu/time")
    public Result<List<StuExtraTime>> getAllextraScoreTimeByStu(@RequestParam("termId") Integer termId) {
        List<StuExtraTime> stuextraTimeList = null;
        try {
            stuextraTimeList = extraTimeService.findByStu(termId);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(stuextraTimeList.size(), stuextraTimeList);
    }

    @RolesAllowed("STU")
    @PostMapping("/add")
    @ResponseBody
    public Result<String> addExtraScore(ExtraScore extraScore, @RequestParam("scoreStr") String scoreStr) {
        try {
            extraScore.setScore(Double.parseDouble(scoreStr));
            extraScoreService.save(extraScore);
        } catch (NumberFormatException e) {
            return ResultFactory.buildFailResult("分数格式错误");
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @RolesAllowed("STU")
    @PostMapping("/addFile")
    @ResponseBody
    public Result<String> addFile(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
//        String realPath = req.getServletContext().getRealPath("/uploads/");
        File f = new File(realPath);
        if (!f.exists()) {
            boolean mkdirs = f.mkdirs();
        }
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
        try {
            file.transferTo(new File(realPath, fileName));
        } catch (IOException e) {
            return ResultFactory.buildFailResult();
        }
        log.info(realPath + fileName);
        return ResultFactory.buildSuccessResult(fileName);
    }

    @RolesAllowed("STU")
    @GetMapping("/look")
    public String getStuExtraScore(@RequestParam("termId") Integer termId, @RequestParam("timeId") Integer timeId, Model model) {
        ExtraScore extraScore = null;
        try {
            extraScore = extraScoreService.findByStu(termId, timeId);
        } catch (UnknownException e) {
            return null;
        }
        model.addAttribute("score", extraScore.getScore());
        model.addAttribute("remark", extraScore.getRemark());
        model.addAttribute("path", "/extraScore/uploads/" + extraScore.getPath());
        return "stu/extra-score-look";
    }

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
    public Result<String> deeteBuId(@RequestParam("id") Integer id) {
        return ResultFactory.buildSuccessResult(extraTimeService.deleteById(id), null);
    }

    @RolesAllowed({"TUTOR", "STU"})
    @GetMapping("/uploads/{name}")
    public void ShowImg(@PathVariable("name") String pictureName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileInputStream fileIs = null;
        OutputStream outStream = null;
        try {
            fileIs = new FileInputStream(realPath + pictureName);
            //得到文件大小
            int i = fileIs.available();
            //准备一个字节数组存放二进制图片
            byte data[] = new byte[i];
            //读字节数组的数据
            fileIs.read(data);
            //设置返回的文件类型
            response.setContentType("image/*");
            //得到向客户端输出二进制数据的对象
            outStream = response.getOutputStream();
            //输出数据
            outStream.write(data);
            outStream.flush();
        } catch (Exception e) {
            return;
        } finally {
            //关闭输出流
            outStream.close();
            //关闭输入流
            fileIs.close();
        }
    }
}
