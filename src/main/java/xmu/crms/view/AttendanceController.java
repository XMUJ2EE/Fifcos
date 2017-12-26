package xmu.crms.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.service.ClassService;
import xmu.crms.service.SeminarService;
import xmu.crms.service.UserService;
import xmu.crms.view.vo.AttendanceVO;
import xmu.crms.view.vo.LocationVO;
import xmu.crms.view.vo.UserVO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 讨论课的签到状态Controller
 * @author wang
 */

@Controller
@RequestMapping("/seminar")
public class AttendanceController {

    @Autowired
    UserService userService;
    @Autowired
    ClassService classService;
    @Autowired
    SeminarService seminarService;

    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance",method = RequestMethod.POST)
    public ResponseEntity invokeCallInRoll(@PathVariable BigInteger seminarId,
                                           @PathVariable BigInteger classId,
                                           @RequestBody LocationVO location){
        try{
            ClassInfo classInfo = classService.getClassByClassId(classId);
            Seminar seminar = seminarService.getSeminarBySeminarId(seminarId);
            Location locationNew = new Location(location, classInfo, seminar);
            classService.callInRollById(locationNew);
            return ResponseEntity.status(204).build();
        }catch (ClazzNotFoundException e){
            return ResponseEntity.status(404).build();
        }catch (SeminarNotFoundException e){
            return ResponseEntity.status(404).build();
        }
    }

    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance",method = RequestMethod.GET)
    public ResponseEntity getStateByClassId(@PathVariable int seminarId, @PathVariable int classId){
        try {
            Location location = classService.getCallStatusById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId));
            List<Attendance> list = userService.listAttendanceById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId));
            int numPresent = list.size();
            int numStudent = userService.listUserByClassId(BigInteger.valueOf(classId), "", "").size();
            AttendanceVO attendanceVO = new AttendanceVO(location, numPresent, numStudent);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(attendanceVO);
        } catch (LocationNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        } catch (SeminarNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        } catch (ClazzNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }

    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/present",method = RequestMethod.GET)
    public ResponseEntity getNiceStateStudentList(@PathVariable int seminarId, @PathVariable int classId){
        List<UserVO> listAttdent = new ArrayList<UserVO>();
        try {
            List<User> listTotal = userService.listPresentStudent(BigInteger.valueOf(seminarId), BigInteger.valueOf(classId));
            for (User aListTotal : listTotal) {
                UserVO userVO = new UserVO(aListTotal.getId(), aListTotal.getName());
                listAttdent.add(userVO);
            }
        } catch (LocationNotFoundException e) {
            e.printStackTrace();
        }
        if (listAttdent.isEmpty()) {
            return ResponseEntity.status(404).build();
        }else {
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listAttdent);
        }
    }

    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/late",method = RequestMethod.GET)
    public ResponseEntity getLateStudentList(@PathVariable int seminarId, @PathVariable int classId){
        List<UserVO> listLate = new ArrayList<UserVO>();
        try {
            List<Attendance> listTotal = userService.listAttendanceById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId));
            for (Attendance aListTotal : listTotal) {
                if (aListTotal.getAttendanceStatus() == 1) {
                    UserVO userVO = new UserVO(aListTotal.getStudent().getId(), aListTotal.getStudent().getName());
                    listLate.add(userVO);
                }
            }
        } catch (LocationNotFoundException e) {
            e.printStackTrace();
        }
        if (listLate.isEmpty()) {
            return ResponseEntity.status(404).build();
        }else {
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listLate);
        }
    }

    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/absent",method = RequestMethod.GET)
    public ResponseEntity getAbsentStudentList(@PathVariable int seminarId, @PathVariable int classId){
        List<UserVO> listAbsent = new ArrayList<UserVO>();
        try {
            List<User> listTotal = userService.listAbsenceStudent(BigInteger.valueOf(seminarId), BigInteger.valueOf(classId));
            for (User aListTotal : listTotal) {
                UserVO userVO = new UserVO(aListTotal.getId(), aListTotal.getName());
                listAbsent.add(userVO);
            }
        } catch (LocationNotFoundException e) {
            e.printStackTrace();
        }
        if (listAbsent.isEmpty()) {
            return ResponseEntity.status(404).build();
        }else {
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listAbsent);
        }
    }
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/{studentId}",method = RequestMethod.GET)
    public ResponseEntity callInRoll(@PathVariable int seminarId, @PathVariable int classId, @PathVariable int studentId,
                                     @RequestBody LocationVO locationVO) {
        List<Attendance> list = new ArrayList<Attendance>();
        try {
            User user = userService.getUserByUserId(BigInteger.valueOf(studentId));
            int id = userService.insertAttendanceById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId),
                    BigInteger.valueOf(studentId), locationVO.getLongitude(), locationVO.getLatitude()).intValue();
            list = userService.listAttendanceById(BigInteger.valueOf(classId), BigInteger.valueOf(seminarId));
            Attendance attendance = new Attendance();
            for (Attendance aList : list) {
                if (aList.getId().intValue() == id) {
                    attendance = aList;
                    break;
                }
            }
            String attend = "";
            if (attendance.getAttendanceStatus() == 0) {
                attend = "present";
            }
            else if (attendance.getAttendanceStatus() == 1) {
                attend = "late";
            }else {
                attend = "absence";
            }
            Map<String, String> status = new HashMap<String, String>();
            status.put("status", attend);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(status);
        } catch (LocationNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        } catch (InvalidOperationException e) {
            e.printStackTrace();
            return ResponseEntity.status(403).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }
}
