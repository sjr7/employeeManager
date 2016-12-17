package com.suny.utils;


import java.util.List;

/**
 * 数据分页
 * 孙建荣
 * 2016/11/26 11:01
 */
public class Page<T> {
      private int totalCount;    //查询总记录数
      private int pageCount=5;      //每页的显示行数数
      private int  totalPage;    //总页数
      private int currentPage;   //用户想看的页数
      private  List<T> pageDate;   //封装页面数据


      /**
       * 计算开始行数
       * @return
       */
      public int StartCount(int currentPage,int pageCount) {
            return pageCount * (currentPage - 1);
      }

      /**
       * 获取首页页码
       * @return
       */
      public int getTopPageNo(){
            return 1;
      }

      /**
       * 获取上一页的页码
       * @return
       */
      public int getPrevious(){
            if(currentPage<1){
                  return 1;
            }
            return currentPage-1;
      }

      /**
       * 获取下一页页码
       * @return
       */
      public int getNext(){
            if(Integer.valueOf(currentPage)>=totalPage){
                  currentPage=totalPage;
            }
            return currentPage+1;
      }

      /**
       * 获取末页
       * @return
       */
      public int getBottom(){
            return getTotalPage();
      }

      /**
       * 获取总记录数
       * @return
       */
      public int getTotalCount() {
            return totalCount;
      }

      /**
       * 设置总记录数
       * @param totalCount
       */
      public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
      }

      /**
       * 获取分页后每一个行数
       * @return
       */
      public int getPageCount() {
            return pageCount;
      }

      /**
       * 设置每一页显示的行数
       * @param pageCount
       */
      public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
      }

      /**
       * 获取分页总页数
       * @return
       */
      public int getTotalPage() {
            if(totalCount%pageCount==0){
                  totalPage=totalCount/pageCount;
            }else{
                  totalPage=totalCount/pageCount+1;
            }
            return totalPage;
      }

      /**
       * 设置总页数
       * @param totalPage
       */

      public void setTotalPage(int totalPage) {

            this.totalPage = totalPage;
      }


      /**
       * 获取当前页页码
       * @return
       */
      public int getCurrentPage() {
            if(currentPage<=0){
                  currentPage=1;
            }
            else if(currentPage>totalPage){
                  currentPage=totalPage;
            }
            return currentPage;
      }

      /**
       * 设置当前页页码
       * @param currentPage
       */
      public void setCurrentPage(int currentPage) {

            this.currentPage = currentPage;
      }

      /**
       * 得到分页数据
       * @return
       */
      public List<T> getPageDate() {
            return pageDate;
      }


      /**
       * 设置分页数据
       * @param pageDate
       */
      public void setPageDate(List<T> pageDate) {
            this.pageDate = pageDate;
      }


}




















