package com.abo.study.mapper;

import org.apache.ibatis.annotations.*;

/**
 * @author lnb
 * @date 2021/3/4 11:42
 * @description
 */
@Mapper
public interface AccountInfoMapper {
    @Update("update account_info set account_balance=account_balance+#{amount} where account_no=#{accountNo}")
    int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);

    @Select("select count(1) from de_duplication where tx_no = #{txNo}")
    int isExistTx(String txNo);

    @Insert("insert into de_duplication values(#{txNo},now());")
    int addTx(String txNo);


}
