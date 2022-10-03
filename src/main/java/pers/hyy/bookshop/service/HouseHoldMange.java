package pers.hyy.bookshop.service;

import pers.hyy.bookshop.entity.HouseHold;
import pers.hyy.bookshop.vo.ContentType;
import pers.hyy.bookshop.vo.SimplifyModel;

import java.util.List;

public interface HouseHoldMange {
    List<HouseHold> getHouseHolds();
    Integer getTableNums();
    void deleteHouse(List<Integer> u);
    void addHouse(HouseHold houseHold);
    List<HouseHold> pageDiv(Integer pagesIndex);
    Integer getKeyId(String name, String address);

    HouseHold getHouseHoldById(Integer id);
    void updateHouseHold(HouseHold house,Integer key);
    List<HouseHold> queryByKey(ContentType contentType);
}
