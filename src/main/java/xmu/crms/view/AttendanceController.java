package xmu.crms.view;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mads
 */

@Controller
@RequestMapping("/seminar")
public class AttendanceController {

    @RequestMapping("/{seminarId}/class/{classId}/attendance")
    public String getStateByClassId(@PathVariable int seminarId, @PathVariable int classId){
        String state = "{\n" +
                "  \"numPresent\": 40,\n" +
                "  \"numStudent\": 60,\n" +
                "  \"status\": \"calling\",\n" +
                "  \"group\": \"grouping\"\n" +
                "}";
        return state;
    }

    @RequestMapping("/{seminarId}/class/{classId}/attendance/present")
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

    @RequestMapping("/{seminarId}/class/{classId}/attendance/late")
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

    @RequestMapping("/{seminarId}/class/{classId}/attendance/absent")
    public ResponseEntity getAbsentStudentList(@PathVariable int seminarId, @PathVariable int classId){
        String studentList = "[\n" +
                "  {\n" +
                "    \"id\": 34,\n" +
                "    \"name\": \"张六\"\n" +
                "  }\n" +
                "]";
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(studentList);
    }

    @RequestMapping("/{seminarId}/class/{classId}/attendance/{studentId}")
    public ResponseEntity callInRoll(@PathVariable int seminarId, @PathVariable int classId, @PathVariable int studentId){
        String state = "{\n" +
                "  \"status\": \"late\"\n" +
                "}";
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(state);
    }




}
