package zju.group1.forum.dto;


import lombok.Data;

@Data
public class UserInformation {
    private int birth;
    private String email;
    private String phone;
    private String gender;
    private String hometown;
    private String organization;
    private String signature;
}
