package org.optaplanner.examples.projectjobscheduling.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.valuerange.CountableValueRange;
import org.optaplanner.core.api.domain.valuerange.ValueRangeFactory;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.variable.CustomShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableReference;
import org.optaplanner.examples.projectjobscheduling.domain.solver.DelayStrengthComparator;
import org.optaplanner.examples.projectjobscheduling.domain.solver.ExecutionModeStrengthWeightFactory;
import org.optaplanner.examples.projectjobscheduling.domain.solver.NotSourceOrSinkAllocationFilter;
import org.optaplanner.examples.projectjobscheduling.domain.solver.PredecessorsDoneDateUpdatingVariableListener;

import java.util.List;

@PlanningEntity(pinningFilter = NotSourceOrSinkAllocationFilter.class)
@XStreamAlias("PjsAllocation")
public class Allocation extends AbstractPersistable {

    private Job job;

    private Allocation sourceAllocation;//资源分配
    private Allocation sinkAllocation;//重要程度分配
    private List<Allocation> predecessorAllocationList;//上一代分配表
    private List<Allocation> successorAllocationList;//下一代分配表

    // Planning variables: changes during planning, between score calculations.
    private ExecutionMode executionMode;//执行模式
    private Integer delay; // In days 需要的天数

    // Shadow variables
    private Integer predecessorsDoneDate;//上一代完成日期

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Allocation getSourceAllocation() {
        return sourceAllocation;
    }

    public void setSourceAllocation(Allocation sourceAllocation) {
        this.sourceAllocation = sourceAllocation;
    }

    public Allocation getSinkAllocation() {
        return sinkAllocation;
    }

    public void setSinkAllocation(Allocation sinkAllocation) {
        this.sinkAllocation = sinkAllocation;
    }

    public List<Allocation> getPredecessorAllocationList() {
        return predecessorAllocationList;
    }

    public void setPredecessorAllocationList(List<Allocation> predecessorAllocationList) {
        this.predecessorAllocationList = predecessorAllocationList;
    }

    public List<Allocation> getSuccessorAllocationList() {
        return successorAllocationList;
    }

    public void setSuccessorAllocationList(List<Allocation> successorAllocationList) {
        this.successorAllocationList = successorAllocationList;
    }

    @PlanningVariable(valueRangeProviderRefs = {
            "executionModeRange" }, strengthWeightFactoryClass = ExecutionModeStrengthWeightFactory.class)
    public ExecutionMode getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(ExecutionMode executionMode) {
        this.executionMode = executionMode;
    }

    @PlanningVariable(valueRangeProviderRefs = { "delayRange" }, strengthComparatorClass = DelayStrengthComparator.class)
    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    @CustomShadowVariable(variableListenerClass = PredecessorsDoneDateUpdatingVariableListener.class, sources = {
            @PlanningVariableReference(variableName = "executionMode"),
            @PlanningVariableReference(variableName = "delay") })
    public Integer getPredecessorsDoneDate() {
        return predecessorsDoneDate;
    }

    public void setPredecessorsDoneDate(Integer predecessorsDoneDate) {
        this.predecessorsDoneDate = predecessorsDoneDate;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public Integer getStartDate() {
        if (predecessorsDoneDate == null) {
            return null;
        }
        return predecessorsDoneDate + (delay == null ? 0 : delay);
    }

    public Integer getEndDate() {
        if (predecessorsDoneDate == null) {
            return null;
        }
        return predecessorsDoneDate + (delay == null ? 0 : delay)
                + (executionMode == null ? 0 : executionMode.getDuration());
    }

    public Project getProject() {
        return job.getProject();
    }

    public int getProjectCriticalPathEndDate() {
        return job.getProject().getCriticalPathEndDate();
    }

    public JobType getJobType() {
        return job.getJobType();
    }

    public String getLabel() {
        return "Job " + job.getId();
    }

    // ************************************************************************
    // Ranges
    // ************************************************************************

    @ValueRangeProvider(id = "executionModeRange")
    public List<ExecutionMode> getExecutionModeRange() {
        return job.getExecutionModeList();
    }

    @ValueRangeProvider(id = "delayRange")
    public CountableValueRange<Integer> getDelayRange() {
        return ValueRangeFactory.createIntValueRange(0, 500);
    }

}
