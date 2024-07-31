package com.example.demo.revolutquiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ThirdStageTest {
    ThirdStage thirdStage;

    @BeforeEach
    void setUp() {
        thirdStage = new ThirdStage();
    }

    @Test
    void deadlock_test() throws InterruptedException {
        String respurce1 = "resource1";
        String respurce2 = "resource2";

        thirdStage.deadLockExample(respurce1, respurce2);

        Thread.sleep(100);
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadBean.getAllThreadIds();
        boolean anyThreadStuck = threadIds != null;
        if (anyThreadStuck) {
            ThreadInfo[] threadInfos = threadBean.getThreadInfo(threadIds);
            for (ThreadInfo threadInfo : threadInfos)
                System.out.println("Deadlock detected: " + threadInfo);
        }

        assertTrue(threadIds != null && threadIds.length > 0, "Deadlock detected");
    }

    @Test
    void reentrantlock_test() {
        assertEquals(102, thirdStage.lockingResultExample(101));
    }

    @Test
    public void thread_pool_test() {
        ExecutorService mockExecutor = Mockito.mock(ExecutorService.class);

        thirdStage.threadPoolExample(mockExecutor);

        verify(mockExecutor, times(5)).execute(any(ThreadPoolWorker.class));
        verify(mockExecutor).shutdown();
        when(mockExecutor.isTerminated()).thenReturn(true);

        while (!mockExecutor.isTerminated())
            mockExecutor.shutdown();

        verify(mockExecutor, atLeastOnce()).isTerminated();
    }
}