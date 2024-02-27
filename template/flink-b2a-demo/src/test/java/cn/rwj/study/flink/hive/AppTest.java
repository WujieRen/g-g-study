package cn.rwj.study.flink.hive;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest {


    @Test
    public void run(String[] args) {
        String param = "{\"apiInfo\":{\"url\":\"http://loclhost:8866/getUserHouse\",\"httpMethod\":\"POST\"},\"sourceInfo\":{\"dbName\":\"icbc\",\"tbName\":\"test_user\",\"tableField\":[{\"fieldName\":\"id\",\"fieldType\":\"String\"},{\"fieldName\":\"phone\",\"fieldType\":\"int\"},{\"fieldName\":\"name\",\"fieldType\":\"int\"}]},\"sinkInfo\":{\"dbName\":\"icbc\",\"tbName\":\"test_user_wide\",\"tableField\":[{\"fieldName\":\"id\",\"fieldType\":\"String\"},{\"fieldName\":\"phone\",\"fieldType\":\"int\"},{\"fieldName\":\"name\",\"fieldType\":\"string\"},{\"fieldName\":\"age\",\"fieldType\":\"int\"},{\"fieldName\":\"house\",\"fieldType\":\"string\"}]}}";
    }

}
