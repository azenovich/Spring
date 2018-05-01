package by.bsuir.recourse.controller;

import by.bsuir.recourse.configuration.security.Auth;
import by.bsuir.recourse.configuration.security.UserAuthDetails;
import by.bsuir.recourse.controller.exception.ControllerException;
import by.bsuir.recourse.entity.dto.PasswordChanging;
import by.bsuir.recourse.entity.dto.RegistrationDetails;
import by.bsuir.recourse.entity.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("api/users")
public interface UserController extends CrudController<User, Integer> {

    @GetMapping(value = "", params = "role")
    List<User> getUsersByRole(@RequestParam("role") User.Role role, Pageable pageable, @Auth UserAuthDetails authDetails);

    @GetMapping("/me")
    User getMyInfo(@Auth UserAuthDetails authDetails);

    @PostMapping("/register")
    void register(@RequestBody RegistrationDetails registrationDetails) throws ControllerException;

    @PostMapping("/logout")
    void logout(OAuth2Authentication principal) throws ControllerException;

    @PostMapping("/password/change")
    void changePassword(@RequestBody PasswordChanging passwordChanging, @Auth UserAuthDetails userAuthDetails) throws ControllerException;
}
