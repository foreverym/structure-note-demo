package club.banyaun.jwtsec.entity;

import io.lettuce.core.StrAlgoArgs;
import lombok.Data;

/**
 * @author : WangYB
 * @time: 2020/12/24  14:00
 */
@Data
public class LoginDetail {

    private String name;
    private String password;
    private Byte activated;

}
