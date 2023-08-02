package com.hiekn.metaboot.test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class MetaBootApplicationTest {

    @Test
    @Ignore
	public void test() {
        int a = 2;
        assertEquals(1, a);
    }

}
