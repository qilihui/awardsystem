package xyz.xhui.awardsystem.controller.stu;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.dto.NoticeDto;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.service.NoticeStuService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RolesAllowed("STU")
@RequestMapping("/noticeStu")
@Api("学生查看公告")
public class NoticeStuController {
    @Autowired
    private NoticeStuService noticeStuService;

    @GetMapping("")
    @ResponseBody
    public Result<List<NoticeDto>> findAll(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize) {
        PageDto<List<NoticeDto>> pageDto = null;
        try {
            pageDto = noticeStuService.findAll(pageNum - 1, pageSize);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(pageDto.getCount(), pageDto.getObj());
    }
}
