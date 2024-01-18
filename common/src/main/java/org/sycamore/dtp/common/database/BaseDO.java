package org.sycamore.dtp.common.database;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * THIS IS A CLASS
 *
 * @PROJECT_NAME: dtp
 * @CLASS_NAME: BaseDO
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/11 15:27
 */
@Data
@Accessors(chain = true)
public class BaseDO {
    private Long id;
    private Date createTime;
    private Date updateTime;
    private Integer delFlag;
}
