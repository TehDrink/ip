package spark.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    @Test
    public void getTaskInfo_validDate_formatsCorrectly() {
        DeadlineTask task = new DeadlineTask("return book", "2020-12-02 1800");
        String actualOutput = task.getTaskInfo();
        assertEquals("[D][ ] return book (by: Dec 2 2020, 6:00pm)", actualOutput);
    }

    @Test
    public void getDeadlineIso_checkStorageFormat() {
        DeadlineTask task = new DeadlineTask("submit assignment", "2022-01-01 2359");
        String isoOutput = task.getDeadlineIso();
        assertEquals("2022-01-01 2359", isoOutput);
    }
}