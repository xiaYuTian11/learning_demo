package com.example.uidgenerator;

import com.baidu.fsg.uid.UidGenerator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UidGeneratorApplication.class)
class UidGeneratorApplicationTests {
    @Autowired
    private UidGenerator uidGenerator;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 100000; i++) {
            System.out.println(uidGenerator.getUID());
        }
    }

}
