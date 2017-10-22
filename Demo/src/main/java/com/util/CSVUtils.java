package com.util;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * 导出CSV文件工具类
 * @author canace
 *
 */
public class CSVUtils {
    /** CSV文件列分隔符 */
    private static final String CSV_COLUMN_SEPARATOR = ",";

    /** CSV文件行分隔符 */
    private static final String CSV_RN = "\r\n";

    /**
     * 
     * @param dataList 集合数据
     * @param colNames 表头部数据
     * @param mapKey 查找的对应数据
     * @param response 返回结果
     */
    public static boolean doExport(List<HashMap<String, Object>> dataList, String colNames, String mapKey, OutputStream os) {
        try {
            StringBuffer buf = new StringBuffer();

            String[] colNamesArr = null;
            String[] mapKeyArr = null;

            colNamesArr = colNames.split(",");
            mapKeyArr = mapKey.split(",");

            // 完成数据csv文件的封装
            // 输出列头
            for (int i = 0; i < colNamesArr.length; i++) {
                buf.append(colNamesArr[i]).append(CSV_COLUMN_SEPARATOR);
            }
            buf.append(CSV_RN);

            if (null != dataList) { // 输出数据
                for (int i = 0; i < dataList.size(); i++) {
                    for (int j = 0; j < mapKeyArr.length; j++) {
                        buf.append(dataList.get(i).get(mapKeyArr[j])).append(CSV_COLUMN_SEPARATOR);
                    }
                    buf.append(CSV_RN);
                }
            }
            // 写出响应
            os.write(buf.toString().getBytes("GBK"));
            os.flush();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * @throws UnsupportedEncodingException
     * 
     *             setHeader
     */
    public static void responseSetProperties(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 设置文件后缀
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fn = fileName + sdf.format(new Date()).toString() + ".csv";
        // 读取字符编码
        String utf = "UTF-8";

        // 设置响应
        response.setContentType("application/ms-txt.numberformat:@");
        response.setCharacterEncoding(utf);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, utf));
    }
    
    /**
     * 字符串中出现逗号，空格，回车等转义问题
     * @param str
     * @return
     */
    public static String csvHandlerStr(String str){
    	//csv格式如果有逗号，整体用双引号括起来；如果里面还有双引号就替换成两个双引号，这样导出来的格式就不会有问题了             
        String tempDescription=str;
    	if(!"".equals(tempDescription) && tempDescription != null){
            //如果有逗号  
            if(str.contains(",")){                
                //如果还有双引号，先将双引号转义，避免两边加了双引号后转义错误  
                if(str.contains("\"")){  
                    tempDescription=str.replace("\"", "\"\"");  
                }  
                //逗号转义  
                tempDescription="\""+tempDescription+"\"";  
            }
            else if(str.contains("\r\n") || str.contains("\n") || str.contains("\r")){
            	 //如果还有双引号，先将双引号转义，避免两边加了双引号后转义错误  
                if(str.contains("\"")){  
                    tempDescription=str.replace("\"", "\"\"");  
                }  
                //换行符转义  
                tempDescription="\""+tempDescription+"\""; 
            }
    	}
    	return tempDescription;
    }

}
