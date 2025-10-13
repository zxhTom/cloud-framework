package com.github.zxhtom.oss.imports;

import com.github.zxhtom.oss.autoconfigure.OssAutoConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class OssMaltcloudImportor implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        List<String> classNames = new ArrayList(4);
        classNames.add(OssAutoConfiguration.class.getName());
        return (String[])classNames.toArray(new String[0]);
    }
}
