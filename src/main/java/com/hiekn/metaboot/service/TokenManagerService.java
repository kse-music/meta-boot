package com.hiekn.metaboot.service;


import com.hiekn.metaboot.bean.vo.TokenModel;

public interface TokenManagerService {

    /**
     * 创建一个 token 关联上指定用户
     * @param userId 指定用户的 id
     * @return 生成的 token
     */
    TokenModel createToken(long userId);

    /**
     * 检查 token 是否有效
     * @return 是否有效
     */
    boolean checkToken(TokenModel tokenModel);

    /**
     * 从字符串中解析 token
     * @return
     */
    TokenModel getToken(TokenModel tokenModel);

    /**
     * 清除 token
     * @param userId 登录用户的 id
     */
    void deleteToken(long userId);

}
