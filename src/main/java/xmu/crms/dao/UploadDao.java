package xmu.crms.dao;

import java.math.BigInteger;

/**
 * @author mads
 * @date 2018/1/4 22:28
 */
public interface UploadDao {

    Integer updateAvatar(BigInteger userId, String filename);

    Integer updateReport(BigInteger seminarId, BigInteger leaderId, String filename);
}
