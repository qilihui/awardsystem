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
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.service.UserService;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@Controller
@RequestMapping("/user")
@Api(tags = "用户管理：USER")
@Slf4j
@RolesAllowed({"ADMIN"})
//@Secured("ROLE_ADMIN")
//@PreAuthorize("hasRole('STU')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    @ApiOperation("分页查询所有用户")
    @ResponseBody
    public Result<List<SysUserDto>> findAll(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize) {
        PageDto<List<SysUser>> pageDto = userService.findAll(pageNum - 1, pageSize);
        List<SysUserDto> userDtos = new ArrayList<>();
        for (SysUser user : pageDto.getObj()) {
            userDtos.add(new SysUserDto(user));
        }
        return ResultFactory.buildSuccessResult(pageDto.getCount(), userDtos);
    }

    @PostMapping(value = "/changePassword")
    @ApiOperation("修改密码")
    @ResponseBody
    @RolesAllowed({"ADMIN", "TUTOR", "UNION", "HOUSEPARENT", "STU"})
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
    @ResponseBody
    public Result<String> deleteUsers(@RequestParam("ids[]") Integer[] ids) {
        try {
            userService.deleteUsers(ids);
        } catch (EntityFieldException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }
}
