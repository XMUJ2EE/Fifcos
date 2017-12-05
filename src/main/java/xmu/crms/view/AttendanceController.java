package xmu.crms.view;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 讨论课的签到状态Controller
 * @author mads
 * 备注： 由于标准组的Seminar部分不太明确，所以先直接返回字符串
 */

@Controller
@RequestMapping("/seminar")
public class AttendanceController {

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance",method = RequestMethod.GET)
    public ResponseEntity getStateByClassId(@PathVariable int seminarId, @PathVariable int classId){
        String state = "{\n" +
                "  \"numPresent\": 40,\n" +
                "  \"numStudent\": 60,\n" +
                "  \"status\": \"calling\",\n" +
                "  \"group\": \"grouping\"\n" +
                "}";
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(state);
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/present",method = RequestMethod.GET)
    public ResponseEntity getNiceStateStudentList(){
        String studentList = "[\n" +
                "  {\n" +
                "    \"id\": 2357,\n" +
                "    \"name\": \"张三\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 8232,\n" +
                "    \"name\": \"李四\"\n" +
                "  }\n" +
                "]";
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(studentList);
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/late",method = RequestMethod.GET)
    public ResponseEntity getLateStudentList(@PathVariable int seminarId, @PathVariable int classId){
        String studentList = "[\n" +
                "  {\n" +
                "    \"id\": 3412,\n" +
                "    \"name\": \"王五\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 5234,\n" +
                "    \"name\": \"王七九\"\n" +
                "  }\n" +
                "]";
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(studentList);
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/absent",method = RequestMethod.GET)
    public ResponseEntity getAbsentStudentList(@PathVariable int seminarId, @PathVariable int classId){
        String studentList = "[\n" +
                "  {\n" +
                "    \"id\": 34,\n" +
                "    \"name\": \"张六\"\n" +
                "  }\n" +
                "]";
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(studentList);
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}/attendance/{studentId}",method = RequestMethod.GET)
    public ResponseEntity callInRoll(@PathVariable int seminarId, @PathVariable int classId, @PathVariable int studentId){
        String state = "{\n" +
                "  \"status\": \"late\"\n" +
                "}";
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(state);
    }




}
