package zju.group1.forum.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zju.group1.forum.dto.UserInfo;

@Mapper
public interface UserInfoMapper {

    @Insert("insert into user_info (email) values #{email}")
    void createUserInfo(String email);

    @Select("select count(*) from user_info where email=#{email}")
    int isUserInfoExist(String email);

    @Select("select * from user_info where email=#{email}")
    UserInfo getUserInfo(String email);

    @Update("update user_info set email = #{info.email}, eamil = #{info.eamil_hidden}, real_name = #{info.real_name}" +
            "real_name_hidden = #{info.real_name_hidden}, birth = #{info.birth}, birth_hidden = #{info.birth_hidden}" +
            "phone = #{info.phone}, phone_hidden = #{info.phone_hidden}, gender = #{info.gender}" +
            "gender_hidden = #{info.gender_hidden}, hometown = #{info.hometown}, hometown_hidden = #{info.hometown_hidden}" +
            "organization = #{info.organization}, organization_hidden = #{info.organization_hidden}" +
            "signature = #{info.signature} where email = #{info.email}")
    void updateUserInfo(String email, UserInfo info);
}
