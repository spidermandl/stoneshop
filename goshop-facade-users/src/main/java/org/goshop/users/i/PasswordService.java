package org.goshop.users.i;

/**
 * Created by Administrator on 2016/3/12.
 */
public interface PasswordService {
    String encryptPassword(String password, String salt);
}