package cs.scnu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cs.scnu.entity.DataTracer;
import cs.scnu.entity.LoginLog;
import cs.scnu.mapper.LogMapper;
import cs.scnu.result.PageResult;
import cs.scnu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    public void addLoginLog(LoginLog loginLog) {
        logMapper.addLoginLog(loginLog);
    }

    public PageResult getLoginLog(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        Page<LoginLog> pg=logMapper.getLoginLog();
        return new PageResult(pg.getTotal(),pg.getResult());
    }

    public PageResult TracerLog(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        Page<DataTracer> pg=logMapper.TracerLog();
        return new PageResult(pg.getTotal(),pg.getResult());
    }

    public void addTracerLog(DataTracer dataTracer) {
        logMapper.addTracerLog(dataTracer);
    }


}
