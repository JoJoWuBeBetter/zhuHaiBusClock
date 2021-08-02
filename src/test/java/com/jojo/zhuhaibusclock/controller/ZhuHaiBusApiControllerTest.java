package com.jojo.zhuhaibusclock.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jojo.zhuhaibusclock.mapper.SysUserMapper;
import com.jojo.zhuhaibusclock.model.SysUser;
import com.jojo.zhuhaibusclock.model.entity.Token;
import com.jojo.zhuhaibusclock.security.jwt.JwtUtil;
import com.jojo.zhuhaibusclock.service.ZhuHaiBusService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class ZhuHaiBusApiControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    JwtUtil jwtUtil;

    private Token token = null;

    @Before
    public void getToken() {
        SysUser user = userMapper.selectById(3L);
        this.token = jwtUtil.createToken(user);
    }


    @Test
    public void busSearch() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/zhuHaiBus/searchBusByKeyword?type=0&keyword=b2").header("token", token.getToken())).andReturn().getResponse();
        System.out.println(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    public void stationSearch() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/zhuHaiBus/getStationSegmentList?stationId=66201706090919268074").header("token", token.getToken())).andReturn().getResponse();
        log.info(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}