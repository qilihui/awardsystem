package xyz.xhui.awardsystem.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.service.UserHouseparentService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/houseparent")
@Api(tags = "宿管管理接口")
@RolesAllowed({"ADMIN"})
@Slf4j
public class UserHouseparentController {
    private static final Logger logger = LoggerFactory.getLogger(UserHouseparentController.class);

    @Autowired
    private UserHouseparentService userHouseparentService;

    @GetMapping(value = "/edit")
    @ApiOperation("查询用户信息")
    public String edit(Model model, @RequestParam("id") Integer id) {
//        log.info(id.toString());
        Optional<SysUserHouseparent> userHouseparentOptional = userHouseparentService.findBySysUserId(id);
        model.addAttribute("sysUser", userHouseparentOptional.get().getUser());
        model.addAttribute("userHouseparent", userHouseparentOptional.get());
        log.info(userHouseparentOptional.get().toString());
        return "user/user-houseparent-edit";
    }

//    @PostMapping(value = "")
//    @ApiOperation("添加")
//    public Result save(@RequestBody SysUserHouseparent userHouseparent) {
//        logger.info(userHouseparent.toString());
//        SysUserHouseparent retUserHouseparent = null;
//        try {
//            retUserHouseparent = userHouseparentService.save(userHouseparent);
//        } catch (EntityFieldException e) {
//            return ResultFactory.buildFailResult(null, e.getMessage());
//        }
//        PasswordUtils.hiddenPassword(retUserHouseparent);
//        return ResultFactory.buildSuccessResult(retUserHouseparent, "添加成功");
//    }
//
//    @GetMapping(value = "")
//    @ApiOperation("查询所有详细信息")
//    @ResponseBody
//    public Result findAll() {
//        List<SysUserHouseparent> houseparents = userHouseparentService.findAll();
//        for (SysUserHouseparent houseparent : houseparents) {
//            PasswordUtils.hiddenPassword(houseparent);
//        }
//        return ResultFactory.buildSuccessResult(houseparents, "查询成功");
//    }
//
//    @GetMapping(value = "{id}")
//    @ApiOperation("根据id查询")
//    public Result findById(@PathVariable Integer id) {
//        Optional<SysUserHouseparent> retUserHouseparent = userHouseparentService.findById(id);
//        retUserHouseparent.ifPresent(PasswordUtils::hiddenPassword);
//        return ResultFactory.buildSuccessResult(retUserHouseparent.orElse(null), "查询成功");
//    }
//
//    @DeleteMapping(value = "/{id}")
//    @ApiOperation("根据id删除")
//    public Result deleteById(@PathVariable Integer id) {
//        if (userHouseparentService.deleteById(id)) {
//            return ResultFactory.buildSuccessResult(null, "删除成功");
//        } else {
//            return ResultFactory.buildFailResult(null, "删除失败");
//        }
//    }
}
