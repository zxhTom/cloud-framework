package com.github.zxhtom.datasource.model;

import com.github.zxhtom.core.datasouce.PagedRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SortablePageParam extends PagedRequest {

    private List<SortingField> sortingFields;

}