package cn.willow.demo.spring.mvc;

import demo.starter.CustomDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author willow
 * @since 2024/12/17
 */
@RestController
public class ImportDataSourceTestController {
    @Autowired
    private CustomDataSource myDatasource;
    @RequestMapping("/get-datasource")
    public String getDatasource() {
        return myDatasource.getHost() + ":" + myDatasource.getPort();
    }
}
