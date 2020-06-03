package xyz.xhui.awardsystem.controller.stu;

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
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/extraScore")
@Api("学生加分项的管理")
public class ExtraScoreController {
    @Autowired
    private ExtraScoreService extraScoreService;

    @Value("${myConf.uploadPath}")
    private String realPath;

    @RolesAllowed("STU")
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation("学生提交")
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
    @ApiOperation("学生提交图片")
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
    @ApiOperation("学生查看自己提交信息")
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
        model.addAttribute("createTime", MyTimeUtils.getTimeStr(extraScore.getCreateTime()));
        log.info(MyTimeUtils.getTimeStr(extraScore.getCreateTime()));
        return "stu/extra-score-look";
    }

    @RolesAllowed("TUTOR")
    @GetMapping("/tutor/list")
    @ApiOperation("辅导员查看学生提交信息")
    @ResponseBody
    public Result<Object> getStuExtraScoreList(@RequestParam("termId") Integer termId, @RequestParam("timeId") Integer timeId) {
        log.info(termId + " " + timeId);
        List<ExtraScoreVo> scoreList = null;
        try {
            scoreList = extraScoreService.findByTutor(termId, timeId);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(scoreList.size(), scoreList);
    }

    @RolesAllowed("TUTOR")
    @GetMapping("/tutor/one")
    @ApiOperation("辅导员查看学生提交信息one")
    public String getStuExtraScoreOne(@RequestParam("id") Integer id, @RequestParam("timeId") Integer timeId, Model model) {
        ExtraScoreVo scoreVo = null;
        try {
            scoreVo = extraScoreService.findById(id, timeId);
        } catch (UnknownException e) {
            model.addAttribute("exception", e.getMessage());
            return "exception";
        }
        scoreVo.setPath("/extraScore/uploads/" + scoreVo.getPath());
        model.addAttribute("vo", scoreVo);
        model.addAttribute("createTime", MyTimeUtils.getTimeStr(scoreVo.getCreateTime()));
        model.addAttribute("timeId", timeId);
        model.addAttribute("id", id);
        return "tutor/extra-score-look";
    }

    @RolesAllowed("TUTOR")
    @DeleteMapping("/tutor/del")
    @ApiOperation("辅导员删除学生提交信息")
    @ResponseBody
    public Result<String> delStuExtraScoreOne(@RequestParam("id") Integer id, @RequestParam("timeId") Integer timeId) {
        try {
            if (extraScoreService.deleteById(id, timeId) == 0) {
                return ResultFactory.buildFailResult("id不存在");
            }
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @RolesAllowed({"TUTOR", "STU"})
    @GetMapping("/uploads/{name}")
    @ApiOperation("辅导员和学生查看提交的图片")
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
            String substring = pictureName.substring(pictureName.lastIndexOf(".") + 1);
            response.setContentType("image/" + substring);
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

    @RolesAllowed("TUTOR")
    @PostMapping("/tutor/pass")
    @ApiOperation("辅导员审核学生提交信息")
    @ResponseBody
    public Result<String> passStuExtraScoreOne(@RequestParam("id") Integer id, @RequestParam("timeId") Integer timeId, @RequestParam("pass") Integer pass) {
        try {
            extraScoreService.passById(id, timeId, pass);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }
}
