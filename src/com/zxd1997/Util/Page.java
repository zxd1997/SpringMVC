package com.zxd1997.Util;

public class Page {
    private static int N = 10;
    private static int p_N = 9;
    private int count;
    private int page = 1;
    private int maxPage;
    private int startPage;
    private int endPage;

    public static int getN() {
        return N;
    }

    public static void setN(int n) {
        N = n;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page<1)this.page=1;else
        if (page>maxPage)this.page=maxPage;else this.page=page;

    }

    public int getP_N() {
        return p_N;
    }

    public void setP_N(int p_N) {
        Page.p_N = p_N;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMaxPage() {
        return maxPage;
    }

    private void setMaxPage() {
        this.maxPage = count % N == 0 ? count / N : count / N + 1;
    }

    public int getStartPage() {
        return startPage;
    }

    private void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    private void setStart_EndPage(){
        if (maxPage<p_N){
            setStartPage(1);
            setEndPage(maxPage);
        }else if (p_N/2+1>=page){
            setStartPage(1);
            setEndPage(p_N);
        }else if (page<maxPage-p_N/2){
            setStartPage(page-p_N/2);
            setEndPage(page+p_N/2);
        }else {
            setStartPage(maxPage-p_N+1);
            setEndPage(maxPage);
        }
    }
    public int getEndPage() {
        return endPage;
    }

    private void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public Page(int count, int page) {
        this.count = count;
        setMaxPage();
        setPage(page);
        setStart_EndPage();
    }
}