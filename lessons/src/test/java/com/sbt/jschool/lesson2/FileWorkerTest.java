package com.sbt.jschool.lesson2;

import org.junit.*;
import static org.junit.Assert.*;

public class FileWorkerTest {

    FileWorker fileWorker;

    @Before
    public void setUp() throws Exception {
        fileWorker = new FileWorker("words.txt");
    }

    @Test
    public void writeRandomWord() {
        assertTrue(fileWorker.writeRandomWord(0));
        assertFalse(fileWorker.writeRandomWord(-1));
        assertFalse(fileWorker.writeRandomWord(10000));
    }

    @Test
    public void differentWords(){
        assertEquals(fileWorker.countDifferentWords(), 68);
    }
}

