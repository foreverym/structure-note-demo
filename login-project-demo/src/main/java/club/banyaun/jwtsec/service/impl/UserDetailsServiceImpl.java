package club.banyaun.jwtsec.service.impl;

import club.banyaun.jwtsec.dao.UserMapper;
import club.banyaun.jwtsec.entity.ExtAuthUser;
import club.banyaun.jwtsec.entity.User;
import club.banyaun.jwtsec.entity.UserDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @version V1.0.0
 * @Description Spring Security 用于将 数据库中的用户信息转换成 userDetail 对象的服务类的实现类
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/8/2 16:43
 */
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取 userDetail
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ExtAuthUser authUser = userMapper.getAuthUser(username);
        if (authUser == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            UserDetailImpl userDetail = new UserDetailImpl();
            logger.info(">>>>>>>>>>>>>>{}", userDetail);
            userDetail.setPassword(authUser.getPassword());
            userDetail.setUsername(authUser.getName());
            userDetail.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(authUser.getAuth()));
            logger.info(">>>>>>>>>>>>>>" + userDetail.getAuthorities());
            userDetail.setEnabled(true);
            return userDetail;
        }
    }

}
