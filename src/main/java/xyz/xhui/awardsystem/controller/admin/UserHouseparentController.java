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
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;
import xyz.xhui.awardsystem.service.UserHouseparentService;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@Controller
@RequestMapping("/user/houseparent")
@Api(tags = "用户管理：宿管")
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
        return "admin/user/user-houseparent-edit";
    }

    @PostMapping("edit")
    @ApiOperation("修改用户信息")
    @ResponseBody
    public Result<String> editUserInfo(UserInfoDto userInfoDto, SysUserDto userDto) {
        log.info(userInfoDto.toString());
        log.info(userDto.toString());
        Integer count = null;
        try {
            count = userHouseparentService.updateEmailAndRealName(userInfoDto, userDto);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        if (count > 1) {
            return ResultFactory.buildSuccessResult();
        }
        return ResultFactory.buildFailResult();
    }

    @PostMapping(value = "/add")
    @ApiOperation("添加")
    @ResponseBody
    public Result<String> save(UserInfoDto userInfoDto, SysUserDto userDto) {
        logger.info(userInfoDto.toString());
        logger.info(userDto.toString());
        try {
            userHouseparentService.save(userInfoDto,userDto);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

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

//    @DeleteMapping(value = "")
//    @ApiOperation("根据sysUserId删除")
//    @ResponseBody
//    public Result<String> deleteById(@RequestParam Integer id) {
//        try {
//            userHouseparentService.deleteBySysUserId(id);
//        } catch (EntityFieldException e) {
//            return ResultFactory.buildFailResult(e.getMessage());
//        }
//        return ResultFactory.buildSuccessResult();
//    }
}
