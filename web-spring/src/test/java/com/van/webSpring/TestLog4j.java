package com.van.webSpring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog4j {
    
    public static void main(String[] args){
        Logger logger  =  LoggerFactory.getLogger(TestLog4j.class);
        logger.debug( " debug " );
        logger.error( " error " );
    }
}
