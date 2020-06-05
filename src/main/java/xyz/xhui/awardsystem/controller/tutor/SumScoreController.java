package xyz.xhui.awardsystem.controller.tutor;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.xhui.awardsystem.config.result.Result;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.model.vo.SumScoreVo;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/sumScore")
@RolesAllowed("TUTOR")
@Slf4j
@Api("综合成绩管理")
public class SumScoreController {
    @PostMapping("/get")
    @ResponseBody
    public Result<String> getSumScore(SumScoreVo sumScoreVo) {
        log.info(sumScoreVo.toString());
        return ResultFactory.buildSuccessResult();
    }
}
