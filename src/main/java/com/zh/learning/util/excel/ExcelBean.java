package com.zh.learning.util.excel;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * @author goodteam
 */
public class ExcelBean implements java.io.Serializable {
    /**
     * 列头（标题）名
     */
    private String headTextName;
    /**
     * 对应字段名
     */
    private String propertyName;
    /**
     * 合并单元格数
     */
    private Integer cols;
    private XSSFCellStyle cellStyle;

    public ExcelBean() {
    }

    public ExcelBean(String headTextName, String propertyName) {
        this.setHeadTextName(headTextName);
        this.setPropertyName(propertyName);
    }

    public ExcelBean(String headTextName, String propertyName, Integer cols) {
        super();
        this.setHeadTextName(headTextName);
        this.setPropertyName(propertyName);
        this.setCols(cols);
    }

    /**
     * 省略了get和set方法
     *
     * @return
     */
    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public XSSFCellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(XSSFCellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public String getHeadTextName() {
        return headTextName;
    }

    public void setHeadTextName(String headTextName) {
        this.headTextName = headTextName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}  