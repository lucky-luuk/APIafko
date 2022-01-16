package AfkoAPI;

import AfkoAPI.Controller.AccountController;
import AfkoAPI.DAO.AccountDao;
import AfkoAPI.Model.Role;
import AfkoAPI.RequestObjects.RoleUserRequestObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootTest
public class AccountControllerTest {
    @Bean
    @Primary
    public AccountDao accountDao() {
        return Mockito.mock(AccountDao.class);
    }

    @Autowired
    private AccountController accountController;

    @Autowired
    private AccountDao accountDao;

    @Test
    public void saveRole_callsDao() throws Exception {
        Role testRole = new Role("test");

        HTTPResponse<Role> testResponse = HTTPResponse.<Role>returnSuccess(testRole);
        Mockito.when(accountDao.saveRole(testRole)).thenReturn(testResponse);

        HTTPResponse<Role> actualResponse = accountController.saveRole(testRole);

        Assert.assertEquals(actualResponse.getData().getName(), testResponse.getData().getName());
    }

    @Test
    public void addRoleToUser_callsDao() throws Exception {
        RoleUserRequestObject testRoleRequestObject = new RoleUserRequestObject("testName", "testRole");

        HTTPResponse<String> testResponse = HTTPResponse.<String>returnSuccess("SUCCESS");
        Mockito.when(accountDao.addRoleToUser(testRoleRequestObject.getEmail(), testRoleRequestObject.getRoleName())).thenReturn(testResponse);

        HTTPResponse<String> actualResponse = accountController.addRoleRoUser(testRoleRequestObject);

        Assert.assertEquals(actualResponse.getData(), testResponse.getData());
    }

    @Test
    public void removeRoleFromUser_callsDao() throws Exception {
        RoleUserRequestObject testRoleRequestObject = new RoleUserRequestObject("testName", "testRole");

        HTTPResponse<String> testResponse = HTTPResponse.<String>returnSuccess("SUCCESS");
        Mockito.when(accountDao.removeRoleFromUser(testRoleRequestObject.getEmail(), testRoleRequestObject.getRoleName())).thenReturn(testResponse);

        HTTPResponse<String> actualResponse = accountController.addRoleRoUser(testRoleRequestObject);

        Assert.assertEquals(actualResponse.getData(), testResponse.getData());
    }
}
