package xmu.crms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xmu.crms.dao.UploadDao;
import xmu.crms.mapper.UploadMapper;

import java.math.BigInteger;

/**
 * @author mads
 * @date 2018/1/4 22:29
 */
public class UploadDaoImpl implements UploadDao {

    @Autowired
    UploadMapper uploadMapper;

    @Override
    public Integer updateAvatar(BigInteger userId, String filename) {
        return uploadMapper.updateAvatar(userId, filename);
    }

    @Override
    public Integer updateReport(BigInteger seminarId, BigInteger leaderId, String filename) {
        return uploadMapper.updateReport(seminarId, leaderId, filename);
    }
}
