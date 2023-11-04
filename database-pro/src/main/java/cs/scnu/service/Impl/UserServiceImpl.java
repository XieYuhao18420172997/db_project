package cs.scnu.service.Impl;

import cs.scnu.entity.User;
import cs.scnu.mapper.UserMapper;
import cs.scnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }
}
