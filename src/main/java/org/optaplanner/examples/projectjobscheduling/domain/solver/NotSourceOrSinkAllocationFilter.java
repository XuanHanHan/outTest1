package org.optaplanner.examples.projectjobscheduling.domain.solver;

import org.optaplanner.core.api.domain.entity.PinningFilter;
import org.optaplanner.examples.projectjobscheduling.domain.Allocation;
import org.optaplanner.examples.projectjobscheduling.domain.JobType;
import org.optaplanner.examples.projectjobscheduling.domain.Schedule;
//相当于PinningFilter  官方文档解释：固定的计划实体在计划期间永远不会更改
//方法 PinningFilter.accept（Object， Object） 如果选择实体已固定，则返回 false;如果选择实体是可移动的，则返回 true
public class NotSourceOrSinkAllocationFilter implements PinningFilter<Schedule, Allocation> {


    //accept方法被重写
    @Override
    public boolean accept(Schedule schedule, Allocation allocation) {
        JobType jobType = allocation.getJob().getJobType();
        return jobType == JobType.SOURCE || jobType == JobType.SINK;
    }

}
