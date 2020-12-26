package com.hiekn.metaboot;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
public class MetaBootApplicationTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    @Ignore
	public void test() {
        int a = 2;
        assertEquals(1, a);
    }

}
