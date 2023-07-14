package com.kwy.management.utils.Excles;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.kwy.management.entity.ExcleData.DemoData;
import com.kwy.management.entity.ExcleData.FillData;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author haoy
 * @description
 * @date 2023/7/13 13:58
 */

public class FillTest {
    /**
     * 最简单的填充
     *
     * @since 2.1.1
     */
    @Test
    public void simpleFill() {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        String templateFileName = "src/main/resources/stastic/formDemo/demo.xlsx";
        String fileName = "src/main/resources/stastic/simpleFill" + System.currentTimeMillis() + ".xlsx";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        FillData fillData = new FillData();
        fillData.setName("张三");
        fillData.setNumber(5.2);
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(fillData);

//        // 方案2 根据Map填充
//        fileName = TestFileUtil.getPath() + "simpleFill" + System.currentTimeMillis() + ".xlsx";
//        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
//        Map<String, Object> map = MapUtils.newHashMap();
//        map.put("name", "张三");
//        map.put("number", 5.2);
//        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(map);
    }

    @Test
    public void listFill() {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        String templateFileName = "src/main/resources/stastic/formDemo/order.xlsx";
        String fileName = "src/main/resources/stastic/simpleFill" + System.currentTimeMillis() + ".xlsx";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭

        // 这里 会填充到第一个sheet， 然后文件流会自动关闭

        // 方案1
        try (ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
            // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
            // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
            // 如果数据量大 list不是最后一行 参照下一个

            ArrayList<DemoData> list = new ArrayList<DemoData>();
            DemoData demoData1 = new DemoData();
            demoData1.setNumber(3.14);
            demoData1.setPrice(2.0);
            list.add(demoData1);
            DemoData demoData2 = new DemoData();
            demoData2.setNumber(3.14);
            demoData2.setPrice(2.0);
            list.add(demoData2);
            DemoData demoData3 = new DemoData();
            demoData3.setNumber(3.14);
            demoData3.setPrice(2.0);
            list.add(demoData3);

            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            excelWriter.fill(list, fillConfig, writeSheet);
//            excelWriter.fill(list, fillConfig, writeSheet);
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("date", "2019年10月9日13:28:28");
            map.put("total", 1000);
            excelWriter.fill(map, writeSheet);
        }

    }
}
