package com.hilox.demo.base;

import com.hilox.demo.util.ApplicationContextHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 几何图形Service服务管理类
 * Created by Hilox on 2018/12/11 0011.
 */
@Service
public class GeometryServiceManager {

    /**
     * 管理实现类
     */
    private static Map<String, Class<?>> initialCategories = new ConcurrentHashMap<>();

    private static Map<String, GeometryAbstractService> categoryMap = new ConcurrentHashMap<>();

    /**
     * 默认构造方法
     */
    public GeometryServiceManager() {}

    /**
     * 子类自注册方法
     */
    public GeometryServiceManager(String name, Class<?> serviceName) {
        initialCategories.put(name, serviceName);
    }

    /**
     * 根据传入标记字段值获取对应的几何图形服务
     * @param name
     * @return
     */
    public GeometryAbstractService getServiceByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Type不能为空!");
        }

        // 扫描预置的实现类
        scanForService();

        if (categoryMap.containsKey(name)) {
            return categoryMap.get(name);
        } else {
            throw new IllegalArgumentException("对应几何图形服务未注册!");
        }
    }

    /**
     * 扫描几何图形服务注册实现类
     * 为确保获取正确服务, 需要调用scanForService方可正确注册
     */
    private void scanForService() {
        initialCategories.forEach((key, value) -> registryService(key, (GeometryAbstractService) ApplicationContextHelper.getApplicationContext().getBean(value), true));
    }

    /**
     * 几何图形服务注册方法
     * 新增几何图形服务方法, 则将其注入到集合中
     * @param name
     * @param geometryAbstractService
     * @param replace 同名是否替换现有的服务, 默认为false：不替换
     */
    private void registryService(String name, GeometryAbstractService geometryAbstractService, boolean replace) {
        if (!categoryMap.containsKey(name) || replace) {
            categoryMap.put(name, geometryAbstractService);
        }
    }

    /**
     * 根据名称卸载几何图形服务方法
     * @param name
     */
    private void unRegistryService(String name) {
        if (categoryMap.containsKey(name)) {
            categoryMap.remove(name);
        }
    }

    /**
     * 根据方法卸载几何图形服务方法
     */
    private void unRegistryService(GeometryAbstractService geometryAbstractService) {
        if (categoryMap.containsValue(geometryAbstractService)) {
            categoryMap.remove(geometryAbstractService);
        }
    }
}
