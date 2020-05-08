package xyz.xhui.awardsystem.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.entity.SysTerm;
import xyz.xhui.awardsystem.service.TermService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/term")
@Api(tags = "学期管理")
@RolesAllowed({"ADMIN"})
@Slf4j
public class TermController {
    @Autowired
    private TermService termService;

    @ResponseBody
    @ApiOperation("查询所有")
    @GetMapping("")
    public Result<List<SysTerm>> getAll() {
        return ResultFactory.buildSuccessResult(termService.findAll());
    }
}
