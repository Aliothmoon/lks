package pers.hyy.bookshop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.hyy.bookshop.entity.HouseHold;
import pers.hyy.bookshop.vo.SimplifyModel;

import java.util.List;

@Mapper
public interface HouseHoldMapper {
    List<HouseHold> searchAll();
    Integer getTotalNums();
    void delById(List<Integer> u);
    HouseHold selectById(Integer id);
    void insertHold(HouseHold houseHold);
    Integer selectByModel(SimplifyModel vo);
    void updateHold(@Param("item")HouseHold household,@Param("key")Integer keyId);

    List<HouseHold> selectByNames(String name);
    List<HouseHold> selectByAddress(String address);
    List<HouseHold> selectByEmail(String email);
    List<HouseHold> selectByPhone(String phone);
    List<HouseHold> selectByFuzzy(String st);



}
