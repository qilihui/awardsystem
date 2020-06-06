package xyz.xhui.awardsystem.controller.houseparent;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.NoticeApartment;
import xyz.xhui.awardsystem.service.NoticeApartmentService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/noticeApartment")
@Slf4j
@RolesAllowed("HOUSEPARENT")
@Api("宿舍公告管理")
public class NoticeApartmentController {
    @Autowired
    private NoticeApartmentService apartmentService;

    @GetMapping("")
    @ResponseBody
    public Result<List<NoticeApartment>> findAll(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize) {
        PageDto<List<NoticeApartment>> pageDto = null;
        try {
            pageDto = apartmentService.findAll(pageNum - 1, pageSize);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult(pageDto.getCount(), pageDto.getObj());
    }

    @GetMapping("/edit")
    public String findById(@RequestParam("id") Integer id, Model model) {
        NoticeApartment noticeApartment = null;
        try {
            noticeApartment = apartmentService.findById(id);
        } catch (UnknownException e) {
            model.addAttribute("exception", e.getMessage());
            return "exception";
        }
        log.info(noticeApartment.toString());
        model.addAttribute("notice", noticeApartment);
        return "houseparent/notice-edit";
    }

    @PutMapping("")
    @ResponseBody
    public Result<String> update(NoticeApartment noticeApartment) {
        try {
            apartmentService.update(noticeApartment);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @PostMapping("")
    @ResponseBody
    public Result<String> save(NoticeApartment noticeApartment) {
        try {
            apartmentService.save(noticeApartment);
        } catch (UnknownException e) {
            return ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult();
    }

    @DeleteMapping("")
    @ResponseBody
    public Result<String> delete(@RequestParam("id") Integer id) {
        Integer integer = apartmentService.deleteById(id);
        if (integer > 0) {
            return ResultFactory.buildSuccessResult();
        } else {
            return ResultFactory.buildFailResult("id不存在");
        }
    }
}
