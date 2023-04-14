package com.copilot.modules.sys.service;

public interface IIndexService {
    /**
     * 登录
     *
     * @param username username
     * @param password password
     * @return token
     */
    String login(String username, String password);
}
