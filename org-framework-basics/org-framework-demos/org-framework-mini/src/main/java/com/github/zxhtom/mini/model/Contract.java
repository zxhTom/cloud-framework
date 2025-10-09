package com.github.zxhtom.mini.model;

import lombok.Data;

import java.util.Date;

/**
 * @author zxhtom
 * 10/6/25
 */
@Data
public class Contract {
    private Long id;
    private String companyName;
    private String positionName;
    private Double salary;
    private Date startDate;
    private Date endDate;
    private Boolean status;
    private String statusText;
}
