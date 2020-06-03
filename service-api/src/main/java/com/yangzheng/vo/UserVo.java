package com.yangzheng.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangzheng
 * @Description: //TODO
 * @Title: UserVo
 * @ProjectName service-rpc-kafka
 * @date 2020/6/3/003 15:59
 */
@Data
public class UserVo implements Serializable {
    private String name;
    private Integer age;
}
