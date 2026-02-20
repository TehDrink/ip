# 🐾 Spark: Your Barking Task Companion!

> "Bark Bark Bark Bark, Bark." — *Spark, probably.*

Spark is a CLI-based task manager designed to help you **fetch** your goals and **sit** on your deadlines. Whether it's daily chores or long-term projects, Spark keeps your tasks on a short leash.

### What this good boy can do:
* **Multiple Task Types**: Manage `Todo`, `Deadline`, and `Event`.
* **Intelligent Fetching**: List all tasks or find specific ones by keyword.
* **State Management**: Mark tasks as complete or unmark them if you need to redo them.
* **Editing Capabilities**: Change task descriptions and deadlines with ease.
* ~~**Belly Rubs**: You can give him belly rubs~~

### 🚀 Getting Started
1. **Download**: Get the latest `.jar` file from the [Releases](https://github.com/TehDrink/ip/releases/) page.
2. **Terminal**: Open your Command Line Interface (CLI).
3. **Navigate**: Use `cd` to enter the directory where the file is stored.
4. **Run**: Execute the following command:
   `java -jar Spark.jar`

Note: If you are running Spark from source, run it in your terminal by running the Gradle command in the root folder!
`./gradlew run`

### 🚀 How to prompt the dog
#### Adding Tasks 📝
* **Todo**: Add a simple task without any date/time attached.
  * **Syntax:** `todo <description>`
  * **Example:** `todo submit CS2103T iP increment`
* **Deadline**: Add a task that needs to be done *by* a specific time.
  * **Syntax:** `deadline <description> /by <yyyy-MM-dd HHmm>`
  * **Example:** `deadline Book Pokémon Café /by 2025-11-09 1800`
* **Event**: Add a task that starts and ends at specific times.
  * **Syntax:** `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`
  * **Example:** `event Nintendo Museum /from 2025-12-07 1530 /to 2025-12-07 1600`

#### Viewing 🔍
* **List**: Tells Spark to fetch and display all the tasks currently in your list.
  * **Syntax:** `list`

#### Managing States ✅
* **Mark**: Mark a task as completed.
  * **Syntax:** `mark <index>`
  * **Example:** `mark 1`
* **Unmark**: Unmark a completed task if it needs more work.
  * **Syntax:** `unmark <index>`
  * **Example:** `unmark 1`

#### Editing & Deleting ✏️
* **Edit**: Modify an existing task without having to delete and recreate it.
  * **Syntax:** `edit <index> <flag> <new_value>`
  * **Flags:** `/desc` (description), `/by` (deadline), `/from` (event start), `/to` (event end)
  * **Example:** `edit 1 /desc buy premium dog food`
  * **Example:** `edit 2 /by 2026-01-01 2359`
* **Delete**: Permanently remove a task from the list.
  * **Syntax:** `delete <index>`
  * **Example:** `delete 3`

#### 🚪 Exiting 👋
* **Bye**: Say goodbye to Spark and close the application safely. :(
  * **Syntax:** `bye`

### 🚧 Upcoming Features
- Complete! Feel free to recommend more features!