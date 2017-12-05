package xmu.crms.service;

import org.springframework.stereotype.Service;
import xmu.crms.view.VO.AvatarVO;

@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public String uploadAvatar() {
        String url = "/avatar/3486.png";
        return url;
    }
}
