package com.hilox.demo.controller;

import com.hilox.demo.base.GeometryAbstractService;
import com.hilox.demo.base.GeometryServiceManager;
import com.hilox.demo.query.GeometryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 几何图形Controller
 * Created by zhh on 2018/12/11 0011.
 */
@Controller
public class GeometryController {

    @Autowired
    private GeometryServiceManager geometryServiceManager;

    /**
     * 获取几何图形周长
     * @param condition
     * @return
     */
    @RequestMapping("/getPerimeter")
    @ResponseBody
    public Object getPerimeter(@RequestBody GeometryCondition condition) {
        GeometryAbstractService service = geometryServiceManager.getServiceByName(condition.getType());
        return service.getPerimeter(condition);
    }

    /**
     * 获取几何图形面积
     * @param condition
     * @return
     */
    @RequestMapping("/getArea")
    @ResponseBody
    public Object getArea(@RequestBody GeometryCondition condition) {
        GeometryAbstractService service = geometryServiceManager.getServiceByName(condition.getType());
        return service.getArea(condition);
    }
}
