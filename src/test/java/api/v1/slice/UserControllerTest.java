package api.v1.slice;

import api.v1.controller.UserController;
import api.v1.dto.UserMapper;
import api.v1.dto.request.PostUserDto;
import api.v1.dto.response.SuccessResponse;
import api.v1.dto.response.UserResponseDto;
import api.v1.entity.Company;
import api.v1.entity.User;
import api.v1.repository.UserRepository;
import api.v1.service.UserService;
import api.v1.util.ApiDocumentUtils;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper mapper;

    @Test
    public void getMemberTest() throws Exception {
        // given : ????????? ?????? ?????? ??????
        User user = User.builder()
                .name("?????????").password("s4goodbye!").sex(User.Sex.m).companyInfo(new Company("????????????????????????", "005", "001"))
                .build();
        List<User> userList = new ArrayList<>();
        userList.add(user);

        List<UserResponseDto> result = new ArrayList<>();
        UserResponseDto response = new UserResponseDto(1L, "?????????", "m", new Company("????????????????????????", "005", "001"));
        result.add(response);

        // mockito ??????
        given(userService.findUsers()).willReturn(userList);
        given(mapper.usersToUserResponseDto(Mockito.anyList())).willReturn(result);

        // when : ????????? ?????? ???
        ResultActions actions = mockMvc.perform(
                get("/api/v1/usersAll")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then : ????????? ???????????? ????????? ????????? ??????.
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document(
                        "get-user",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("count").type(JsonFieldType.NUMBER).description("????????? ?????? ???"),
                                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data[].name").type(JsonFieldType.STRING).description("?????? ??????(id)"),
                                        fieldWithPath("data[].sex").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("data[].company").type(JsonFieldType.OBJECT).description("?????? ?????? ?????????"),
                                        fieldWithPath("data[].company.name").type(JsonFieldType.STRING).description("?????? ????????? ??????"),
                                        fieldWithPath("data[].company.location").type(JsonFieldType.STRING).description("?????? ????????? ??????"),
                                        fieldWithPath("data[].company.type").type(JsonFieldType.STRING).description("?????? ????????? ??????")
                                ))
                ));

    }

    @Test
    public void getMemberByConditionTest() throws Exception {
        // given : ????????? ?????? ?????? ??????
        User user = User.builder()
                .name("?????????").password("s4goodbye!").sex(User.Sex.m).companyInfo(new Company("????????????????????????", "005", "001"))
                .build();
        List<User> userList = new ArrayList<>();
        userList.add(user);

        List<UserResponseDto> result = new ArrayList<>();
        UserResponseDto response = new UserResponseDto(1L, "?????????", "m", new Company("????????????????????????", "005", "001"));
        result.add(response);

        // mockito ??????
        given(userService.findUsersByCondition(Mockito.anyString(), Mockito.anyString())).willReturn(userList);
        given(mapper.usersToUserResponseDto(Mockito.anyList())).willReturn(result);

        // when : ????????? ?????? ???
        String location = "005";
        String type = null;
        ResultActions actions = mockMvc.perform(
                get("/api/v1/users?location={location}&type={type}", location, type)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then : ????????? ???????????? ????????? ????????? ??????.
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document(
                        "get-user-by-condition",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("location").description("????????? ????????? ??????"),
                                parameterWithName("type").description("????????? ????????? ??????")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("count").type(JsonFieldType.NUMBER).description("????????? ?????? ???"),
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????? ?????????"),
                                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data[].name").type(JsonFieldType.STRING).description("?????? ??????(id)"),
                                        fieldWithPath("data[].sex").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("data[].company").type(JsonFieldType.OBJECT).description("?????? ?????? ?????????"),
                                        fieldWithPath("data[].company.name").type(JsonFieldType.STRING).description("?????? ????????? ??????"),
                                        fieldWithPath("data[].company.location").type(JsonFieldType.STRING).description("?????? ????????? ??????"),
                                        fieldWithPath("data[].company.type").type(JsonFieldType.STRING).description("?????? ????????? ??????")
                                ))
                ));

    }

}
