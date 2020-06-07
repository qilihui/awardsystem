package xyz.xhui.awardsystem.controller.union;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.model.entity.SysUserUnion;
import xyz.xhui.awardsystem.service.DeptService;
import xyz.xhui.awardsystem.service.GradeService;
import xyz.xhui.awardsystem.service.TermService;
import xyz.xhui.awardsystem.service.UserUnionService;

import javax.annotation.security.RolesAllowed;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@Slf4j
@RolesAllowed("UNION")
public class UnionStatController {
    @Autowired
    private UserUnionService unionService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private TermService termService;

    @GetMapping("/union-home")
    public String getHome(Model model) {
        Integer week = null;
        try {
            week = termService.findNowWeek();
        } catch (UnknownException e) {
            model.addAttribute("exception", e.getMessage());
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("nowTime", df.format(System.currentTimeMillis()));
        model.addAttribute("nowWeek", week);
        return "home/union";
    }

    @GetMapping(value = "/union/getPage")
    public String getPage(@RequestParam("name") String pageName) {
        log.info(pageName);
        return "union/" + pageName;
    }

    @GetMapping("/union/info")
    public String getInfo(Model model) {
        Optional<SysUserUnion> userUnionOptional = unionService.findBySysUserId(MyUserUtils.getId());
        SysUserUnion userUnion = null;
        try {
            userUnion = userUnionOptional.get();
            model.addAttribute("sysUser", userUnion);
            model.addAttribute("deptName", deptService.findById(userUnion.getDeptId()).get().getName());
        }catch (NoSuchElementException e){
            model.addAttribute("exception", e.getMessage());
            return "exception";
        }
        return "union/union-info";
    }
}
