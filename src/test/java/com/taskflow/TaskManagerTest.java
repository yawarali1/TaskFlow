package com.taskflow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = new TaskManager();
    }

    @Test
    void testAddTaskIncrementsIdAndStoresCorrectly() {
        Task first = manager.addTask("Learn Git Basics");
        Task second = manager.addTask("Write JUnit Tests");

        assertEquals(1, first.id());
        assertEquals("Learn Git Basics", first.description());
        assertFalse(first.isCompleted());

        assertEquals(2, second.id());
        assertEquals(2, manager.getAllTasks().size());
    }

    @Test
    void testCalculateCompletionRateWhenEmptyReturnsZero() {
        assertEquals(0.0, manager.calculateCompletionRate());
    }

    @Test
    void testCalculateCompletionRateCalculatesPrecisePercentages() {
        manager.addTask("Task 1");
        manager.addTask("Task 2");
        manager.addTask("Task 3");

        // Complete 1 out of 3 tasks (should be 33.33%)
        manager.toggleTaskCompletion(1);

        assertEquals(33.33, manager.calculateCompletionRate());
    }

    @Test
    void testToggleTaskCompletionFailsForInvalidId() {
        manager.addTask("Task 1");
        boolean result = manager.toggleTaskCompletion(999); // Non-existent ID
        
        assertFalse(result);
    }
}