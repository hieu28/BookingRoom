package com.example.models.responses;

import java.util.ArrayList;
import java.util.List;

public class BookingfindAllPagReponse {
    private int page;
    private int totalpage;
    private List<BookingReponse> listresult = new ArrayList<>();

    public int getTotalpage() { return totalpage; }
    public void setTotalpage(int totalpage) { this.totalpage = totalpage; }
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
    public List<BookingReponse> getListresult() { return listresult; }
    public void setListresult(List<BookingReponse> listresult) { this.listresult = listresult; }
}
