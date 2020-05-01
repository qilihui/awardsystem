package xyz.xhui.awardsystem.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.PasswordErrorException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.service.UserService;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@Controller
@RequestMapping("/user")
@Api(tags = "用户管理接口")
@Slf4j
//@Secured("ROLE_ADMIN")
//@PreAuthorize("hasRole('STU')")
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping(value = "")
//    @ApiOperation("添加用户")
//    public Result save(@RequestBody SysUser sysUser) {
//        logger.info(sysUser.toString());
//        SysUser retUser = null;
//        try {
//            retUser = userService.save(sysUser);
//        } catch (EntityFieldException e) {
//            return ResultFactory.buildFailResult(null, e.getMessage());
//        }
//        PasswordUtils.hiddenPassword(retUser);
//        return ResultFactory.buildSuccessResult(retUser, "添加成功");
//    }

//    @GetMapping(value = "")
//    @ApiOperation("查询所有用户")
//    @RolesAllowed({"ADMIN"})
//    public Result findAll() {
//        List<SysUser> content = userService.findAll();
//        List<SysUserDto> userDtos = new ArrayList<>();
//        for (SysUser user : content) {
//            userDtos.add(new SysUserDto(user));
//        }
//        return ResultFactory.buildSuccessResult(userDtos.size(), userDtos);
//    }


    @GetMapping(value = "")
    @ApiOperation("分页查询所有用户")
    @RolesAllowed({"ADMIN"})
    @ResponseBody
    public Result<List<SysUserDto>> findAll(@RequestParam("page") Integer pagenum, @RequestParam("limit") Integer pagesize) {
        List<SysUser> userList = userService.findAll(pagenum - 1, pagesize);
        List<SysUserDto> userDtos = new ArrayList<>();
        for (SysUser user : userList) {
            userDtos.add(new SysUserDto(user));
        }
        return ResultFactory.buildSuccessResult(userService.getCount(), userDtos);
    }

//    @GetMapping(value = "/edit")
//    @ApiOperation("编辑用户信息")
//    public String edit(Model model, @RequestParam("id") Integer id) {
////        log.info(id.toString());
//        Optional<SysUser> userOptional = userService.findById(id);
//        model.addAttribute("sysUser", userOptional.get());
//        return "user/user-edit";
//    }

    //    @GetMapping(value = "/{id}")
//    @ApiOperation("根据id查询用户")
//    @RolesAllowed({"ADMIN"})
//    public Result findOne(@PathVariable Integer id) {
//        Optional<SysUser> retUser = userService.findById(id);
//        retUser.ifPresent(PasswordUtils::hiddenPassword);
//        return ResultFactory.buildSuccessResult(retUser.orElse(null), "查询成功");
//    }
//
    @PostMapping(value = "/changePassword")
    @ApiOperation("修改密码")
    @ResponseBody
    public Result<String> changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        try {
            userService.changePassword(oldPassword, newPassword);
        } catch (PasswordErrorException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @PostMapping(value = "/resetPassword")
    @ApiOperation("恢复默认密码")
    @RolesAllowed({"ADMIN"})
    @ResponseBody
    public Result<String> resetPassword(@RequestParam("userId") Integer userId) {
        try {
            userService.changePassword(userId);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult("用户不存在");
        }
        return ResultFactory.buildSuccessResult();
    }

    @DeleteMapping("/users")
    @ApiOperation("批量删除")
    @RolesAllowed({"ADMIN"})
    @ResponseBody
    public Result<String> deleteUsers(@RequestParam("ids[]") Integer[] ids) {
//        log.info(Arrays.toString(ids));
        try {
            userService.deleteUsers(ids);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }
}
