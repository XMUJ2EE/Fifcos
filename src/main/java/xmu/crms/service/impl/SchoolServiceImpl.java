package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.dao.SchoolDao;
import xmu.crms.entity.School;
import xmu.crms.mapper.SchoolMapper;
import xmu.crms.service.SchoolService;

import java.math.BigInteger;
import java.util.List;

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService{
	@Autowired
	private SchoolDao schoolDao;

	@Override
    public List<School> listSchoolByCity(String city){
    	
    	return schoolDao.listSchoolByCity(city);
    }

    @Override
    public BigInteger insertSchool(School school){
        schoolDao.insertSchool(school);
    	return school.getId();
    }

    @Override
    public List<String> listProvince(){
    	return schoolDao.listProvince();
    }

    @Override
    public List<String> listCity(String province){
    	return schoolDao.listCity(province);

    }

    @Override
    public School getSchoolBySchoolId(BigInteger SchoolId){
    	return schoolDao.getSchoolBySchoolId(SchoolId);

    }
}
