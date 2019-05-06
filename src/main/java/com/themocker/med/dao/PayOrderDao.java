package com.themocker.med.dao;

import com.themocker.med.model.Doctor;
import com.themocker.med.model.Hospital;
import com.themocker.med.model.Payorder;
import com.themocker.med.model.Puser;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PayOrderDao {

    // 注意空格
    String TABLE_NAME = " payorder ";
    String INSERT_FIELDS = " PayNum, PayPuserNo, PayContent, PayAmount, PayCreateTime, PayFinishTime, PayStatus";
    String SELECT_FIELDS = " PayNo, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{puserAccount},#{puserPassword},#{puserName},#{puserSex},#{puserAge},#{puserCall})"})
    int addPuser(Puser puser);

    @Select({"select  DepName from ", TABLE_NAME, " where DepNo=#{id}"})
    String selectDepNameByDepNo(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where PayPuserNo=#{id}"})
    List<Payorder> selectPayOrderByPuserNo(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where DocDepNo=#{id}"})
    List<Doctor> selectDocByDepNo(int id);


    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, ""})
    List<Hospital> selectAllHospital();

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where PuserAccount=#{account} and PuserPassword=#{password}"})
    Puser selectByPasswordandAccount(String account, String password);

    @Update({"update ", TABLE_NAME, " set PuserPassword=#{puserPassword} where PuserNo=#{puserNo}"})
    int updatePassword(Puser puser);

    @Update({"update ", TABLE_NAME, " set  PuserName=#{puserName}, PuserSex=#{puserSex}, PuserAge=#{puserAge}, PuserCall=#{puserCall} where PuserNo=#{puserNo}"})
    int update(Puser puser);


}
