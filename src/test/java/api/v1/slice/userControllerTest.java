//package api.v1.slice;
//
//import api.v1.controller.UserController;
//import api.v1.dto.UserMapper;
//import api.v1.dto.response.SuccessResponse;
//import api.v1.entity.User;
//import api.v1.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(UserController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class userControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private UserMapper mapper;
//
//    @Test
//    public void getMemberTest() throws Exception {
//        // given : 결과로 나올 회원 준비
//        User user = User.builder()
//                .name("김코딩").password("s4goodbye!").sex(User.Sex.m).companyName("프로젝트스테이츠")
//                .companyType("005").companyLocation("001")
//                .build();
//        List<User> userList = new ArrayList<>();
//        userList.add(user);
//
//        // mockito 설정
//        given(userService.findUsers()).willReturn(userList);
//
//        // when : 조회를 했을 때
//        ResultActions actions = mockMvc.perform(
//                get("/api/v1/users")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        // then : 결과는 만들어둔 회원과 같아야 한다.
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.name").value(user.getName()))
//                .andExpect(jsonPath("$.data.companyName").value(user.getCompanyName()))
//                .andDo(document(
//                        "get-user",
//                        ApiDocumentUtils.getRequestPreProcessor(),
//                        ApiDocumentUtils.getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("memberId").description("Member ID")
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
//                                        fieldWithPath("data.phone").type(JsonFieldType.STRING).description("휴대폰 번호"),
//                                        fieldWithPath("data.memberStatus").type(JsonFieldType.STRING).description("회원 상태"),
//                                        fieldWithPath("data.stamp").type(JsonFieldType.NUMBER).description("스탬프 갯수")
//                                ))
//                ));;
//
//    }
//
//}
