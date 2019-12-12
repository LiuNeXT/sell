package com.imooc.sell;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j   //加入lomlok的Slf4j,不需要在写Logger的成员变量
public class TestLogConfig {

    //在类上面加入，@Slf4j后，不需要写下面的成员变量
    //private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    @Test
    public  void  testLogLevel(){
        //等级由小到大
        log.trace(".....trace...");
        log.debug("....debug....");
        log.info("....info......");
        log.warn(".....warn.....");
        log.error("....error....");
        //----------------------------
        String name = "Alex";
        String password = "Hello@123";
        log.info("name={}   password={}",name,password);//使用{}来填充变量
    }

    @Test
    public  void testLogFile(){
        for (int i = 0; i < 5; i++) {
            testLogLevel();
        }
    }
}
