package id.co.indivara.jdt12.minproBank;
import id.co.indivara.jdt12.minproBank.Entity.MstAkun;
import id.co.indivara.jdt12.minproBank.controller.MstAkunController;
import id.co.indivara.jdt12.minproBank.mapper.MapperConver;
import id.co.indivara.jdt12.minproBank.service.MstAkunService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class   JunitBankMaster {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    MstAkunService mstAkunService;
    @Autowired
    MstAkunController mstAkunController;

    @Before
    public  void SetUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mstAkunController).build();
    }
@Test
    public void getAllAkun()throws Exception{
    List<MstAkun>akuncek = mstAkunService.getAllAccount();
    mockMvc.perform(MockMvcRequestBuilders
                    .get("/akun")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(result -> {
                List<MstAkun> akuns = MapperConver.getAllData(result.getResponse().getContentAsString(), MstAkun.class);
                Assertions.assertNotNull(akuns);
                Assertions.assertEquals(akuncek.get(0).getNoRekening(), akuns.get(0).getNoRekening());
            })
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].akunId").isNotEmpty());
    }
}
