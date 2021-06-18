package com.example.models.responses;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFageResponse {
    private int page;
    private int totalpage;

    public List<EmployeeResponse> getListresult() {
        return listresult;
    }

    public void setListresult(List<EmployeeResponse> listresult) {
        this.listresult = listresult;
    }

    private List<EmployeeResponse> listresult = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }
}
