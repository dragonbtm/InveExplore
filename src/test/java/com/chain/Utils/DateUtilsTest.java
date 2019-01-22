package com.chain.Utils;

import com.chain.common.utils.DateUtils;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

/**
 * @Author: zz
 * @Description:
 * @Date: 下午 3:15 2019/1/22 0022
 * @Modified By
 */
public class DateUtilsTest {

    @Test
    public void dateFormat() {
        Date date = new Date();
        String daaa = Instant.now().toString();
        date = DateUtils.stringToDate(DateUtils.format(date, "yyyy-MM-dd"),"yyyy-MM-dd");
        System.out.println(date);
    }
}
