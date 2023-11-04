package cs.scnu.service;


import cs.scnu.entity.DataTracer;
import cs.scnu.entity.LoginLog;
import cs.scnu.result.PageResult;


public interface LogService {
    void addLoginLog(LoginLog loginLog);

    PageResult getLoginLog(Integer page, Integer pageSize);

    PageResult TracerLog(Integer page, Integer pageSize);

    void addTracerLog(DataTracer dataTracer);
}
