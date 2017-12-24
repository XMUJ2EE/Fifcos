package xmu.crms.mapper;

import java.math.BigInteger;
import java.util.List;

import xmu.crms.entity.School;

public interface SchoolMapper {

	List<School> listSchoolByCity(String city);
	
	Boolean insertSchool(School school);
	
	List<String> listProvince();
	
	List<String> listCity(String province);
	
	School getSchoolBySchoolId(BigInteger SchoolId);
}
