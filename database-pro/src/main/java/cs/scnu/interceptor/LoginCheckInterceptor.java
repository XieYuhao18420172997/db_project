package cs.scnu.interceptor;


import com.alibaba.fastjson.JSONObject;
import cs.scnu.Utils.JwtUtils;
import cs.scnu.context.BaseContext;
import cs.scnu.result.Result;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        //1.获取请求url。
        String url = req.getRequestURL().toString();
        log.info("请求的url: {}",url);

        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if(url.contains("login")){
            log.info("登录操作, 放行...");
            return true;
        }

        //3.获取请求头中的令牌（token）。
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空,返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）。
        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            Long userId = Long.valueOf(claims.get("userId").toString());


            /////将用户id存储到ThreadLocal////////
            BaseContext.setCurrentId(userId);
            ////////////////////////////////////

        } catch (Exception e) {//jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败, 返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);

            return false;
        }

        //6.放行。
        log.info("令牌合法, 放行");
        return true;
    }
}
