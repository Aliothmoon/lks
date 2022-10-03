package pers.hyy.bookshop.service;

import org.springframework.stereotype.Service;
import pers.hyy.bookshop.entity.User;
import pers.hyy.bookshop.vo.PasswordChange;


public interface UserManage {

    User login(User user);

    boolean updateUser(PasswordChange vo, String userName);

    Boolean registerUser(User user);
}
