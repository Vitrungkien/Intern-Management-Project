package me.jwt_spring.controllers;

import me.jwt_spring.ThrowException.UnauthorizedException;
import me.jwt_spring.models.Intern;
import me.jwt_spring.models.ResponseObject;
import me.jwt_spring.models.Users;
import me.jwt_spring.repository.InternRepository;
import me.jwt_spring.repository.UserRepository;
import me.jwt_spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intern")
@CrossOrigin("*")
public class InternController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InternRepository internRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String helloUserController(){
        return "Intern access level";
    }

    //Hiển thị danh sách tất cả user
    @GetMapping("/users")
    List<Users> getUserList() {
        return userRepository.findAll();
    }

    //Hiển thị danh sách tất cả intern
    @GetMapping("/getall")
    List<Intern> getInternList() {
        return internRepository.findAll();
    }

    //Tìm kiếm interns bằng từ khóa
    @GetMapping("/search")
    public List<Intern> searchInterns(@RequestParam("keyword") String keyword) {
        return internRepository.findByKeyword(keyword);
    }

    //sửa my profile
    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateUserProfile(@RequestBody Intern updatedUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // Lấy id user từ thông tin xác thực
        String userId = authentication.getName();

    // Lấy user hiện tại
        Intern currentUser = (Intern) authentication.getPrincipal();

    // Kiểm tra xem user hiện tại có phải là chủ sở hữu của bản cập nhật hay không
        if (currentUser.getUserId().equals(updatedUser.getUserId())) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "update Intern successfully", userService.updateIntern(updatedUser))
            );
        }

        throw new UnauthorizedException("You are not authorized to update this user's profile");
    }
}
