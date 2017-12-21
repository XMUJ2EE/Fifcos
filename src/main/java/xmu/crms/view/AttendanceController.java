package xmu.crms.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xmu.crms.entity.Attendance;
import xmu.crms.entity.User;
import xmu.crms.exception.LocationNotFoundException;
import xmu.crms.service.UserService;
import xmu.crms.view.vo.UserVO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 讨论课的签到状态Controller
 * @author mads
 * 备注： 由于标准组的Seminar部分不太明确，所以先直接返回字符串
 */

@Controller
@RequestMapping("/seminar")
public class AttendanceController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance",method = RequestMethod.GET)
    public ResponseEntity getStateByClassId(@PathVariable int seminarId, @PathVariable int classId){

        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/present",method = RequestMethod.GET)
    public ResponseEntity getNiceStateStudentList(@PathVariable int seminarId, @PathVariable int classId){
        List<UserVO> listAttdent = new ArrayList<UserVO>();
        try {
            List<User> listTotal = userService.listPresentStudent(BigInteger.valueOf(seminarId), BigInteger.valueOf(classId));
            for (int i=0; i<listTotal.size(); i++) {
                UserVO userVO = new UserVO(listTotal.get(i).getId(), listTotal.get(i).getName());
                listAttdent.add(userVO);
            }
        } catch (LocationNotFoundException e) {
            e.printStackTrace();
        }
        if (listAttdent.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }else {
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listAttdent);
        }
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/late",method = RequestMethod.GET)
    public ResponseEntity getLateStudentList(@PathVariable int seminarId, @PathVariable int classId){
        List<UserVO> listLate = new ArrayList<UserVO>();
        try {
            List<Attendance> listTotal = userService.listAttendanceById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId));
            for (int i=0; i<listTotal.size(); i++) {
                if (listTotal.get(i).getAttendanceStatus() == 1) {
                    UserVO userVO = new UserVO(listTotal.get(i).getStudent().getId(), listTotal.get(i).getStudent().getName());
                    listLate.add(userVO);
                }
            }
        } catch (LocationNotFoundException e) {
            e.printStackTrace();
        }
        if (listLate.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }else {
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listLate);
        }
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/absent",method = RequestMethod.GET)
    public ResponseEntity getAbsentStudentList(@PathVariable int seminarId, @PathVariable int classId){
        List<UserVO> listAbsent = new ArrayList<UserVO>();
        try {
            List<User> listTotal = userService.listAbsenceStudent(BigInteger.valueOf(seminarId), BigInteger.valueOf(classId));
            for (int i=0; i<listTotal.size(); i++) {
                    UserVO userVO = new UserVO(listTotal.get(i).getId(), listTotal.get(i).getName());
                    listAbsent.add(userVO);
            }
        } catch (LocationNotFoundException e) {
            e.printStackTrace();
        }
        if (listAbsent.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }else {
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listAbsent);
        }
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/{studentId}",method = RequestMethod.GET)
    public ResponseEntity callInRoll(@PathVariable int seminarId, @PathVariable int classId, @PathVariable int studentId){

        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
    }




}
