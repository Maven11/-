package com.hwua.pojo;

import java.util.List;

/**
 * 分页的实体类,封装每一页所要存放的数据
 */
public class PageEntity {
    private Long totalRecords;// 总记录数
    private Integer pageSize;//每页显示的记录数
    private Integer totalPage;//总页数
    private Integer prePage;//上一页
    private Integer nextPage;//下一页
    private Integer pageNo;//当前页
    private List<Message> list = null;//保存每页显示的记录,1对多的关系

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public Integer getTotalPage() {
        totalPage = (int) (totalRecords / pageSize);
        if (totalRecords % pageSize != 0) {
            totalPage += 1;
        }
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获取上一页页码
     *
     * @return
     */
    public Integer getPrePage() {
        if (pageNo <= 1) {
            prePage = 1;
        } else {
            prePage = pageNo - 1;
        }
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    /**
     * 获取下一页的页码
     *
     * @return
     */
    public Integer getNextPage() {
        if (pageNo >= getTotalPage()) {
            nextPage = getTotalPage();
        } else {
            nextPage = pageNo + 1;
        }
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }
}
