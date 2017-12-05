package xmu.crms.service;

import xmu.crms.view.vo.AvatarVO;

public interface UploadService {
	
	/**
	 * 上传头像
	 *
	 * @return avatar地址
	 */
	AvatarVO uploadAvatar();
}
