package com.cinshop;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import com.cinshop.customer.LoginUserRepository;
import com.cinshop.customer.LoginUserService;
import com.cinshop.customer.SecurityConfig;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

@WebMvcTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {LoginUserService.class, 
        		   LoginUserRepository.class, SecurityConfig.class}
))
@AutoConfigureJdbc  // 組み込みデータソース＋JdbcTemplateのAuto Configurationが有効化される
public class ControllerTest {
	
	@Autowired
	MockMvc mvc;
	
    @Test
    @WithUserDetails(value = "値を変える@gmail.com", userDetailsServiceBeanName = "loginUserDetailsService")
    public void 一般ユーザーでログインができる() throws Exception {
    	mvc.perform(get("/login").accept(MediaType.TEXT_HTML))
    	.andExpect(status().isOk());
    }
}