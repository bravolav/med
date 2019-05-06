package com.themocker.med.dao;

import com.themocker.med.model.Department;
import com.themocker.med.model.Doctor;
import com.themocker.med.model.Hospital;
import com.themocker.med.model.Puser;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface DoctorDao {

    // 注意空格
    String TABLE_NAME = " doctor ";
    String INSERT_FIELDS = " DocDepNo, DocName, DocVisTime, DocClass";
    String SELECT_FIELDS = " DocNo, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{puserAccount},#{puserPassword},#{puserName},#{puserSex},#{puserAge},#{puserCall})"})
    int addPuser(Puser puser);

    @Select({"select  DepName from ", TABLE_NAME, " where DepNo=#{id}"})
    String selectDepNameByDepNo(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where DocNo=#{id}"})
    Doctor selectDocByDocNo(int id);

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
