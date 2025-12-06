package com.example.nvs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")   // 👈 add this line
class NvsApplicationTests {

    @Test
    void contextLoads() {
    }
}
