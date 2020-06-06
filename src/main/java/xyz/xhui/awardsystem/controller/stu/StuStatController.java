package xyz.xhui.awardsystem.controller.stu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyTimeUtils;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.model.dto.ScoreDto;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.service.*;

import javax.annotation.security.RolesAllowed;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@Slf4j
@RolesAllowed("STU")
public class StuStatController {
    @Autowired
    private UserStuService userStuService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ApartmentScoreService apartmentScoreService;

    @Autowired
    private UnionScoreService unionScoreService;

    @GetMapping("/stu-home")
    @Transactional
    public String getHome(Model model) {
        List<ScoreDto> apartmentScoreDtoList = null;
        List<ScoreDto> unionScoreDtoList = null;
        Integer week = null;
        try {
            week = apartmentScoreService.findNowWeek();
            apartmentScoreDtoList = apartmentScoreService.findByNowWeek();
            unionScoreDtoList = unionScoreService.findByNowWeek();
        } catch (UnknownException e) {
            e.printStackTrace();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("nowTime", df.format(System.currentTimeMillis()));
        model.addAttribute("nowWeek", week);
        model.addAttribute("apartmentScoreList", apartmentScoreDtoList);
        model.addAttribute("unionScoreList", unionScoreDtoList);
        return "home/stu";
    }

    @GetMapping(value = "/stu/getPage")
    public String getPage(@RequestParam("name") String pageName) {
        log.info(pageName);
        return "stu/" + pageName;
    }

    @GetMapping("/stu/info")
    public String getInfo(Model model) {
        Optional<SysUserStu> userStuOptional = userStuService.findBySysUserId(MyUserUtils.getId());
        SysUserStu userStu = null;
        try {
            userStu = userStuOptional.get();
            model.addAttribute("sysUser", userStu);
            model.addAttribute("deptName", deptService.findById(userStu.getDeptId()).get().getName());
            model.addAttribute("gradeName", gradeService.findById(userStu.getGradeId()).get().getName());
            model.addAttribute("apartmentName", apartmentService.findById(userStu.getApartmentId()).get().getName());
        } catch (NoSuchElementException e) {
            model.addAttribute("exception", e.getMessage());
            return "exception";
        }
        return "stu/stu-info";
    }

    @GetMapping("/stu/extra-score-add")
    public String getPage1(@RequestParam("timeId") Integer timeId, @RequestParam("termId") Integer termId, Model model) {
        model.addAttribute("timeId", timeId);
        model.addAttribute("termId", termId);
        return "stu/extra-score-add";
    }
}
