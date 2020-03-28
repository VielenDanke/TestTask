package kz.danke.test.task.controller;

import com.sun.security.auth.UserPrincipal;
import kz.danke.test.task.model.User;
import kz.danke.test.task.repository.UserRepository;
import kz.danke.test.task.service.UserService;
import kz.danke.test.task.util.FileUploadUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static kz.danke.test.task.utils.UserPopulate.USER_LIST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:/application-test.properties")
public class UserControllerIT {

    private static final String USERNAME_FIRST_USER = "TEST_1";

    private MockMvc mockMvc;
    private UserController userController;
    private UserRepository userRepository;
    private MockMultipartFile mockMultipartFile;
    @MockBean
    private UserService userService;
    @MockBean
    private FileUploadUtil fileUploadUtil;

    @Before
    public void setup() {
        USER_LIST.forEach(userRepository::save);
        mockMultipartFile = new MockMultipartFile("a", "a", ".jpeg", "123".getBytes());
    }

    @Test
    public void setFieldsShouldNotBeNull() {
        Assert.assertNotNull(mockMvc);
        Assert.assertNotNull(userController);
    }

    @Test
    public void shouldReturnByGetRequest_LoginPage() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(view().name("login"));
    }

    @Test
    public void shouldReturnByGetRequest_RegistrationPage() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andExpect(view().name("registration"));
    }

    @Test
    @WithUserDetails(USERNAME_FIRST_USER)
    public void shouldReturnByGetRequest_CabinetPage() throws Exception {
        Mockito.when(userService.findByUsername(USERNAME_FIRST_USER))
                .thenReturn(USER_LIST.get(0));

        this.mockMvc.perform(get("/cabinet"))
                .andExpect(model().attribute("name", USER_LIST.get(0).getUsername()))
                .andExpect(model().attribute("number", USER_LIST.get(0).getNumber()))
                .andExpect(view().name("cabinet"));

        Mockito.verify(userService, Mockito.times(1)).findByUsername(USERNAME_FIRST_USER);
    }

    @Test
    public void shouldReturnByGetRequest_MainPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    public void shouldAddUserToDatabase() throws Exception {
        Mockito.when(fileUploadUtil.fileUpload(mockMultipartFile, "/test/"))
                .thenReturn("TEST_FILE_NAME");

        User user = USER_LIST.get(0);
        user.setMultipartFile(mockMultipartFile);

        this.mockMvc.perform(post("/save")
                .flashAttr("user", user))
                .andExpect(view().name("/login"));
    }

    @Test
    @WithUserDetails(USERNAME_FIRST_USER)
    public void shouldIncreaseTheNumber() throws Exception {
        Mockito.when(userService.findByUsername(USERNAME_FIRST_USER))
                .thenReturn(USER_LIST.get(0));

        Mockito.when(userService.save(USER_LIST.get(0)))
                .thenReturn(USER_LIST.get(0));

        this.mockMvc.perform(get("/increase").principal(new UserPrincipal(USERNAME_FIRST_USER)))
                .andExpect(model().attribute("number", 0))
                .andExpect(model().attribute("name", USER_LIST.get(0).getUsername()))
                .andExpect(view().name("cabinet"));

        Mockito.verify(userService, Mockito.times(1)).findByUsername(USERNAME_FIRST_USER);
        Mockito.verify(userService, Mockito.times(1)).save(USER_LIST.get(0));
    }

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
