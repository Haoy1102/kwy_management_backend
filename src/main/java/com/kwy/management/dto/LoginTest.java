package com.kwy.management.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author haoy
 * @description
 * @date 2023/7/5 16:56
 */
@Data
public class LoginTest implements Serializable {
    private String token;
}
