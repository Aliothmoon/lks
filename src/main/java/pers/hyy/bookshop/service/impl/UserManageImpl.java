package pers.hyy.bookshop.service.impl;

import org.springframework.stereotype.Service;
import pers.hyy.bookshop.dao.UserMapper;
import pers.hyy.bookshop.entity.User;
import pers.hyy.bookshop.service.UserManage;
import pers.hyy.bookshop.utils.MD5Utils;
import pers.hyy.bookshop.vo.PasswordChange;

import javax.annotation.Resource;

@Service
public class UserManageImpl implements UserManage {
    private static final String Signature = "UTF-8";
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        String password = user.getPassword();
        user.setPassword(MD5Utils.MD5Encode(password, "UTF-8"));
        return userMapper.login(user);
    }

    @Override
    public Boolean registerUser(User user) {
        user.setPassword(MD5Utils.MD5Encode(user.getPassword(),"UTF-8"));
        User ext = userMapper.searchByUsername(user.getUsername());
        if (ext != null)
            return false;
        userMapper.insertUser(user);
        return true;
    }

    @Override
    public boolean updateUser(PasswordChange vo, String username) {
        String oldWords = MD5Utils.MD5Encode(vo.getOldWords(), Signature);
        String newWords = MD5Utils.MD5Encode(vo.getNewWords(), Signature);
        User user = userMapper.searchByUser(username, oldWords);
//        System.out.println("user = " + user);
        if (user == null)
            return false;
        userMapper.updatePassword(newWords, username);
        return true;
    }
}
