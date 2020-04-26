package zju.group1.forum.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import zju.group1.forum.dto.InfoMessage;
import zju.group1.forum.dto.Message;
import zju.group1.forum.dto.UserInfo;
import zju.group1.forum.interceptor.AuthToken;
import zju.group1.forum.mapper.UserInfoMapper;
import zju.group1.forum.mapper.UserMapper;
import zju.group1.forum.provider.RedisProvider;

import java.io.IOException;

@Api(tags = "个人信息")
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisProvider redisProvider;

    @ApiOperation("查看个人信息")
    @PostMapping(value = "/personinfo")
    @AuthToken
    public ModelAndView queryInfo(@RequestParam("authorizeToken") String authorizeToken) throws IOException {
        InfoMessage infoMessage = new InfoMessage();
        ModelAndView mv = new ModelAndView();

        if (authorizeToken == null) {
            infoMessage.setState(false);
            infoMessage.setMessage("请重新登录");
            infoMessage.setAuthorizeToken(authorizeToken);
            mv.addObject("queryinfo", infoMessage);
            mv.setViewName("personinfo");
            return mv;
        }

        String email = redisProvider.getAuthorizedName("authorizeToken");
        if (email == null) {
            infoMessage.setState(false);
            infoMessage.setMessage("无当前用户，请重新注册/登录");
            infoMessage.setAuthorizeToken(authorizeToken);
            mv.addObject("queryinfo", infoMessage);
            mv.setViewName("personinfo");
            return mv;
        }

        if (userInfoMapper.isUserInfoExist(email) == 0) {
            userInfoMapper.createUserInfo(email);
        }
        UserInfo userInfo = userInfoMapper.getUserInfo(email);

        infoMessage.setState(true);
        infoMessage.setMessage("查询成功！");
        infoMessage.setAuthorizeToken(authorizeToken);
        infoMessage.setInfo(userInfo);
        /*
        infoMessage.setReal_name(userInfo.getReal_name());
        infoMessage.setReal_name_hidden(userInfo.getReal_name_hidden());
        infoMessage.setEmail(userInfo.getEmail());
        infoMessage.setEamil_hidden(userInfo.getEamil_hidden());
        infoMessage.setBirth(userInfo.getBirth());
        infoMessage.setBirth_hidden(userInfo.getBirth_hidden());
        infoMessage.setGender(userInfo.getGender());
        infoMessage.setGender_hidden(userInfo.getGender_hidden());
        infoMessage.setHometown(userInfo.getHometown());
        infoMessage.setHometown_hidden(userInfo.getHometown_hidden());
        infoMessage.setOrganization(userInfo.getOrganization());
        infoMessage.setOrganization_hidden(userInfo.getOrganization_hidden());
        infoMessage.setPhone(userInfo.getPhone());
        infoMessage.setPhone_hidden(userInfo.getPhone_hidden());
        infoMessage.setSignature(userInfo.getSignature());
         */
        mv.addObject("queryinfo", infoMessage);
        mv.setViewName("personinfo");
        return mv;
    }


    @ApiOperation("修改个人信息")
    @PostMapping(value = "/modifyinfo")
    @AuthToken
    public ModelAndView editInfo(@RequestParam("authorizeToken") String authorizeToken,
                                       @RequestParam("info") InfoMessage newInfo) throws IOException {
        InfoMessage infoMessage = new InfoMessage();
        ModelAndView mv = new ModelAndView();

        if (authorizeToken == null) {
            infoMessage.setState(false);
            infoMessage.setMessage("请重新登录");
            infoMessage.setAuthorizeToken(authorizeToken);
            mv.addObject("editinfo", infoMessage);
            mv.setViewName("personinfo");
            return mv;
        }

        String email = redisProvider.getAuthorizedName("authorizeToken");
        if (email == null) {
            infoMessage.setState(false);
            infoMessage.setMessage("无当前用户，请重新注册/登录");
            infoMessage.setAuthorizeToken(authorizeToken);
            mv.addObject("editinfo", infoMessage);
            mv.setViewName("personinfo");
            return mv;
        }

        UserInfo newUserInfo = new UserInfo(newInfo);
        userInfoMapper.updateUserInfo(email, newUserInfo);
        infoMessage.setState(true);
        infoMessage.setMessage("修改成功！");
        infoMessage.setAuthorizeToken(authorizeToken);
        infoMessage.setInfo(newUserInfo);
        mv.addObject("editinfo", infoMessage);
        mv.setViewName("personinfo");

        return mv;
    }
}
