package de.ebay.recruitingh.csv.util;


import de.ebay.recruiting.csv.util.DateUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateUtilTest {

    @Test
    public void differenceInDaysOlder() {
        String date1 = "1985-03-09";
        String date2 = "1990-11-05";
        long days = DateUtil.getDifferenceInDays(date1, date2);
        boolean older = (days > 0) ? true : false;
        assertTrue(older);
    }

    @Test
    public void differenceInDaysYounger() {
        String date1 = "1990-11-05";
        String date2 = "1977-12-01";
        long days = DateUtil.getDifferenceInDays(date1, date2);
        boolean older = (days > 0) ? true : false;
        assertFalse(older);
    }

    @Test
    public void differenceInDaysSame() {
        String date1 = "1990-11-05";
        String date2 = "1990-11-05";
        long days = DateUtil.getDifferenceInDays(date1, date2);
        assertEquals(days, 0);
    }

    @Test
    public void differenceInDaysPositive() {
        String date1 = "1990-11-01";
        String date2 = "1990-11-05";
        long days = DateUtil.getDifferenceInDays(date1, date2);
        assertEquals(days, 4);
    }

    @Test
    public void differenceInDaysNegative() {
        String date1 = "1990-11-05";
        String date2 = "1990-11-01";
        long days = DateUtil.getDifferenceInDays(date1, date2);
        assertEquals(days, -4);
    }



}
