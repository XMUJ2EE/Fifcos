package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.dao.UploadDao;
import xmu.crms.dao.UploadDao;
import xmu.crms.service.UploadService;

import java.math.BigInteger;
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired(required = false)
    private UploadDao uploadDao;

    @Override
    public void uploadRoster(BigInteger classId, String pathName) {

    }

    @Override
    public void uploadReport(BigInteger seminaId, BigInteger leaderId, String pathName) {
        uploadDao.updateReport(seminaId, leaderId,pathName);
    }

    @Override
    public void uploadAvater(BigInteger userId, String pathName) {
        uploadDao.updateAvatar(userId,pathName);
    }
}
