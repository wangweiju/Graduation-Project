package com.wangweiju.mapper;


import com.wangweiju.pojo.Project;
import com.wangweiju.pojo.Projects;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectMapper {
    List<Project> queryProjectList();
    Project queryProjectById(int id);
    int addProject(Projects projects);
    int updateProject(Project project);
    int deleteProject(int id);
}
