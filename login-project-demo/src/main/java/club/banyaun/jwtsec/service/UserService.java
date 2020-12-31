package club.banyaun.jwtsec.service;

import club.banyaun.jwtsec.entity.LoginDetail;
import club.banyaun.jwtsec.entity.User;

/**
 * @author : WangYB
 * @time: 2020/12/23  16:55
 */
public interface UserService {

    public void register(User user);

    public int updateToActive(Integer id);

    public LoginDetail queryLoginDetail(String name);

}
