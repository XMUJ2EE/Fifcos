package xmu.crms.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.School;
import xmu.crms.service.SchoolService;
import xmu.crms.view.vo.AddSchoolVO;

import javax.websocket.server.PathParam;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolService schoolService;

    @RequestMapping(method = GET)
    @ResponseBody
    public ResponseEntity getSchoolList(@RequestParam("city") String city) {
        List<School> schools = schoolService.listSchoolByCity(city);

        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(schools);
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public ResponseEntity addSchool(@RequestBody AddSchoolVO addSchoolVO) {

        School school = new School(null, addSchoolVO.getName(), addSchoolVO.getProvince(), addSchoolVO.getCity());
        BigInteger id = schoolService.insertSchool(school);
        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(id);
    }

    @RequestMapping(value = "/province", method = GET)
    @ResponseBody
    public ResponseEntity getProvince() {
        List<String> provinces = new ArrayList<String>();
        provinces = schoolService.listProvince();

        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(provinces);
    }
    @RequestMapping(value = "/city", method = GET)
    @ResponseBody
    public  ResponseEntity getCity(@RequestParam ("province") String province) {
        List<String> citys = new ArrayList<String>();
        citys = schoolService.listCity(province);

        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(citys);
    }
}
