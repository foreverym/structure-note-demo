package club.banyaun.jwtsec.dao;

import club.banyaun.jwtsec.entity.ExtAuthUser;
import club.banyaun.jwtsec.entity.LoginDetail;
import club.banyaun.jwtsec.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author : WangYB
 * @time: 2020/12/23  18:26
 */
@Mapper
@Component
public interface UserMapper {

    public int insertUser(@Param("user") User user);

    public int updateToActive(Integer id);

    public LoginDetail queryLoginDetail(String name);

    public ExtAuthUser getAuthUser(String name);

}
