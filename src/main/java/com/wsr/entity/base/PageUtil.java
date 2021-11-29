package com.wsr.entity.base;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：wangsr
 * @description：分页总数
 * @date ：Created in 2021/11/29 10:05
 */
public class PageUtil<T> implements Serializable {
    private static final long serialVersionUID = 5897450856255378556L;
    /**
     * 总记录数
     */
    @ApiModelProperty(value="总记录数")
    private int totalCount;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value="每页记录数")
    private int pageSize;
    /**
     * 总页数
     */
    @ApiModelProperty(value="总页数")
    private int totalPage;
    /**
     * 当前页数
     */
    @ApiModelProperty(value="当前页数")
    private int currPage;
    /**
     * 列表数据
     */
    @ApiModelProperty(value="列表数据")
    private List<T> list;

    /**
     * 分页
     * @param list        列表数据
     * @param totalCount  总记录数
     * @param pageSize    每页记录数
     * @param currPage    当前页数
     */
    public PageUtil(List<T> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    /**
     * 分页
     */
    public PageUtil(Page<T> page, List<T> list) {
        this.list = list;
        this.totalCount = (int)page.getTotal();
        this.pageSize = (int)page.getPageSize();
        this.currPage = (int)page.getPageNum();
        this.totalPage = (int)page.getPages();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
