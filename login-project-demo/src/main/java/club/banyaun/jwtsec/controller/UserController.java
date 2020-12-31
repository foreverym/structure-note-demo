package club.banyaun.jwtsec.controller;

import club.banyaun.jwtsec.entity.User;
import club.banyaun.jwtsec.service.UserService;
import io.swagger.annotations.ApiKeyAuthDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : WangYB
 * @time: 2020/12/23  16:19
 */
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void doRegister(@RequestBody User user) {
        log.info(">>>>>>>>>>user: {}", user);
        userService.register(user);
    }

}
