package cs.scnu.controller;

import cs.scnu.entity.LoginLog;
import cs.scnu.mapper.LogMapper;
import cs.scnu.result.PageResult;
import cs.scnu.result.Result;
import cs.scnu.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {
    @Autowired
    private LogService logService;


    @GetMapping("/LoginLog/page")
    public Result<PageResult> getLoginLog(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        log.info("分页查询, 参数: {},{},{},{},{}", page, pageSize);
        PageResult pageResult = logService.getLoginLog(page, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("/TracerLog/page")
    public Result<PageResult> getTracerLog(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        log.info("分页查询, 参数: {},{},{},{},{}", page, pageSize);
        PageResult pageResult = logService.TracerLog(page, pageSize);
        return Result.success(pageResult);
    }
}
