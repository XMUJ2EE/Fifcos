package xmu.crms.dao;

import xmu.crms.entity.School;

import java.math.BigInteger;
import java.util.List;

/**
 * @author mads
 * @date 2018/1/5 11:43
 */
public interface SchoolDao {

    /**
     * 获得学校
     * @param city
     * @return
     */
    List<School> listSchoolByCity(String city);

    /**
     * 新建学校
     * @param school
     * @return
     */
    Integer insertSchool(School school);

    /**
     * 列出省份
     * @return
     */
    List<String> listProvince();

    /**
     * 列出城市
     * @param province
     * @return
     */
    List<String> listCity(String province);

    /**
     * 获取学校
     * @param schoolId
     * @return
     */
    School getSchoolBySchoolId(BigInteger schoolId);
}
