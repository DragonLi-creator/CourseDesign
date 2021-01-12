package com.coursedesign.service.contractService;

import com.coursedesign.entity.Contract;
import com.coursedesign.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ContractService {
    @Autowired
    ContractMapper contractMapper;

    public List<Contract> getAllContract(){
        return contractMapper.getAllContract();
    }

    public boolean inertContract(String host_name, String host_card, String rent_name, String rent_card,  int house_id, String content, Timestamp start_time, Timestamp end_time){
        return contractMapper.insertContract(host_name,host_card,rent_name,rent_card,house_id,content, start_time, end_time);
    }

    public boolean deleteContractById(int id){
        return contractMapper.deleteContract(id);
    }

    public int getContractCount() {
        return contractMapper.getContractCount();
    }
}
