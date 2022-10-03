package pers.hyy.bookshop.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pers.hyy.bookshop.dao.HouseHoldMapper;
import pers.hyy.bookshop.entity.HouseHold;
import pers.hyy.bookshop.service.HouseHoldMange;
import pers.hyy.bookshop.vo.ContentType;
import pers.hyy.bookshop.vo.SimplifyModel;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 * @author AliothHu-TSKM
 * @date 2022/7/6 16:47
 * @version preview
 */
@Service @Log4j2
public class HouseHoldMangeImpl implements HouseHoldMange {
    @Resource
    private HouseHoldMapper houseHoldMapper;

    @Override
    public List<HouseHold> getHouseHolds() {
        return houseHoldMapper.searchAll();
    }

    @Override
    public HouseHold getHouseHoldById(Integer id) {
        return houseHoldMapper.selectById(id);
    }

    @Override
    public void updateHouseHold(HouseHold house, Integer key) {
        houseHoldMapper.updateHold(house, key);
    }

    @Override
    public Integer getKeyId(String name, String address) {
        SimplifyModel model = new SimplifyModel(name, address);
        return houseHoldMapper.selectByModel(model);
    }

    @Override
    public List<HouseHold> queryByKey(ContentType contentType) {
        String type = contentType.getType();
        String content = contentType.getContent();
        switch (type) {
            case "A":
                return houseHoldMapper.selectByNames(content);
            case "B":
                return houseHoldMapper.selectByAddress(content);
            case "C":
                return houseHoldMapper.selectByEmail(content);
            case "D":
                return houseHoldMapper.selectByPhone(content);
            default:
                return houseHoldMapper.selectByFuzzy(content);
        }
    }

    @Override
    public List<HouseHold> pageDiv(Integer pagesIndex) {
//        可以使用sql语句 进行分页查询limit n,m
        List<HouseHold> houseHolds = houseHoldMapper.searchAll();
        int fromIndex = (pagesIndex - 1) * 10;
        int toIndex = fromIndex + 10;
        if (toIndex > houseHolds.size()) {
            toIndex = houseHolds.size();
        }
        return houseHolds.subList(fromIndex, toIndex);
    }

    @Override
    public void addHouse(HouseHold houseHold) {
        houseHoldMapper.insertHold(houseHold);
    }

    @Override
    public void deleteHouse(List<Integer> u) {
        houseHoldMapper.delById(u);
    }

    @Override
    public Integer getTableNums() {
        return houseHoldMapper.getTotalNums();
    }
}
