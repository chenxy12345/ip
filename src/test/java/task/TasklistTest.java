package task;

import exceptions.ElmachoExceptions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TasklistTest {

    /*
     * To test if tasklist properly deletes tasks
     */
    @Test
    public void deleteTask_success() {
        Tasklist tasklist = new Tasklist();
        tasklist.add(new ToDo("testing 1", false));
        tasklist.add(new ToDo("testing 2", false));
        assertEquals(2, tasklist.getNumberOfTasks());
        try {
            tasklist.delete(1);
            assertEquals(1, tasklist.getNumberOfTasks());
        } catch (ElmachoExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * To test the outer bounds of task numbers, to check that deleting this nonexistent task will fail
     */
    @Test
    public void deleteTask_fail() {
        Tasklist tasklist = new Tasklist();
        tasklist.add(new ToDo("testing 1", false));
        try {
            tasklist.delete(2);
            fail();
        } catch (ElmachoExceptions e){
            assertEquals("Invalid task number.", e.getMessage());
        }
    }
}
