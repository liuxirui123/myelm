package com.neusoft.elm.dao;

import com.neusoft.elm.po.Business;

import java.util.List;

public interface BusinessDao {

    List<Business> listBusiness(String businessName, String businessAddress);

    int createBusiness(String businessName);

    int deleteBusiness(int businessID);

    boolean checkBusinessPassword(Integer businessID, String password);//检查用户的密码是否正确

    Business getBusinessByIdByPass(Integer businessID, String password);

    Business getBusinessById(Integer businessID);

    int updateBusiness(Business business);

    int updateBusinessByPassword(Integer businessID, String password);
}