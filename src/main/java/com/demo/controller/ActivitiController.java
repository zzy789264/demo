package com.demo.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @创建人 zhangzy
 * 创建时间 2018/9/14
 * @描述
 */
@RestController
public class ActivitiController {
    //加载的那两个内容就是我们之前已经弄好的基础内容哦。
    //得到了流程引擎
    @RequestMapping("/createProcess")
    public void createProcess(@RequestBody Map map) {
        //创建工作流
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource(map.get("bpmnUrl").toString())
//                .addClasspathResource(map.get("pngUrl").toString())
                .name(map.get("name").toString())
                .deploy();

        System.err.println("获取ID：" + deployment.getId());
        System.err.println("获取Name：" + deployment.getName());
        System.err.println("获取Key：" + deployment.getKey());
        System.err.println("获取DeploymentTime：" + deployment.getDeploymentTime());
    }

    @RequestMapping("/deleteProcess")
    public void deleteProcess(String processId) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRepositoryService()
                .deleteDeployment(processId);
    }

}
