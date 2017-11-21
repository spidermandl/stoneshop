package org.goshop.users.service;

import com.oracle.tools.packager.Log;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * Created by Desmond on 07/11/2017.
 */
public class PasswdTest {
    @Test
    public void generatePassword() throws  Exception{
        String passwd = new SimpleHash("md5", "111111", ByteSource.Util.bytes("n6NvJ"), 1).toHex();
        Log.info(passwd);
    }
}
