package cs.scnu.mapper;

import cs.scnu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where username = #{username} and password = #{password}")
    User getByUsernameAndPassword(User user);
}
