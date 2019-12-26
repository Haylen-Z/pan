package com.haylen.pan.service;

import com.haylen.pan.bo.OwnerDetails;
import com.haylen.pan.dto.OwnerParam;
import com.haylen.pan.entity.Owner;

/**
 * @author haylen
 * @date 2019-12-26
 */
public interface OwnerService {
    /**
     * 根据用户名获取用户
     * @param name 用户名
     * @return 用户
     */
    Owner getOwnerByUsername(String name);
    /**
     * 根据用户名获取用户细节
     * @param name 用户名
     * @return 用户细节
     */
    OwnerDetails getOwnerDetailsByUsername(String name);

    /**
     * 注册用户
     * @param ownerParam 注册参数
     * @return 成功注册用户，失败null.
     */
    Owner register(OwnerParam ownerParam);
}