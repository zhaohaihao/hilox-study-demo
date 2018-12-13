package com.hilox.demo.base;

import com.hilox.demo.query.GeometryCondition;

/**
 * 所有几何图形Service抽象父类
 * Created by Hilox on 2018/12/11 0011.
 */
public abstract class GeometryAbstractService extends GeometryServiceManager {

    /**
     * 默认构造方法
     */
    public GeometryAbstractService() {}

    /**
     * 子类自注册方法
     */
    public GeometryAbstractService(String name, Class<?> serviceName) {
        super(name, serviceName);
    }

    /**
     * 获取几何图形周长
     * @param condition
     * @return
     */
    public abstract Object getPerimeter(GeometryCondition condition);

    /**
     * 获取几何图形面积
     * @param condition
     * @return
     */
    public abstract Object getArea(GeometryCondition condition);
}
