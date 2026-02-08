package spark.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;

public class ParserTest {

    @Test
    public void parser_deadlineCommand_parsesCorrectly() throws IOException, SparkException {

        String testFilePath = "data/test_parser.txt";
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }

        Storage storage = new Storage(testFilePath);
        TaskList tasks = new TaskList(storage);
        Parser parser = new Parser("deadline return book /by 2025-10-10 1800");

        String output = parser.parse(tasks);

        assertEquals(1, tasks.getSize());
        assertTrue(tasks.getTask(0).getTaskInfo().contains("Oct 10 2025"));
    }

    @Test
    public void parser_invalidCommand_throwsException() throws IOException {
        Storage storage = new Storage("data/test_parser.txt");
        TaskList tasks = new TaskList(storage);
        Parser parser = new Parser("blah blah blah");

        try {
            parser.parse(tasks);
            // Fail since no exception thrown
            fail();
        } catch (SparkException e) {
            assertEquals("Bark? I didn't quite get that! Fetch me a proper command!", e.getMessage());
        }
    }
}
