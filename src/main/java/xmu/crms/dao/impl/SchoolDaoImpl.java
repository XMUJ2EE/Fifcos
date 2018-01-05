package xmu.crms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.crms.dao.SchoolDao;
import xmu.crms.entity.School;
import xmu.crms.mapper.SchoolMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author mads
 * @date 2018/1/5 11:43
 */
@Repository("SchoolDao")
public class SchoolDaoImpl implements SchoolDao {

    @Autowired(required = false)
    SchoolMapper schoolMapper;

    @Override
    public List<School> listSchoolByCity(String city) {
        return schoolMapper.listSchoolByCity(city);
    }

    @Override
    public Integer insertSchool(School school) {
        return schoolMapper.insertSchool(school);
    }

    @Override
    public List<String> listProvince() {
        return schoolMapper.listProvince();
    }

    @Override
    public List<String> listCity(String province) {
        return schoolMapper.listCity(province);
    }

    @Override
    public School getSchoolBySchoolId(BigInteger schoolId) {
        return schoolMapper.getSchoolBySchoolId(schoolId);
    }
}
