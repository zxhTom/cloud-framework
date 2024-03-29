package com.github.zxhtom.poi;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.util.BytePictureUtils;
import com.github.zxhtom.poi.model.TableDoc;
import com.github.zxhtom.poi.policy.DemoPolicy;
import com.github.zxhtom.poi.utils.ImageUtil;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;

import java.io.*;
import java.util.*;

public class SimpleTest {

    public void init(String name, Map<String,Object> data) {
        init(name,data,null);
    }
    public void init(String name, Map<String,Object> data,Configure configure) {
        String fileName = name+".docx";
        InputStream in = this.getClass().getResourceAsStream("/"+fileName);
        XWPFTemplate template = XWPFTemplate.compile(in,configure).render(data);
        String s = this.getClass().getResource("/").getPath();
        File file = new File(s+File.separator+name+"2.docx");
        try {
            FileOutputStream out = new FileOutputStream(file);
            template.write(out);
            out.close();
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void helloWorld() throws IOException {
        String name = "template";
        String fileName = name+".docx";
        InputStream in = this.getClass().getResourceAsStream("/"+fileName);
        XWPFTemplate template = XWPFTemplate.compile(in).render(
                new HashMap<String, Object>(){{
                    put("title", "hello world , i am zxhtom");
                }});
        String s = this.getClass().getResource("/").getPath();
        File file = new File(s+File.separator+name+"2.docx");
        FileOutputStream out = new FileOutputStream(file);
        template.write(out);
        out.close();
        template.close();
    }


    /**多样式文本*/
    @Test
    public void moreStringTest() {
        String name = "moreAnalise";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zxhtom");
        TextRenderData zxhtoms = new TextRenderData("000000", "zxhtoms");
        Style style = new Style();
        style.setColor("00FF00");
        style.setHighlightColor(STHighlightColor.Enum.forString("green"));
        zxhtoms.setStyle(style);
        map.put("author", zxhtoms);
        // 超链接
        map.put("link",
                new HyperLinkTextRenderData("website", "https://zxhtom.oschina.io/"));
        // 锚点
        map.put("anchor",
                new HyperLinkTextRenderData("anchortxt", "anchor:zxhtom"));
        init(name,map);
    }

    @Test
    public void pictureTest() throws FileNotFoundException {
        String name = "picture";
        Map<String, Object> map = new HashMap<>();
        // 本地图片
        map.put("local", new PictureRenderData(80, 100, this.getClass().getResource("/local.png").getPath()));

        // 图片流
        map.put("localbyte", new PictureRenderData(80, 100, ".png", this.getClass().getResourceAsStream("/input.png")));

        // 网络图片(注意网络耗时对系统可能的性能影响)
        map.put("urlpicture", new PictureRenderData(50, 50, ".png", BytePictureUtils.getUrlBufferedImage("http://deepoove.com/images/icecream.png")));

        // java 图片
        map.put("bufferimage", new PictureRenderData(80, 100, ".png", ImageUtil.getDefault(50,50,1)));
        init(name,map);
    }


    @Test
    public void tableTest() {
        String name = "table";
        Map<String, Object> map = new HashMap<>();
        //表头
        RowRenderData header = RowRenderData.build(new TextRenderData("99246f", "姓名"), new TextRenderData("99246f", "学历"));
        //数据体
        RowRenderData row0 = RowRenderData.build("张三", "研究生");
        RowRenderData row1 = RowRenderData.build("李四", "博士");
        map.put("table", new MiniTableRenderData(header, Arrays.asList(row0, row1)));
        init(name,map);
    }


    @Test
    public void seriListTest() {
        String name = "serlist";
        Map<String, Object> map = new HashMap<>();
        TextRenderData iphone = new TextRenderData("iphone");
        TextRenderData macbook = new TextRenderData("macbook");
        TextRenderData ipad = new TextRenderData("ipad");
        TextRenderData ipod = new TextRenderData("ipod");
        TextRenderData watch = new TextRenderData("watch");
        List<TextRenderData> textRenderData = Arrays.asList(iphone, macbook, ipad, ipod, watch);
        map.put("list", new NumbericRenderData(NumbericRenderData.FMT_DECIMAL,textRenderData));
        init(name,map);
    }


    @Test
    public void blockTest() {
        String name = "block";
        Map<String, Object> map = new HashMap<>();
        Map<String, String> userMap = new HashMap<>();
        List<Map<String, String>> list = new ArrayList<>();
        list.add(new HashMap<String, String>(){
            {
                put("name", "zhangsan");
                put("age", "18");
            }
        });
        list.add(new HashMap<String, String>(){
            {
                put("name", "lisi");
                put("age", "20");
            }
        });

        map.put("none", null);
        map.put("show", new HashMap<String,String>(){
            {
                put("appleProduction", "macbook pro m1 pro");
            }
        });
        map.put("list", list);
        init(name,map);
    }

    @Test
    public void mergeTest() {
        String name = "parent";
        List<Map<String, Object>> subList = new ArrayList<>();
        subList.add(new HashMap<String, Object>() {
            {
                put("address", "中国");
                put("fav", "上网");
            }
        });
        Map<String, Object> map = new HashMap<>();
        map.put("child", new DocxRenderData(this.getClass().getResourceAsStream("/child.docx"), subList));
        init(name,map);
    }

    @Test
    public void suitPictureTest() {
        String name = "citepicture";
        Map<String, Object> map = new HashMap<>();
        map.put("img", new PictureRenderData(0, 0, ".png", this.getClass().getResourceAsStream("/input.png")));
        init(name,map);
    }

    @Test
    public void splitPageTest() {
        //分页符测试
        String name = "splitpage";
        Map<String, Object> map = new HashMap<>();
        TableDoc doc = new TableDoc();
        doc.setTableId("USER");
        doc.setTableName("用户表");
        doc.setTableComment("记录用户信息");
        //数据体
        RowRenderData row0 = RowRenderData.build("中文名称", doc.getTableName());
        RowRenderData row1 = RowRenderData.build("英文名称", doc.getTableId());
        RowRenderData row2 = RowRenderData.build("描述", doc.getTableComment());
        RowRenderData row3 = RowRenderData.build("结构信息");
        RowRenderData row4 = RowRenderData.build("字段名", "类型","默认值","说明");
        RowRenderData row5 = RowRenderData.build("ID_", "BIGINT(19)","","主键ID");
        map.put("d1", new MiniTableRenderData(Arrays.asList(row0,row1,row1,row1,row0)));
        map.put("d2", new MiniTableRenderData(Arrays.asList(row0,row1,row1,row1,row0)));
        List<MiniTableRenderData> list = new ArrayList<>();
        list.add(new MiniTableRenderData(Arrays.asList(row0,row1,row2,row3,row4,row5)));
        list.add(new MiniTableRenderData(Arrays.asList(row0,row1,row2,row3,row4,row5)));
        doc.setTableData(list);
        map.put("tlist", doc);
        Configure config = Configure.newBuilder()
                .bind("tlist", new DemoPolicy())
                .build();
        init(name,map,config);
    }

    @Test
    public void nest2Test() {
        //分页符测试
        String name = "nestsplit";
        Map<String, Object> map = new HashMap<>();
        TableDoc doc = new TableDoc();
        doc.setTableId("USER");
        doc.setTableName("用户表");
        doc.setTableComment("记录用户信息");
        //数据体
        RowRenderData row0 = RowRenderData.build("中文名称", doc.getTableName());
        RowRenderData row1 = RowRenderData.build("英文名称", doc.getTableId());
        RowRenderData row2 = RowRenderData.build("描述", doc.getTableComment());
        RowRenderData row3 = RowRenderData.build("结构信息");
        RowRenderData row4 = RowRenderData.build("字段名", "类型","默认值","说明");
        RowRenderData row5 = RowRenderData.build("ID_", "BIGINT(19)","","主键ID");
        map.put("d1", new MiniTableRenderData(Arrays.asList(row0,row1,row1,row1,row0)));
        map.put("d2", new MiniTableRenderData(Arrays.asList(row0,row1,row1,row1,row0)));
        List<MiniTableRenderData> list = new ArrayList<>();
        list.add(new MiniTableRenderData(Arrays.asList(row0,row1,row2,row3,row4,row5)));
        list.add(new MiniTableRenderData(Arrays.asList(row0,row1,row2,row3,row4,row5)));
        doc.setTableData(list);
        List<Map<String,Object>> rrl = new ArrayList<>();
        Map<String, Object> ttd = new HashMap<>();
        ttd.put("tlist", doc);
        rrl.add(ttd);
        map.put("nest", new DocxRenderData(this.getClass().getResourceAsStream("/splitpage.docx"),rrl));
        Configure config = Configure.newBuilder()
                .bind("tlist", new DemoPolicy())
                .build();
        init(name,map,config);
    }
}
