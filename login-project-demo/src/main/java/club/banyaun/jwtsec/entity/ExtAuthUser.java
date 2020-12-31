package club.banyaun.jwtsec.entity;

import lombok.Data;

/**
 * @author : WangYB
 * @time: 2020/12/24  15:52
 */
@Data
public class ExtAuthUser {

    private String name;
    private String password;
    private String auth;

}
