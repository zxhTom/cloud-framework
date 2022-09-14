package com.github.zxhtom.poi.policy;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.policy.*;
import com.deepoove.poi.render.RenderContext;
import com.deepoove.poi.template.ElementTemplate;
import com.deepoove.poi.util.StyleUtils;
import com.github.zxhtom.poi.model.TableDoc;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;

public class DemoPolicy extends ListRenderPolicy {
    @Override
    public void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template) {
        final RenderContext renderContext = new RenderContext(eleTemplate, data, template);
        XWPFRun run = renderContext.getRun();
        run.setText("", 0);
        TableDoc tableDoc = (TableDoc) data;
        List<MiniTableRenderData> list = tableDoc.getTableData();
        Integer index =0 ;
        do {
            XWPFParagraph paragraph = run.getDocument().createParagraph();
            Style style = new Style();
            style.setFontFamily("宋体");
            style.setFontSize(22);
            style.setBold(true);
            style.setItalic(true);
            StyleUtils.styleRun(run, style);
            run.setText(tableDoc.getTableName());
            run = paragraph.createRun();
            MiniTableRenderData miniTableRenderData = list.get(index);
//            MiniTableRenderPolicy.Helper.renderMiniTable(run, miniTableRenderData);
            TableDocumentRenderPolicy.Helper.renderTableDocument(run,miniTableRenderData);
            //最后一个分页不需要
            if (index == list.size()-1) {
                run.getParagraph().setPageBreak(false);
            }
        } while (++index < list.size());
    }
}
