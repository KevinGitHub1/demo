package com.zmk.cms.mobile.common.service;

import com.zmk.cms.mobile.common.bean.Token;
import com.zmk.cms.mobile.common.util.UserConfigChange;

public interface TokenService {
    public Token createTokenForUser(String username);
    public boolean validateToken(String tokenId);
    public void removeToken(String tokenId);
}
