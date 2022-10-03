package pers.hyy.bookshop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.hyy.bookshop.entity.HouseHold;
import pers.hyy.bookshop.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> selectAll();
    User login(User user);

    void insertUser(User user);
    User searchByUser(@Param("username")String username,@Param("password") String password);
    User searchByUsername(String username);

    void updatePassword(@Param("password") String password,@Param("name") String username);
}
