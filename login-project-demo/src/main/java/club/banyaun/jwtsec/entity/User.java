package club.banyaun.jwtsec.entity;

import lombok.Data;

/**
 * @author : WangYB
 * @time: 2020/12/23  16:15
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String password;
    private String email;
    private String birth;
    private String phone;
    private Integer role;
    private Byte activated;


}
