package com.github.zxhtom.datasource.utils;

import com.github.zxhtom.datasource.constant.MybatisConstant;
import com.github.zxhtom.datasource.properties.MybatisLocaltionProperties;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.utils
 * @date 2021/8/24 15:08
 */
public class MapperUtils {
    private static MapperUtils util = new MapperUtils();

    public static MapperUtils getInstance(){
        return util;
    }


    /**
    * @author zxhtom
    * @Description 根据路径获取真实资源
    * @Date 15:16 2021/8/24
    * @Param 路径集合
    */
    public Resource[] getMapperLocaltions(MybatisLocaltionProperties properties) throws IOException {
        List<Resource> resourceList = new ArrayList<>();
        if (null!=properties&&null!=properties.getMapperLocations()&&properties.getMapperLocations().length>0) {
            for (String location : properties.getMapperLocations()) {
                resourceList.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources(location)));
            }
        }
        if (resourceList.size() == 0) {
            return new PathMatchingResourcePatternResolver().getResources(MybatisConstant.MAPPERLOCALTIONS[0]);
        }
        return (Resource[]) resourceList.toArray(new Resource[resourceList.size()]);
    }

    public String getMapperPackage(MybatisLocaltionProperties properties){
        if (null!=properties&&StringUtils.isNotEmpty(properties.getMapperPackage())) {
            return properties.getMapperPackage();
        }
        return MybatisConstant.MAPPERPACKAGE;
    }
}
