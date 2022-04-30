package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte one = 64;
        short two = 256;
        int three = 512;
        long four = 1024L;
        double five = 1.01;
        float six = 2.02F;
        char seven = 'A';
        boolean eight = true;
        LOG.debug("Eight types - one : {}, two : {}, three : {}, four : {}, five : {}, six : {}, seven : {}, eight : {}",
                one, two, three, four, five, six, seven, eight);
    }
}