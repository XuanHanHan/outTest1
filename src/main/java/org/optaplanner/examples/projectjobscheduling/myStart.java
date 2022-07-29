package org.optaplanner.examples.projectjobscheduling;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.examples.projectjobscheduling.domain.Schedule;
import org.optaplanner.examples.projectjobscheduling.persistence.ProjectJobSchedulingXmlSolutionFileIO;
import org.optaplanner.persistence.jackson.impl.domain.solution.JacksonSolutionFileIO;
import org.optaplanner.persistence.xstream.impl.domain.solution.XStreamSolutionFileIO;

import java.io.File;

public class myStart {


    //关键配置信息
    protected static String solverConfigResource = "projectJobSchedulingSolverConfig.xml";
    static XStreamSolutionFileIO<Schedule> projectJobSchedulingXmlSolutionFileIO = new ProjectJobSchedulingXmlSolutionFileIO();
    static JacksonSolutionFileIO<Schedule> jacksonSolutionFileIO=new JacksonSolutionFileIO<>(Schedule.class);
    //开始处理
    static File newFile = new File("data\\projectjobscheduling\\unsolved\\A-1.xml");

    public static void main(String[] args) {

        SolverFactory<Schedule> solverFactory = SolverFactory.createFromXmlResource(solverConfigResource);

        Schedule problem = projectJobSchedulingXmlSolutionFileIO.read(newFile);
        File file1 = new File("data\\projectjobscheduling\\solved\\A-11j.json");
        jacksonSolutionFileIO.write(problem,file1);


        Solver<Schedule> solver = solverFactory.buildSolver();
        Schedule result = solver.solve(problem);



        File file = new File("data\\projectjobscheduling\\solved\\A-11.xml");
        projectJobSchedulingXmlSolutionFileIO.write(result, file);
    }


}
