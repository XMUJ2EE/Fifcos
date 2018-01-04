package xmu.crms.service;

import java.math.BigInteger;

/**
 * @author mads
 * @date 2018/1/4 22:27
 */
public interface UploadService {

    public void uploadRoster(BigInteger classId, String pathName);


    public void uploadReport(BigInteger seminaId, BigInteger leaderId, String pathName);


    public void uploadAvater(BigInteger userId, String pathName);
}
