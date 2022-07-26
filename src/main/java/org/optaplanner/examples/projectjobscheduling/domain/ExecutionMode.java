package org.optaplanner.examples.projectjobscheduling.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@JsonRootName(value = "PjsExecutionMode")
@XStreamAlias("PjsExecutionMode")
public class ExecutionMode extends AbstractPersistable {

    private Job job;
    private int duration; // In days

    private List<ResourceRequirement> resourceRequirementList;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<ResourceRequirement> getResourceRequirementList() {
        return resourceRequirementList;
    }

    public void setResourceRequirementList(List<ResourceRequirement> resourceRequirementList) {
        this.resourceRequirementList = resourceRequirementList;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

}
