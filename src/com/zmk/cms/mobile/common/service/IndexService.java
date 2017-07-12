package com.zmk.cms.mobile.common.service;

import com.zmk.cms.mobile.common.bean.MobileBean;

public interface IndexService {
    public MobileBean login(String username, String password);

    public MobileBean logout(String tokenId, String username);
	public MobileBean getUserConfig(String tokenId);
}
