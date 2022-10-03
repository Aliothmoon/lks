package pers.hyy.bookshop.controller.household;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hyy.bookshop.common.Result;
import pers.hyy.bookshop.common.enums.CodeEnum;
import pers.hyy.bookshop.entity.HouseHold;
import pers.hyy.bookshop.service.HouseHoldMange;
import pers.hyy.bookshop.vo.ContentType;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class HouseHoldSetup {
    public static final String StrData = "data";
    @Resource
    private HouseHoldMange houseHoldMange;

    @RequestMapping("/det")
    public Result det(@RequestBody JSONObject json) throws Exception {
        List data = (List) json.get(StrData);
        houseHoldMange.deleteHouse(data);
        return Result.Return(CodeEnum.SUCCESS);
    }

    //    分页表值
    @RequestMapping("/pages")
    public Result pages(@RequestBody JSONObject json) {
        Integer pagesIndex = json.getInteger(StrData);
        List<HouseHold> div = houseHoldMange.pageDiv(pagesIndex);
        return Result.Return(CodeEnum.SUCCESS, div);
    }

    //    获取总数
    @RequestMapping("/pagenums")
    public Result pagenums(@RequestBody JSONObject json) {
        Integer tableNums = houseHoldMange.getTableNums();
        return Result.Return(CodeEnum.SUCCESS, tableNums);
    }

    //    获取信息
    @RequestMapping("/addtc")
    public Result addi(@RequestBody JSONObject json) {
        HouseHold houseHold = json.getObject(StrData, HouseHold.class);
        houseHoldMange.addHouse(houseHold);
        Integer keyId = houseHoldMange.getKeyId(houseHold.getName(), houseHold.getAddress());
        return Result.Return(CodeEnum.SUCCESS, keyId);
    }

    @RequestMapping("/getmodel")
    public Result getOneModel(@RequestBody JSONObject json) {
        Integer keyId = json.getInteger(StrData);
        HouseHold model = houseHoldMange.getHouseHoldById(keyId);
        return Result.Return(CodeEnum.SUCCESS, model);
    }

    @RequestMapping("/update")
    public Result updateHouseHold(@RequestBody JSONObject json) {
        HouseHold houseHold = json.getObject(StrData, HouseHold.class);
        houseHoldMange.updateHouseHold(houseHold, houseHold.getKeyId());
        return Result.Return(CodeEnum.SUCCESS);
    }

    @RequestMapping("/querybytype")
    public Result queryByAlpha(@RequestBody JSONObject json) {
        ContentType data = json.getObject(StrData, ContentType.class);
        List<HouseHold> holdList = houseHoldMange.queryByKey(data);
        return Result.Return(CodeEnum.SUCCESS, holdList);
    }
}
