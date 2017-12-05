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
        List<String> provinces = new ArrayList<String>();
        provinces.add("北京");
        provinces.add("天津");
        provinces.add("河北省");
        provinces.add("...");
        provinces.add("澳门特别行政区");
        return provinces;
    }

    @Override
    public List<String> getCity(String province) {
        List<String> city = new ArrayList<String>();
        city.add("北京");
        city.add("天津");
        city.add("河北省");
        city.add("...");
        city.add("澳门特别行政区");
        return city;
    }
}
