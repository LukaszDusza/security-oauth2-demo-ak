package akademia.controller;

import akademia.model.UserInfo;
import akademia.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UserInfoController {

    private UserInfoService service;

    public UserInfoController(UserInfoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<UserInfo> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/users")
    public UserInfo addNewUser(@RequestBody UserInfo userInfo) {
        return service.addNewUserInfo(userInfo);
    }
}
