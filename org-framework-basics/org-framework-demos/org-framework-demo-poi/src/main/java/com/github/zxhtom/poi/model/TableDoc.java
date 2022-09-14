package com.github.zxhtom.poi.model;

import com.deepoove.poi.data.MiniTableRenderData;
import lombok.Data;

import java.util.List;

@Data
public class TableDoc {
    private String tableName;
    private String tableId;
    private String tableComment;
    private List<MiniTableRenderData> tableData;
}
