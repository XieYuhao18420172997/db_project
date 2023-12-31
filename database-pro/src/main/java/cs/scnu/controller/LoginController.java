package cs.scnu.controller;

import cs.scnu.Utils.JwtUtils;
import cs.scnu.entity.LoginLog;
import cs.scnu.entity.User;
import cs.scnu.result.Result;
import cs.scnu.service.LogService;
import cs.scnu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Result login(@RequestBody User user) {
        log.info("{}登陆了", user);

        User u=userService.login(user);

        if(u!=null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", u.getUserId());
            claims.put("username", u.getUsername());

            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的信息


            LoginLog loginLog=LoginLog.builder()
                    .userId(u.getUserId())
                    .loginResult(0)
                    .createTime(LocalDateTime.now())
                    .build();
            logService.addLoginLog(loginLog);

            return Result.success(jwt);
        }


        LoginLog loginLog=LoginLog.builder()
                .userId(99)
                .loginResult(1)
                .createTime(LocalDateTime.now())
                .build();
        logService.addLoginLog(loginLog);

        return Result.error("用户名或密码错误！");
    }
}
