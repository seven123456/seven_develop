package com.seven.seven.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created  on 2018-05-29.
 * author:seven
 * email:seven2016s@163.com
 */

public class CollectListInfos implements Serializable {
    private List<CollectInfo> datas;
    private int curPage;

    public List<CollectInfo> getDatas() {
        return datas;
    }

    public void setDatas(List<CollectInfo> datas) {
        this.datas = datas;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
}

