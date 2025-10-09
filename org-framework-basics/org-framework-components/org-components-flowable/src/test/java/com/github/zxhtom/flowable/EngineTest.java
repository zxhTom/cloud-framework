package com.github.zxhtom.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * TODO
 *
 * @author zxhtom
 * 2023/9/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EngineTest {
    @Autowired
    RepositoryService repositoryService;
    @Test
    public void deploy() throws IOException {
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment()
                .category("zxhtom流程分类")
                .name("zxhtom's请假流程")
                .addClasspathResource("请假流程.bpmn20.xml")
                .key("zxhtom_key");

        Deployment deployment = deploymentBuilder.deploy();
    }
}
