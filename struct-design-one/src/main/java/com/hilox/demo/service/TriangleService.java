package com.hilox.demo.service;

import com.hilox.demo.base.GeometryAbstractService;
import com.hilox.demo.constant.GeometryTypeConstant;
import com.hilox.demo.query.GeometryCondition;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 全等三角形Service
 * Created by zhh on 2018/12/11 0011.
 */
@Service
public class TriangleService extends GeometryAbstractService {

    /**
     * 自注册
     */
    public TriangleService() {
        super(GeometryTypeConstant.TRIANGLE, TriangleService.class);
    }

    @Override
    public BigDecimal getPerimeter(GeometryCondition condition) {
        BigDecimal side = condition.getSide();
        if (side == null) {
            throw new IllegalArgumentException("全等三角形边长不能为空!");
        }
        // 3 * side
        BigDecimal result = side.multiply(new BigDecimal(3));
        return result;
    }

    @Override
    public Double getArea(GeometryCondition condition) {
        BigDecimal side = condition.getSide();
        if (side == null) {
            throw new IllegalArgumentException("全等三角形边长不能为空!");
        }

        // 30°
        double radians = Math.toRadians(30);
        // 计算cos30°
        double cos = Math.cos(radians);
        double sideDouble = side.doubleValue();
        // 高
        double high = sideDouble * cos;
        return high * sideDouble / 2;
    }

}
