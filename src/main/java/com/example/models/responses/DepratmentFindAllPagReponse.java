package com.example.models.responses;

import java.util.ArrayList;
import java.util.List;

public class DepratmentFindAllPagReponse {
    private int page;
    private int totalpage;
    private List<DepartmentReponse> listresult = new ArrayList<>();

    public int getTotalpage() {
        return totalpage;
    }
    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }
    public List<DepartmentReponse> getListresult() {
        return listresult;
    }
    public void setListresult(List<DepartmentReponse> listresult) {
        this.listresult = listresult;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
}
