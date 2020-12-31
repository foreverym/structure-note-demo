package club.banyaun.jwtsec.controller;

import club.banyaun.jwtsec.constant.RedisConstant;
import club.banyaun.jwtsec.service.UserService;
import club.banyaun.jwtsec.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : WangYB
 * @time: 2020/12/23  19:00
 */
@RestController
@RequestMapping("/email")
public class ActiveEmailController {

    public static final Logger logger = LoggerFactory.getLogger(ActiveEmailController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/activeEmail")
    public String activeEmail(Integer id, String check) {
        if (id == null || check == null) {
            return "请求失败";
        }
        String uuid = redisUtil.getValue(RedisConstant.REDIS_ACTIVE_CODE + id.toString());
        logger.info(uuid);
        if (uuid == null) {
            return "激活过期";
        }
        if (check.equals(uuid)) {
            int flag = userService.updateToActive(id);
            if (flag <= 0) {
                return "激活失败";
            }
            return "恭喜你，激活成功!";
        }
        return "激活失败";
    }

}
