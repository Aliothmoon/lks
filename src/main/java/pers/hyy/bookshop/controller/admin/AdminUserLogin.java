package pers.hyy.bookshop.controller.admin;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.hyy.bookshop.common.Result;
import pers.hyy.bookshop.common.enums.CodeEnum;
import pers.hyy.bookshop.entity.User;
import pers.hyy.bookshop.exception.GlobalExceptionHandler;
import pers.hyy.bookshop.service.HouseHoldMange;
import pers.hyy.bookshop.service.UserManage;
import pers.hyy.bookshop.utils.PicUtils;
import pers.hyy.bookshop.utils.TokenUtils;
import pers.hyy.bookshop.vo.PasswordChange;


import javax.annotation.Resource;


import java.io.InputStream;

@RestController
@CrossOrigin
public class AdminUserLogin {
    private static final Logger logger = LoggerFactory.getLogger(AdminUserLogin.class);
    @Resource
    private UserManage userManage;

    /**
     *
     * 使用RESTful风格有更好的降低耦合度
     * 时间不够没有使用RESTful 全是post
     * 映射路径不规范
     * VerifyCodeUtils工具类
     * 验证码图片可用base64响应请求
     * 未写待补
     * Axios+Json
     * 前后端数据响应
     * 拦截器用Vue的路由导航代替
     * Controller写了过多业务逻辑
     */
    @RequestMapping("/reg")
    public Result registerUser(@RequestBody JSONObject json) {
        User data = json.getObject("data", User.class);
        System.out.println("data = " + data);
        Boolean check = userManage.registerUser(data);
        return check ? Result.Return(CodeEnum.SUCCESS) : Result.Return(CodeEnum.FAILURE);

    }

    @RequestMapping("/login")
    public Result login(@RequestBody JSONObject json) throws Exception {
        if (json == null) {
            logger.info("NULL");
            return Result.Return(CodeEnum.FAILURE);
        }
        User user = json.toJavaObject(User.class);
        User login = userManage.login(user);
        if (login != null) {
            Boolean checked = json.getBoolean("checked");
            logger.info("All Right");
            return Result.Return(CodeEnum.SUCCESS);
        } else {
            logger.info("Login Failed");
            return Result.Return(CodeEnum.FAILURE);
        }

    }

    @RequestMapping("/verify")
    public Result verify(@RequestBody JSONObject json) {
        logger.info("Verify...");
        String token = json.getString("token");
        Boolean ch = TokenUtils.verifyToken(token);
        if (ch) {
            logger.info("Verify OK");
            return Result.Return(CodeEnum.SUCCESS, TokenUtils.getName(token));
        } else {
            return Result.Return(CodeEnum.FAILURE);
        }
    }

    @RequestMapping("/asktoken")
    public Result askToken(@RequestBody JSONObject json) {
        logger.info("Ask Token...");
        try {
            String token = TokenUtils.getToken(json.toJavaObject(User.class));
            return Result.Return(CodeEnum.SUCCESS, token);
        } catch (Exception e) {
            logger.error("Ask Token Error");
            return Result.Return(CodeEnum.FAILURE);
        }
    }

    //    输入文件流 路径奇怪    前端冗杂逻辑奇怪
    @RequestMapping("/axt")
    public Result axt(@RequestParam MultipartFile file, @RequestParam("username") String username) throws Exception {
        logger.info(username);
        if (username == null)
            return Result.Return(CodeEnum.FAILURE);
        InputStream inputStream = file.getInputStream();
        PicUtils.setPic(inputStream, username);
        return Result.Return(CodeEnum.SUCCESS);
    }

//    过多使用sout而非logger
    @RequestMapping("/change")
    public Result change(@RequestBody JSONObject json) {
//        System.out.println("json = " + json);
        PasswordChange data = json.getObject("data", PasswordChange.class);
        String token = data.getToken();
        String name = TokenUtils.getName(token);
        if (token == null)
            return Result.Return(CodeEnum.FAILURE);
        boolean b = userManage.updateUser(data, name);
        if (!b) {
            logger.error("修改失败");
            return Result.Return(CodeEnum.FAILURE);
        }
        return Result.Return(CodeEnum.SUCCESS);
    }

}
