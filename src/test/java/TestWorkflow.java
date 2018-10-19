import static org.junit.Assert.assertEquals;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

public class TestWorkflow {
	  @Rule
	  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

	  @Test
	  @Deployment
	  public void ruleUsageExample() {
	    RuntimeService runtimeService = processEngineRule.getRuntimeService();
	    // proximo passo, descobrir em que paste precisamos colocar esse modelo...
	    runtimeService.startProcessInstanceByKey("callCustomer");

	    // daqui para baixo, podemos ignorar por enquanto. Focar no problema de cima
	    TaskService taskService = processEngineRule.getTaskService();
	    Task task = taskService.createTaskQuery().singleResult();
	    assertEquals("My Task", task.getName());

	    taskService.complete(task.getId());
	    assertEquals(0, runtimeService.createProcessInstanceQuery().count());
	 }
}
