package com.example.zepto;

import com.example.zepto.enums.Gender;
import com.example.zepto.enums.UserType;
import com.example.zepto.models.User;
import com.example.zepto.pojos.AddUserRequest;
import com.example.zepto.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserTests {

    @InjectMocks
    private UserService userService;

    AddUserRequest user = AddUserRequest.builder()
            .name("Saumya")
            .pinCode(301001L)
            .userType(UserType.ADMIN)
            .gender(Gender.FEMALE)
            .age(28)
            .address("Toli ka kuan")
            .build();

    AddUserRequest user1 = AddUserRequest.builder()
            .name("SaumyaUser")
            .pinCode(301001L)
            .userType(UserType.USER)
            .gender(Gender.FEMALE)
            .age(28)
            .address("Toli ka kuan")
            .build();

    @Test
    public void createUserCheck() throws Exception {
        User adminUser = userService.createUser(user);
        User newUser = userService.createUser(user1);

        Assert.assertNotNull(adminUser);
        Assert.assertEquals(adminUser.getName(),user.getName());
        Assert.assertEquals(adminUser.getPinCode(),user.getPinCode());
        Assert.assertEquals(adminUser.getUserType(),user.getUserType());
        Assert.assertEquals(adminUser.getGender(),user.getGender());
        Assert.assertEquals(adminUser.getAge(),user.getAge());
        Assert.assertEquals(adminUser.getAddress(),user.getAddress());

        Assert.assertNotNull(newUser);
        Assert.assertEquals(newUser.getName(),user1.getName());
        Assert.assertEquals(newUser.getPinCode(),user1.getPinCode());
        Assert.assertEquals(newUser.getUserType(),user1.getUserType());
        Assert.assertEquals(newUser.getGender(),user1.getGender());
        Assert.assertEquals(newUser.getAge(),user1.getAge());
        Assert.assertEquals(newUser.getAddress(),user1.getAddress());
    }

    @Test
    public void getUserCheck() throws Exception {
        User adminUser = userService.createUser(user);
        adminUser.setId(1);
        User foundUser = userService.getUser(1);
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(foundUser.getName(),user.getName());
        Assert.assertEquals(foundUser.getPinCode(),user.getPinCode());
        Assert.assertEquals(foundUser.getUserType(),user.getUserType());
        Assert.assertEquals(foundUser.getGender(),user.getGender());
        Assert.assertEquals(foundUser.getAge(),user.getAge());
        Assert.assertEquals(foundUser.getAddress(),user.getAddress());
    }

    @Test
    public void isAdminUserCheck() throws Exception {
        User adminUser = userService.createUser(user);
        adminUser.setId(1);
        boolean adminType = userService.isAdminUser(adminUser.getId());
        Assert.assertTrue(adminType);

        User customer = userService.createUser(user1);
        customer.setId(2);
        boolean customerType = userService.isAdminUser(customer.getId());
        Assert.assertFalse(customerType);
    }

}
