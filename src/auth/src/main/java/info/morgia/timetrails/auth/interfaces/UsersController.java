package info.morgia.timetrails.auth.interfaces;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {

    @GetMapping("/user-detail")
    public UserDetailResult userDetail() {
        log.info("inside GET /users/user-detail");
        return new UserDetailResult(1,"demo user from service");
    }
}
