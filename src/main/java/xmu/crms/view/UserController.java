package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.User;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.UserService;
import xmu.crms.view.vo.UserUpdateVO;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Map;

@Controller

@RequestMapping("/")
@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/me", method = GET)
	@ResponseBody
	public ResponseEntity getCurrentUser() {
		BigInteger id = BigInteger.valueOf(1);
		try{
			User user = userService.getUserByUserId(id);
			if(user != null){
				return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(user);
			}else{
				return ResponseEntity.status(404).build();
			}
		}catch (UserNotFoundException e){
			System.out.println(e.getMessage());
			return ResponseEntity.status(400).build();
		}
	}
	
	@RequestMapping(value = "/me", method = PUT)
	public ResponseEntity updateCurrentUser(@RequestBody UserUpdateVO userUpdateVO) {
//	    User user = new User(123, Type.STUDENT,request.get("number"),
//                request.get("name"),request.get("phone"),request.get("email"),
//                Gender.MALE,null,request.get("title"),
//                null,null,request.get("avator"));
//		Boolean result = userService.updateUserById(123, user);
//		if(result){
		BigInteger id = BigInteger.valueOf(1);
		try {
			User user = userService.getUserByUserId(id);
			if (user == null) {
				return ResponseEntity.status(404).build();
			}else{
				int gender = userUpdateVO.getGender().equals("male") ? 0 : 1;

				User userUpdate = new User(id, null, null,
						null, userUpdateVO.getAvatar(), null,
						userUpdateVO.getName(), null, gender, null,
						null, null, null, userUpdateVO.getEmail());
				if (userUpdateVO.getTitle().equals("")) {
					userUpdate.setTitle(null);
				}
				else if(userUpdate.getTitle().equals("professor")){
					userUpdate.setTitle(1);
				}else{
					userUpdate.setTitle(0);
				}
				userService.updateUserByUserId(id, userUpdate);
				return ResponseEntity.status(204).body(null);
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
	}

}