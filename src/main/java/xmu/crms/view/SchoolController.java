package xmu.crms.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.School;
import xmu.crms.service.CourseService;
import xmu.crms.service.CourseServiceImpl;
import xmu.crms.service.SchoolService;
import xmu.crms.service.SchoolServiceImpl;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolService schoolService = new SchoolServiceImpl();

    @RequestMapping(method = GET)
    @ResponseBody
    public ResponseEntity<List<School>> getSchoolList(@PathParam("city") String city) {

        List<School> schools = schoolService.getSchoolList(city);

        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(schools);
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public ResponseEntity addSchool(@RequestBody School school) {
        int schoolId = 38;
        if(schoolService.addSchool(school.getName(), school.getProvince(), school.getCity())) {
            return ResponseEntity.created(URI.create("/school")).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object() {
                public int id = schoolId;
            });
        }else {
            return ResponseEntity.status(409).body(null);
        }
    }

    @RequestMapping(value = "/province", method = GET)
    @ResponseBody
    public ResponseEntity<List<String>> getProvince() {
        List<String> provinces = new ArrayList<String>();
        provinces.add("北京");
        provinces.add("天津");
        provinces.add("福建省");
        provinces.add("浙江省");
        provinces.add("上海");

        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(provinces);
    }

    @RequestMapping(value = "/city", method = GET)
    @ResponseBody
    public  ResponseEntity<List<String>> getCity(@PathParam("province") String province) {
        List<String> provinces = new ArrayList<String>();
        provinces.add("北京");
        provinces.add("天津");
        provinces.add("福建省");
        provinces.add("浙江省");
        provinces.add("上海");
        if (provinces.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }else{
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(provinces);
        }
    }
}
