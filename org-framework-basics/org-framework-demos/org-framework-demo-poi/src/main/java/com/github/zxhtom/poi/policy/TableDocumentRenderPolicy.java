package com.github.zxhtom.poi.policy;

import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import com.deepoove.poi.xwpf.BodyContainer;
import com.deepoove.poi.xwpf.BodyContainerFactory;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;

/**
 * 数据库设计文档专用渲染策略
 */
public class TableDocumentRenderPolicy extends DynamicTableRenderPolicy {
    @Override
    public void render(XWPFTable table, Object data) {
        Helper.renderTableDocument(table,data);
    }

    public static class Helper extends MiniTableRenderPolicy.Helper{

        private static int getMaxColumFromData(List<RowRenderData> datas) {
            int maxColom = 0;
            for (RowRenderData obj : datas) {
                if (null == obj) continue;
                if (obj.size() > maxColom) maxColom = obj.size();
            }
            return maxColom;
        }
        public static void renderTableDocument(XWPFRun run, MiniTableRenderData tableData) {
            // 1.计算行和列
            int row = tableData.getRows().size(), col = 0;
            if (!tableData.isSetHeader()) {
                col = getMaxColumFromData(tableData.getRows());
            } else {
                row++;
                col = tableData.getHeader().size();
            }

            // 2.创建表格
            BodyContainer bodyContainer = BodyContainerFactory.getBodyContainer(run);
            XWPFTable table = bodyContainer.insertNewTable(run, row, col);
            TableTools.initBasicTable(table, col, tableData.getWidth(), tableData.getStyle());

            // 3.渲染数据
            int startRow = 0;
            if (tableData.isSetHeader()) MiniTableRenderPolicy.Helper.renderRow(table, startRow++, tableData.getHeader());
            for (RowRenderData data : tableData.getRows()) {
                MiniTableRenderPolicy.Helper.renderRow(table, startRow++, data);
            }
            renderTableDocument(table,tableData);
            run.getParagraph().setPageBreak(true);
        }
        public static void renderTableDocument(XWPFTable table, Object data) {
            if (null == data) return;

            MiniTableRenderData miniTableRenderData = (MiniTableRenderData) data;
            List<RowRenderData> rows = miniTableRenderData.getRows();
            for (int i = 0; i < table.getRows().size(); i++) {
                if (i < 3) {
                    TableTools.mergeCellsHorizonal(table, i, 1, 3);
                    table.getRows().get(i).getCell(0).setColor("b0b0b0");
                }
                if (i == 3) {
                    TableTools.mergeCellsHorizonal(table, i, 0, 3);
                    table.getRows().get(i).getCell(0).setColor("b0b0b0");
                }
            }
        }
    }
}
