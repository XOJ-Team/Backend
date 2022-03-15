package com.xoj.backend.service.impl;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.base.Session;
import com.xoj.backend.util.UserThreadLocal;
import com.xoj.backend.entity.User;
import com.xoj.backend.mapper.UserBaseMapper;
import com.xoj.backend.param.NormalLoginParam;
import com.xoj.backend.param.MailLoginParam;
import com.xoj.backend.param.ResetPasswordParam;
import com.xoj.backend.service.LoginService;
import com.xoj.backend.util.TransUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/***
 * @Author yezhen
 * @Date 22:51 2022/3/12
 ***/

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserBaseMapper mapper;

    @Override
    public RestResponse<User> normalLogin(NormalLoginParam param) {
        String mail = param.getMail();
        String password = TransUtils.getMd5(param.getPassword());
        User user = getUser(mail);
        if(user == null){
            return RestResponse.error();
        }
        if(user.getPassword().equals(password)){
            Session.setUserInfo(user);
            UserThreadLocal.set(user);
            return RestResponse.ok(user);
        }else {
            return RestResponse.error();
        }

    }

    @Override
    @SuppressWarnings("all")
    public RestResponse<User> mailLogin(MailLoginParam param) {
        String verificationNumber = (String)Session.getSession().getAttribute("verificationNumber");

        if(verificationNumber == null || !param.getVerificationNumber().equals(verificationNumber)){
            return RestResponse.error("Verification code error");
        }

        User user  = User.builder().mail(param.getMail())
                .name(param.getName())
                .phoneNumber(param.getPhoneNumber())
                .password(param.getPassword()).build();
        int insert = mapper.insertSelective(user);

        if(insert == 1){
            Session.setUserInfo(user);
            UserThreadLocal.set(user);
            return RestResponse.ok(user);
        }else{
            return RestResponse.error();
        }
    }


    @Override
    public User getUser(String mail) {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("mail", mail);
        List<User> users = mapper.selectByExample(example);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }else{
            User user = users.get(0);
            Session.setUserInfo(user);
            return user;
        }
    }

    @Override
    @SuppressWarnings("all")
    public RestResponse<User> resetPassword(ResetPasswordParam param) {
        Integer verificationNumber = (Integer)Session.getSession().getAttribute("verificationNumber");

        if(verificationNumber == null || !param.getVerificationNumber().equals(verificationNumber)){
            return RestResponse.error("Verification code error");
        }

        User user  = User.builder().mail(param.getMail())
                .password(TransUtils.getMd5(param.getPassword())).build();
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("mail", user.getMail());
        int update = mapper.updateByExampleSelective(user, example);
        user = getUser(user.getMail());
        if(update == 1){
            Session.setUserInfo(user);
            UserThreadLocal.set(user);
            return RestResponse.ok(user);
        }else{
            return RestResponse.error();
        }
    }
}
