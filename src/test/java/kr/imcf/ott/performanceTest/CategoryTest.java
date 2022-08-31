package kr.imcf.ott.performanceTest;

import kr.imcf.ott.category.CategoryDTO;
import kr.imcf.ott.category.CategoryService;
import kr.imcf.ott.domain.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryTest {

    @Autowired
    private CategoryService categoryService;

    @Transactional
    @Test
    public void 카테고리조회(){
        StopWatch stopwatch = new StopWatch();

        stopwatch.start("카테고리 조회");
        List<CategoryDTO> results = categoryService.getCategoryList();
        stopwatch.stop();
        System.out.println(stopwatch.prettyPrint());
    }
}
