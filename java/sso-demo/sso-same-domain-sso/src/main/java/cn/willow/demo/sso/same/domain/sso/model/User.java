package cn.willow.demo.sso.same.domain.sso.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User implements Serializable {
    private String username;
    private String password;
}
