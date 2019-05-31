package com.themocker.med.dao;

import com.themocker.med.model.Puser;
import com.themocker.med.model.Register;
import org.apache.ibatis.annotations.*;


@Mapper
public interface PuserDao {

    // 注意空格
    String TABLE_NAME = " puser ";
    String INSERT_FIELDS = " PuserAccount, PuserPassword, PuserName, PuserSex, PuserAge, PuserCall ";
    String SELECT_FIELDS = " PuserNo, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{puserAccount},#{puserPassword},#{puserName},#{puserSex},#{puserAge},#{puserCall})"})
    int addPuser(Puser puser);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where puserAccount=#{puserAccount}"})
    Puser selectPuserByPuserAccount(String puserAccount);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{account}"})
    Puser selectByAccount(String account);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where PuserAccount=#{account} and PuserPassword=#{password}"})
    Puser selectByPasswordandAccount(String account,String password);

    @Update({"update ", TABLE_NAME, " set PuserPassword=#{puserPassword} where PuserNo=#{puserNo}"})
    int updatePassword(Puser puser);

    @Update({"update ", TABLE_NAME, " set  PuserName=#{puserName}, PuserSex=#{puserSex}, PuserAge=#{puserAge}, PuserCall=#{puserCall} where PuserNo=#{puserNo}"})
    int update(Puser puser);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteById(int id);
}
