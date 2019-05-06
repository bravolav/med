package com.themocker.med.dao;

import com.themocker.med.model.Doctor;
import com.themocker.med.model.Hospital;
import com.themocker.med.model.Puser;
import com.themocker.med.model.Register;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface RegisterDao {
    // 注意空格
    String TABLE_NAME = " register ";
    String INSERT_FIELDS = "  RegDocNo, RegPuserNo, RegTime,RegCreateTime ,RegStatus";
    String SELECT_FIELDS = " RegNo, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{puserAccount},#{puserPassword},#{puserName},#{puserSex},#{puserAge},#{puserCall})"})
    int addPuser(Puser puser);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where RegPuserNo=#{id}"})
    List<Register> selectRegByDepNo(int id);

    @Select({"select RegDocNo", SELECT_FIELDS, " from ", TABLE_NAME, " where RegNo=#{id}"})
    int selectDocNoByRegNo(int id);

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

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteById(int id);
}
