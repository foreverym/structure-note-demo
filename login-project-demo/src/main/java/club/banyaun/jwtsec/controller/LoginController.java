package club.banyaun.jwtsec.controller;

import club.banyaun.jwtsec.entity.LoginDetail;
import club.banyaun.jwtsec.entity.RequestLoginUser;
import club.banyaun.jwtsec.entity.TokenDetail;
import club.banyaun.jwtsec.entity.TokenDetailImpl;
import club.banyaun.jwtsec.result.CodeMsg;
import club.banyaun.jwtsec.result.Result;
import club.banyaun.jwtsec.service.UserService;
import club.banyaun.jwtsec.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : WangYB
 * @time: 2020/12/23  16:19
 */
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenUtils tokenUtils;

    @PostMapping("/doLogin")
    public Result doLogin(@Valid @RequestBody RequestLoginUser requestLoginUser,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(CodeMsg.LOGIN_PARAM_ERROR);
        }
        LoginDetail loginDetail = userService.queryLoginDetail(requestLoginUser.getUsername());
        if (loginDetail.getActivated() == 0) {
            return Result.error(CodeMsg.NOT_ACTIVE);
        }
        if (requestLoginUser.getPassword().equals(loginDetail.getPassword())) {
            TokenDetail tokenDetail = new TokenDetailImpl(requestLoginUser.getUsername());
            String token = tokenUtils.generateToken(tokenDetail);
            return Result.success(token);
        }
        return null;
    }

}
