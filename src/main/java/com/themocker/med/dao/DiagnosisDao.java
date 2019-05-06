package com.themocker.med.dao;

import com.themocker.med.model.Diagnosis;
import com.themocker.med.model.Doctor;
import com.themocker.med.model.Hospital;
import com.themocker.med.model.Puser;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface DiagnosisDao {

    // 注意空格
    String TABLE_NAME = " diagnosis ";
    String INSERT_FIELDS = "  DiaRegNo, DiaPuserNo, DiaResult, DiaCreateTime";
    String SELECT_FIELDS = " DiaNo, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{puserAccount},#{puserPassword},#{puserName},#{puserSex},#{puserAge},#{puserCall})"})
    int addPuser(Puser puser);


    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where DocNo=#{id}"})
    Doctor selectDocByDocNo(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where DiaPuserNo=#{id}"})
    List<Diagnosis> selectDiaByPuserNo(int id);


    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, ""})
    List<Hospital> selectAllHospital();

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where PuserAccount=#{account} and PuserPassword=#{password}"})
    Puser selectByPasswordandAccount(String account, String password);



    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteById(int id);
}
