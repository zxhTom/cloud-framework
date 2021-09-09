package com.github.zxhtom.datasource.typeHandler;

import com.github.zxhtom.core.enums.BaseEnum;
import com.github.zxhtom.core.properties.EnumProperties;
import com.github.zxhtom.core.utils.EnumUtils;
import com.github.zxhtom.core.utils.GenericUtils;
import com.github.zxhtom.web.context.ApplicationContextUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.datasource.typeHandler
 * @date 2021/9/9 10:38
 */
@MappedTypes(BaseEnum.class)
public class BaseEnumTypeHandler<T extends BaseEnum> extends BaseTypeHandler<T> {

    private final Class<T> type;

    public BaseEnumTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BaseEnum baseEnum, JdbcType jdbcType) throws SQLException {
        EnumProperties enumProperties = ApplicationContextUtil.getBean(EnumProperties.class);
        if (enumProperties.isSource()) {
            preparedStatement.setString(i,baseEnum.toString());
        }else{
            preparedStatement.setString(i,baseEnum.getCode().toString());
        }
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return (T) EnumUtils.valueOf(resultSet.getString(s), type);
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return (T) EnumUtils.valueOf(resultSet.getString(i),type);
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return (T) EnumUtils.valueOf(callableStatement.getString(i),type);
    }
}
