package com.hilox.demo.service;

import com.hilox.demo.base.GeometryAbstractService;
import com.hilox.demo.constant.GeometryTypeConstant;
import com.hilox.demo.query.GeometryCondition;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 正方形Service
 * Created by zhh on 2018/12/11 0011.
 */
@Service
public class SquareService extends GeometryAbstractService {

    /**
     * 自注册
     */
    public SquareService() {
        super(GeometryTypeConstant.SQUARE, SquareService.class);
    }

    @Override
    public BigDecimal getPerimeter(GeometryCondition condition) {
        BigDecimal side = condition.getSide();
        if (side == null) {
            throw new IllegalArgumentException("正方形边长不能为空!");
        }
        // 4 * side
        BigDecimal result = side.multiply(new BigDecimal(4));
        return result;
    }

    @Override
    public BigDecimal getArea(GeometryCondition condition) {
        BigDecimal side = condition.getSide();
        if (side == null) {
            throw new IllegalArgumentException("正方形边长不能为空!");
        }
        // side * side
        BigDecimal result = side.pow(2);
        return result;
    }

}
