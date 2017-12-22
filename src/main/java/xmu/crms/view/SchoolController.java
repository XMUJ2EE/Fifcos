package xmu.crms.view;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/school")
public class SchoolController {
   // @Autowired
  //  SchoolService schoolService = new SchoolServiceImpl();

    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(method = GET)
    @ResponseBody
    public ResponseEntity getSchoolList(@PathParam("city") String city) {

//        List<School> schools = schoolService.getSchoolList(city);
//
//        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(schools);
        String school = "[\n" +
                "  {\n" +
                "    \"id\": 32,\n" +
                "    \"name\": \"厦门大学\",\n" +
                "    \"province\": \"福建\",\n" +
                "    \"city\": \"厦门\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 37,\n" +
                "    \"name\": \"厦门软件学院\",\n" +
                "    \"province\": \"福建\",\n" +
                "    \"city\": \"厦门\"\n" +
                "  }\n" +
                "]";
        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(school);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(method = POST)
    @ResponseBody
    public ResponseEntity addSchool() {
//        int schoolId = 38;
////        if (schoolService.addSchool("a", "b", "c")){
////            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(schoolId);
////        }else{
////            return ResponseEntity.status(409).body(null);
////        }
////
////        /*if(schoolService.addSchool(school.getName(), school.getProvince(), school.getCity())) {
////            return ResponseEntity.created(URI.create("/school")).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object() {
////                public int id = schoolId;
////            });
////        }else {
////            return ResponseEntity.status(409).body(null);
////        }*/
        String id = "{\n" +
                "  \"id\": 38\n" +
                "}";
        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(id);
    }

    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/province", method = GET)
    @ResponseBody
    public ResponseEntity getProvince() {

//        List<String> provinces = new ArrayList<String>();
//        provinces = schoolService.getProvince();
//
//        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(provinces);
        String province = "[\n" +
                "  \"北京\",\n" +
                "  \"天津\",\n" +
                "  \"河北省\",\n" +
                "  \"……\",\n" +
                "  \"澳门特别行政区\"\n" +
                "]";
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(province);
    }

    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/city", method = GET)
    @ResponseBody
    public  ResponseEntity getCity(@PathParam("province") String province) {
//        List<String> city = new ArrayList<String>();
//
//        city = schoolService.getCity(province);
//        if (city.isEmpty()){
//            return ResponseEntity.status(404).body(null);
//        }else{
//            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(city);
//        }
        String city = "[\n" +
                "  \"北京\",\n" +
                "  \"天津\",\n" +
                "  \"河北省\",\n" +
                "  \"……\",\n" +
                "  \"澳门特别行政区\"\n" +
                "]";
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(city);
    }
}
