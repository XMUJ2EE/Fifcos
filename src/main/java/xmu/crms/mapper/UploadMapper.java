package xmu.crms.mapper;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public interface UploadMapper {
    Integer updateAvatar(BigInteger userId, String filename);

    Integer updateReport(BigInteger seminarId, BigInteger leaderId, String filename);
}
