package com.neusoft.elm.view;

import com.neusoft.elm.po.Business;

import java.util.List;

public interface BusinessView {

    void listBusinessAll();

    void listBusiness();

    void createBusiness();

    void deleteBusiness();

    Business login();

    void showBusinessList(List<Business> list);

    void updateBusiness(Integer businessID);

    void updateBusinessByPassword(Integer businessID);
}