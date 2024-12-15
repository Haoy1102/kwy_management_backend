package com.kwy.management.controller;

import com.kwy.management.comon.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: DemoController
 * Package: com.kwy.management.controller
 * Description:
 *
 * @Author haoy
 * @Create 2024/12/16 10:19
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping
public class DemoController {
    @GetMapping("/hello")
    public R<String> hello(){
        return R.success("hello");
    }
}
