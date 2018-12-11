package com.hilox.demo.service;

import com.hilox.demo.base.GeometryAbstractService;
import com.hilox.demo.constant.GeometryTypeConstant;
import com.hilox.demo.query.GeometryCondition;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 圆形Service
 * Created by zhh on 2018/12/11 0011.
 */
@Service
public class CircularService extends GeometryAbstractService {

    /**
     * 自注册
     */
    public CircularService() {
        super(GeometryTypeConstant.CIRCULAR, CircularService.class);
    }

    @Override
    public Double getPerimeter(GeometryCondition condition) {
        BigDecimal radius = condition.getRadius();
        if (radius == null) {
            throw new IllegalArgumentException("圆形半径不能为空!");
        }
        // 2 * π * r
        double radiusDouble = radius.doubleValue();
        return 2 * Math.PI * radiusDouble;
    }

    @Override
    public Double getArea(GeometryCondition condition) {
        BigDecimal radius = condition.getRadius();
        if (radius == null) {
            throw new IllegalArgumentException("圆形半径不能为空!");
        }
        // π * r * r
        double radiusDouble = radius.doubleValue();
        return Math.PI * radiusDouble * radiusDouble;
    }

}
