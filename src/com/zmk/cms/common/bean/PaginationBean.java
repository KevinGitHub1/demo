package com.zmk.cms.common.bean;

import java.util.List;

/**
 * 分页使用的Bean，用来传递分页信息（使用该类时，应设置pageSize,totalRecords，currentPage，list）
 * 
 */
public class PaginationBean {

    /**
     * 总记录数
     */
    private long totalRecords;
    /**
     * 总页数
     */
    private long totalPages;
    /**
     * 当前页
     */
    private long currentPage;
    /**
     * 每页显示的记录数
     */
    private int pageSize;
    /**
     * 当前页的对象列表
     */
    @SuppressWarnings("unchecked")
    private List list;

    public PaginationBean(long totalRecords, long currentPage) {
        this.setTotalRecords(totalRecords);
        this.setCurrentPage(currentPage);
    }

    public PaginationBean() {

    }

    /**
     * 获得总计录数
     * 
     * @return the totalRecords
     */
    public long getTotalRecords() {
        return totalRecords;
    }

    /**
     *　设置总计录数
     * 
     * @param totalRecords
     *            the totalRecords to set
     */
    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
        this.totalPages = calculatePage();
    }

    /**
     * 计算总页数
     */
    private long calculatePage() {
        if (pageSize == 0)
            return 0;
        if (this.totalRecords % this.pageSize == 0) {
            return this.totalRecords / this.pageSize;
        }
        return this.totalRecords / this.pageSize + 1;
    }

    /**
     * 获得总页数
     * 
     * @return the totalPages
     */
    public long getTotalPages() {
        return totalPages;
    }

    /**
     * 获得当前页
     * 
     * @return the currentPage
     */
    public long getCurrentPage() {
        return currentPage;
    }

    /**
     *　设置当前页
     * 
     * @param currentPage
     *            the currentPage to set
     */
    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 获得list的值
     * 
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public List getList() {
        return list;
    }

    /**
     *　设置list
     * 
     * @param list
     *            the list to set
     */
    @SuppressWarnings("unchecked")
    public void setList(List list) {
        this.list = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
