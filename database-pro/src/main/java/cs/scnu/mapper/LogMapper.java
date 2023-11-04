package cs.scnu.mapper;

import com.github.pagehelper.Page;
import cs.scnu.entity.DataTracer;
import cs.scnu.entity.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
public interface LogMapper {

    @Insert("insert into t_login_log (user_id, login_result, create_time) values (#{userId},#{loginResult},#{createTime})")
    void addLoginLog(LoginLog loginLog);

    @Select("select * from t_login_log")
    Page<LoginLog> getLoginLog();

    @Select("select * from t_data_tracer")
    Page<DataTracer> TracerLog();

    @Insert("insert into t_data_tracer (user_id, content, create_time) VALUES (#{userId},#{content},#{createTime})")
    void addTracerLog(DataTracer dataTracer);
}
