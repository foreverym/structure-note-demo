package club.banyaun.jwtsec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : WangYB
 * @time: 2020/12/24  16:41
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @GetMapping("/doTask")
    public void doTask() {
        return;
    }

    @GetMapping("/doTask2")
    public void doTask2() {
        return;
    }

}
