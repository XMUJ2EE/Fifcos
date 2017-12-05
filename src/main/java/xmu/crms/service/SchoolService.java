package xmu.crms.service;

import xmu.crms.entity.School;

import java.util.List;

public interface SchoolService {
    /**
     * 获取学校列表
     * @param city
     * @return 学校的List
     */
    List<School> getSchoolList(String city);

    /**
     * 添加学校
     * @param name
     * @param province
     * @param city
     * @return 是否成功
     */
    Boolean addSchool(String name, String province, String city);

    /**
     * 获取省份
     * @return 省份的List
     */
    List<String> getProvince();

    /**
     * 获取城市
     * @param province
     * @return 城市的List
     */
    List<String> getCity(String province);

}
