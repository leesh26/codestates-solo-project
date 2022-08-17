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
        // given : 결과로 나올 회원 준비
        User user = User.builder()
                .name("김코딩").password("s4goodbye!").sex(User.Sex.m).companyInfo(new Company("프로젝트스테이츠", "005", "001"))
                .build();
        List<User> userList = new ArrayList<>();
        userList.add(user);

        List<UserResponseDto> result = new ArrayList<>();
        UserResponseDto response = new UserResponseDto(1L, "김코딩", "m", new Company("프로젝트스테이츠", "005", "001"));
        result.add(response);

        // mockito 설정
        given(userService.findUsers()).willReturn(userList);
        given(mapper.usersToUserResponseDto(Mockito.anyList())).willReturn(result);

        // when : 조회를 했을 때
        ResultActions actions = mockMvc.perform(
                get("/api/v1/usersAll")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then : 결과는 만들어둔 회원과 같아야 한다.
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document(
                        "get-user",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("count").type(JsonFieldType.NUMBER).description("조회된 회원 수"),
                                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].name").type(JsonFieldType.STRING).description("회원 이름(id)"),
                                        fieldWithPath("data[].sex").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("data[].company").type(JsonFieldType.OBJECT).description("소속 기업 데이터"),
                                        fieldWithPath("data[].company.name").type(JsonFieldType.STRING).description("소속 기업의 이름"),
                                        fieldWithPath("data[].company.location").type(JsonFieldType.STRING).description("소속 기업의 위치"),
                                        fieldWithPath("data[].company.type").type(JsonFieldType.STRING).description("소속 기업의 업종")
                                ))
                ));

    }

    @Test
    public void getMemberByConditionTest() throws Exception {
        // given : 결과로 나올 회원 준비
        User user = User.builder()
                .name("김코딩").password("s4goodbye!").sex(User.Sex.m).companyInfo(new Company("프로젝트스테이츠", "005", "001"))
                .build();
        List<User> userList = new ArrayList<>();
        userList.add(user);

        List<UserResponseDto> result = new ArrayList<>();
        UserResponseDto response = new UserResponseDto(1L, "김코딩", "m", new Company("프로젝트스테이츠", "005", "001"));
        result.add(response);

        // mockito 설정
        given(userService.findUsersByCondition(Mockito.anyString(), Mockito.anyString())).willReturn(userList);
        given(mapper.usersToUserResponseDto(Mockito.anyList())).willReturn(result);

        // when : 조회를 했을 때
        String location = "005";
        String type = null;
        ResultActions actions = mockMvc.perform(
                get("/api/v1/users?location={location}&type={type}", location, type)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then : 결과는 만들어둔 회원과 같아야 한다.
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document(
                        "get-user-by-condition",
                        ApiDocumentUtils.getRequestPreProcessor(),
                        ApiDocumentUtils.getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("location").description("검색할 기업의 위치"),
                                parameterWithName("type").description("검색할 기업의 업종")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("count").type(JsonFieldType.NUMBER).description("조회된 회원 수"),
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("조회 결과 데이터"),
                                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].name").type(JsonFieldType.STRING).description("회원 이름(id)"),
                                        fieldWithPath("data[].sex").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("data[].company").type(JsonFieldType.OBJECT).description("소속 기업 데이터"),
                                        fieldWithPath("data[].company.name").type(JsonFieldType.STRING).description("소속 기업의 이름"),
                                        fieldWithPath("data[].company.location").type(JsonFieldType.STRING).description("소속 기업의 위치"),
                                        fieldWithPath("data[].company.type").type(JsonFieldType.STRING).description("소속 기업의 업종")
                                ))
                ));

    }

}
