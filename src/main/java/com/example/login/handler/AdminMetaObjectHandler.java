package com.example.login.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wb_Lin
 * @create 2020-07-04 11:09
 */
@Component
public class AdminMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("begintime",new Date(),metaObject);
        this.setFieldValByName("status",Integer.valueOf("1"),metaObject);
        this.setFieldValByName("createtime",new Date(),metaObject);
        this.setFieldValByName("updatetime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatetime",new Date(),metaObject);
    }
}
