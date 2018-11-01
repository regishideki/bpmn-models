package auto_refi.growth_process;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

@Deployment(resources = "./auto_refi/growth_process/201810291438.bpmn")
public class _201810291438Test {
	  @Rule
	  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

	  @Test
	  public void goToAutomaticFlowWhenLoanAmountIsLowerThan10000() {
	    RuntimeService runtimeService = processEngineRule.getRuntimeService();
	    Map<String, Object> variables = new HashMap<>();
	    variables.put("loanAmount", 9000);
	    runtimeService.startProcessInstanceByKey("growthProcess", variables);
	    TaskService taskService = processEngineRule.getTaskService();

	    Task task = taskService.createTaskQuery().singleResult();
	   
	    assertEquals("Automatic Flow", task.getName());
	 }
	  
	  @Test
	  public void goToNormalFlowWhenLoanAmountIsGreaterOrEqualsTo10000() {
	    RuntimeService runtimeService = processEngineRule.getRuntimeService();
	    Map<String, Object> variables = new HashMap<>();
	    variables.put("loanAmount", 10000);
	    runtimeService.startProcessInstanceByKey("growthProcess", variables);
	    TaskService taskService = processEngineRule.getTaskService();
	    
	    Task task = taskService.createTaskQuery().singleResult();
	    
	    assertEquals("Normal Flow", task.getName());
	  }
	  
	  @Test
	  public void finishesFlow() {
	    RuntimeService runtimeService = processEngineRule.getRuntimeService();
	    Map<String, Object> variables = new HashMap<>();
	    variables.put("loanAmount", 10000);
	    runtimeService.startProcessInstanceByKey("growthProcess", variables);
	    TaskService taskService = processEngineRule.getTaskService();
	    Task task = taskService.createTaskQuery().singleResult();

	    taskService.complete(task.getId());
	    
	    assertEquals(0, runtimeService.createProcessInstanceQuery().count());
	  }
}
 