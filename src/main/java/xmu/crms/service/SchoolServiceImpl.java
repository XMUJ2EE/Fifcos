package xmu.crms.service;

import org.springframework.stereotype.Service;
import xmu.crms.entity.School;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Override
    public List<School> getSchoolList(String city) {
        School school1 = new School(32,"厦门大学","福建省","厦市门");
        School school2 = new School(37,"厦门软件学院","福建省","厦门市");

        List<School> schools = new ArrayList<School>();
        schools.add(school1);
        schools.add(school2);
        return schools;
    }

    @Override
    public Boolean addSchool(String name, String province, String city) {
        return true;
    }

    @Override
    public List<String> getProvince() {
        return null;
    }

    @Override
    public List<String> getCity(String province) {
        return null;
    }
}
