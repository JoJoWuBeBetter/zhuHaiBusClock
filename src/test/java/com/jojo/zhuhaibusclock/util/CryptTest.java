package com.jojo.zhuhaibusclock.util;

import org.junit.Test;

import static com.jojo.zhuhaibusclock.util.Crypt.wxDecrypt;
import static org.junit.Assert.*;

public class CryptTest {
    @Test
    public void wxDecryptTest() throws Exception {
        String sessionKey = "DFPtOTUfbm4O+fVDplkkNg==";
        String encryptedData = "psrh/DR30mqGrnyuIPO30y4slixIoo0SrzuYZcVyUTpR+w0gwrUvTq1rUuVMBh5RM9yCOj47pFB6WswAqNerAe3hOdGi8JFe7NPJo5JXLSDZ57d3q0SKakWLQH83G/3SeE14hUenAggtvQH98jxBbthnEfD/M2WaiL8BqUzj1bhLQTbOobhaz+ex2MVI0b1K4bjZF5qLPqXrvYSCkE+28nmL4ZIqI4Bzx4cy9JETDw7Lx8FmoS6r5JEViXBdb8P0G/cT6Tlbl8/1zCoaVt3bJ5X5XW+vWRCVgOtTwzoDP4k5UC78swiYxNEmj+Yo7sQugtH3edUGYX/LmPNGturjypjByHAjEZTrDUD3I93yMPVc4RiZ9jra7cK9+RwBhZtomc4UoiYgdFO6tygbp46emBjN2eE0LMA8YUOjte0maxnFT1LzA04YJQAImyRWCYzoqlzsnBQZNYDOav4uq7TnwA==";
        String iv = "I7xq2n04Z/5x+034GUWp4w==";
        System.out.println(wxDecrypt(sessionKey, encryptedData, iv));
    }
}