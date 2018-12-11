package com.hilox.demo.query;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 几何图形条件
 * Created by zhh on 2018/12/11 0011.
 */
@Data
public class GeometryCondition implements Serializable {

    private static final long serialVersionUID = -6981947017889724720L;

    /**
     * 边长
     */
    private BigDecimal side;

    /**
     * 半径
     */
    private BigDecimal radius;

    /**
     * 标记字段
     */
    private String type;
}
