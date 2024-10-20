package com.inncontrol.iam;

import com.inncontrol.iam.domain.services.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCommandService userCommandService;

    @BeforeEach
    public void setUp() {
        Mockito.reset(userCommandService);
    }

    @Test
    public void testVerifyToken() throws Exception {
        String token = "someToken";
        RefreshTokenCommand refreshTokenCommand = new RefreshTokenCommand(token);

        Mockito.when(userCommandService.handle(refreshTokenCommand)).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/v1/authentication/verify-token/" + token))
                .andExpect(status().isNotFound());
    }

}
