package com.example.demo.model.requestDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginFromAmsDto {
    String username;
    String amsToken;
}
