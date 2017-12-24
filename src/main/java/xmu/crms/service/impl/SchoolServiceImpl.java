package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.entity.School;
import xmu.crms.mapper.SchoolMapper;
import xmu.crms.service.SchoolService;

import java.math.BigInteger;
import java.util.List;

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService{
	@Autowired(required = false)
	private SchoolMapper schoolMapper;
	
    public List<School> listSchoolByCity(String city){
    	
    	return schoolMapper.listSchoolByCity(city);
    }

   
    public BigInteger insertSchool(School school){

    	return schoolMapper.insertSchool(school);
    }

   
    public List<String> listProvince(){
    	return schoolMapper.listProvince();
    }

   
    public List<String> listCity(String province){
    	return schoolMapper.listCity(province);

    }

    
    public School getSchoolBySchoolId(BigInteger SchoolId){
    	return schoolMapper.getSchoolBySchoolId(SchoolId);

    }
}
