package org.optaplanner.my_start;

import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.examples.common.app.CommonApp;
import org.optaplanner.examples.common.business.SolutionBusiness;
import org.optaplanner.examples.common.swingui.SolutionPanel;
import org.optaplanner.examples.common.swingui.SolverAndPersistenceFrame;
import org.optaplanner.examples.projectjobscheduling.domain.Schedule;
import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;
import org.optaplanner.persistence.xstream.impl.domain.solution.XStreamSolutionFileIO;

import javax.swing.*;
import java.io.File;
import java.util.concurrent.ExecutionException;

public class myStart {


    //关键配置信息
    protected static String name="myStart";
    protected static String description="try to start by mySelf";
    protected static String solverConfigResource="org/optaplanner/examples/projectjobscheduling/projectJobSchedulingSolverConfig.xml";
    protected static String dataDirName="projectjobscheduling";

    //求解器资源信息

    public SolutionBusiness<Schedule,?> solutionBusiness;

    //建立commonApp，剥离图标
    public static CommonApp<Schedule> myApp = new CommonApp<>(name,description,solverConfigResource,dataDirName,null) {
        @Override
        protected SolutionPanel<Schedule> createSolutionPanel() {
            return null;
        }

        @Override
        public SolutionFileIO<Schedule> createSolutionFileIO() {
            return new XStreamSolutionFileIO<Schedule>();
        }
    };


    //建立一个solution
    public static SolutionBusiness<Schedule, ?> createSolutionBusiness() {

        SolutionBusiness<Schedule, ?> solutionBusiness = new SolutionBusiness<>(myApp,SolverFactory.createFromXmlResource(solverConfigResource));
        solutionBusiness.setDataDir(determineDataDir(dataDirName));
        solutionBusiness.setSolutionFileIO(myApp.createSolutionFileIO());
        solutionBusiness.updateDataDirs();
        return solutionBusiness;
    }

    //开始处理
    File newFile=new File("/A-1.xml");

    public void toStart(){
        this.solutionBusiness= createSolutionBusiness();
        solutionBusiness.openSolution(newFile);
        Schedule problem = solutionBusiness.getSolution();
        new SolveWorker(problem).execute();
    }


    public void anotherStart(){
        SolverFactory<Schedule> solverFactory=SolverFactory.createFromXmlResource(solverConfigResource);

    }

    //找到data
    /**
     * The path to the data directory, preferably with unix slashes for portability.
     * For example: -D{@value #DATA_DIR_SYSTEM_PROPERTY}=sources/data/
     */
    public static final String DATA_DIR_SYSTEM_PROPERTY = "org.optaplanner.examples.dataDir";

    public static File determineDataDir(String dataDirName) {
        String dataDirPath = System.getProperty("org.optaplanner.examples.dataDir", "data/");
        File dataDir = new File(dataDirPath, dataDirName);
        if (!dataDir.exists()) {
            throw new IllegalStateException("The directory dataDir (" + dataDir.getAbsolutePath()
                    + ") does not exist.\n" +
                    " Either the working directory should be set to the directory that contains the data directory" +
                    " (which is not the data directory itself), or the system property "
                    + DATA_DIR_SYSTEM_PROPERTY + " should be set properly.\n" +
                    " The data directory is different in a git clone (optaplanner/optaplanner-examples/data)" +
                    " and in a release zip (examples/sources/data).\n" +
                    " In an IDE (IntelliJ, Eclipse, VSCode), open the \"Run configuration\""
                    + " to change \"Working directory\" (or add the system property in \"VM options\").");
        }
        return dataDir;
    }


    //
    public class SolveWorker extends SwingWorker<Schedule, Void> {

        protected final Schedule problem;

        public SolveWorker(Schedule problem) {
            this.problem = problem;
        }

        @Override
        protected Schedule doInBackground() {
            return solutionBusiness.solve(problem, null);
        }

        @Override
        protected void done() {
            try {
                Schedule bestSolution = get();
                solutionBusiness.setSolution(bestSolution);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Solving was interrupted.", e);
            } catch (ExecutionException e) {
                throw new IllegalStateException("Solving failed.", e.getCause());
            }

        }

    }

}
