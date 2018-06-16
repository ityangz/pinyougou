package com.pinyougou.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果实体类
 */
public class PageResult implements Serializable {
    //总记录数   使用基本数据类型，默认值为0
    private long total;
    //分页数据
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public PageResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
