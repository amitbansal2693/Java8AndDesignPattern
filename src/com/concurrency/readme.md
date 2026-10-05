1. The machine
   Imagine a server with:
   8 CPU cores
   The CPU is the actual hardware doing calculations.
   You can think of the cores as 8 independent execution units.
   At a particular moment, roughly 8 CPU-bound threads can execute simultaneously:
   Core 1 → Thread A
   Core 2 → Thread B
   Core 3 → Thread C
   Core 4 → Thread D
   Core 5 → Thread E
   Core 6 → Thread F
   Core 7 → Thread G
   Core 8 → Thread H
   The important point:
   CPU cores execute threads.
   They don't directly execute "processes" or "requests."
2. Process
   Now you start your Java application.
   For example:
   java -jar my-application.jar
   The operating system creates a process for your JVM.
   Conceptually:
   Machine
   │
   ├── CPU
   │   ├── Core 1
   │   ├── Core 2
   │   ├── ...
   │   └── Core 8
   │
   └── JVM Process
   The process gets its own memory space and operating-system resources.
   Inside that process is your Java application.
3. The process contains threads
   When the JVM starts, it doesn't have just one thread.
   There are multiple JVM threads, including the main thread and various JVM/runtime threads.
   Your application can also create additional threads.
   Conceptually:
   JVM Process
   │
   ├── Thread 1
   ├── Thread 2
   ├── Thread 3
   ├── Thread 4
   ├── Thread 5
   └── ...
   So the basic relationship is:
   Machine
   ↓
   Process
   ↓
   Threads
   ↓
   CPU cores execute those threads
4. What is a thread?
   A thread is an independent path of execution inside a process.
   Suppose you have:
   public void processOrder() {
   validate();
   calculate();
   save();
   }
   If one thread executes this method, that thread moves through:
   validate()
   ↓
   calculate()
   ↓
   save()
   Another thread can execute another piece of application work at the same time.
   Threads within the same Java process generally share the process's heap memory.
   That's extremely important for concurrency.
5. Now HTTP requests arrive
   Suppose your Spring/SAP Commerce application receives:
   Request A
   Request B
   Request C
   Request D
   The web server typically has a thread pool.
   For example:
   Thread Pool
   ├── Thread 1
   ├── Thread 2
   ├── Thread 3
   ├── Thread 4
   ├── ...
   └── Thread 50
   A request can be assigned to an available worker thread:
   Request A → Thread 1
   Request B → Thread 2
   Request C → Thread 3
   Request D → Thread 4
   So now several requests can be processed concurrently.
6. Does every request create a new thread?
   Usually, no.
   Modern servers generally use thread pools.
   For example:
   Request A → Thread 1
   ↓
   finishes

Request E → Thread 1
The thread is reused.
This is much more efficient than:
Request A → create Thread 1 → destroy
Request B → create Thread 2 → destroy
Request C → create Thread 3 → destroy
Instead:
Thread Pool
│
├── Thread 1 ── Request A ── Request E ── Request H
├── Thread 2 ── Request B ── Request F
├── Thread 3 ── Request C
└── Thread 4 ── Request D ── Request G
7. What if 100 requests arrive?
   Suppose:
   CPU cores = 8
   Thread pool = 50
   Requests = 100
   You don't get 100 threads.
   You have approximately:
   50 worker threads
   The first available threads take requests:
   Request 1  → Thread 1
   Request 2  → Thread 2
   ...
   Request 50 → Thread 50
   The remaining requests may wait in a queue:
   Request 51
   Request 52
   Request 53
   ...
   Request 100
   As threads finish their current requests:
   Thread 1 finishes
   ↓
   takes Request 51

Thread 2 finishes
↓
takes Request 52
And so on.
8. But we only have 8 CPU cores
   This is where it becomes interesting.
   You have:
   8 CPU cores
   50 application threads
   You might ask:
   How can 50 threads run if there are only 8 cores?
   They don't all execute CPU instructions simultaneously.
   The operating system schedules runnable threads onto the CPU cores.
   Conceptually:
   OS Scheduler
   │
   ┌──────────┼──────────┐
   ↓          ↓          ↓
   Core 1     Core 2     ... Core 8
   ↑          ↑              ↑
   Thread     Thread         Thread
   At one moment:
   Core 1 → Thread 1
   Core 2 → Thread 2
   Core 3 → Thread 3
   ...
   Core 8 → Thread 8
   Then later:
   Core 1 → Thread 17
   Core 2 → Thread 3
   Core 3 → Thread 22
   ...
   The OS continuously schedules runnable threads.
9. Context switching
   Suppose Thread 1 is executing.
   The OS decides another thread should execute.
   It saves Thread 1's execution state and switches to Thread 2.
   Conceptually:
   Core 1

Thread 1
↓
save state
↓
Thread 2
↓
execute
Later:
Thread 2
↓
save state
↓
Thread 1
↓
continue
This is a context switch.
Context switching isn't free. It has overhead.
That's one reason having thousands of threads isn't automatically better.
10. Concurrency
    Now we can define concurrency properly.
    Suppose:
    Thread A
    Thread B
    Thread C
    Thread D
    are all making progress during overlapping periods.
    That's concurrency.
    They don't necessarily execute at exactly the same instant.
    For example, on one core:
    Time →

A A B B C C A B C
Only one executes at a particular instant, but all three are progressing.
That's concurrency.
11. Parallelism
    Now suppose you have multiple cores:
    Core 1 → Thread A
    Core 2 → Thread B
    Core 3 → Thread C
    Core 4 → Thread D
    They can literally execute at the same time.
    That's parallelism.
    So:
    Concurrency
    = multiple tasks making progress

Parallelism
= multiple tasks executing simultaneously
Parallelism requires multiple execution resources.
12. One request normally uses one thread
    Suppose:
    GET /orders/123
    comes in.
    Typically:
    Request
    ↓
    Worker Thread
    ↓
    Controller
    ↓
    Service
    ↓
    Repository
    ↓
    Response
    The same worker thread can execute that request's code from beginning to end.
    It doesn't automatically create multiple threads.
13. But one request CAN use multiple threads
    Your application can explicitly introduce asynchronous work.
    For example:
    Request
    ↓
    Thread 1
    │
    ├── submit work → Thread 2
    │
    └── submit work → Thread 3
    Now the request's overall work involves multiple threads.
    This is common with:
    ExecutorService
    CompletableFuture
    asynchronous APIs
    background processing
    So:
    One request does not equal one thread forever.
    It's just that the normal synchronous request path commonly executes on one worker thread.
14. One thread can process many requests
    Because of thread pools:
    Thread 1
    ↓
    Request A
    ↓
    finished
    ↓
    Request B
    ↓
    finished
    ↓
    Request C
    So you should not think:
    Request = Thread
    Instead:
    Request
    ↓
    temporarily assigned to
    ↓
    available worker thread
15. CPU-bound request
    Suppose your request performs a huge calculation:
    Request
    ↓
    Thread
    ↓
    CPU calculation
    ↓
    CPU calculation
    ↓
    CPU calculation
    ↓
    Response
    The thread is actively using CPU.
    If you have:
    8 cores
    100 CPU-heavy threads
    only a limited number can execute CPU instructions simultaneously.
    The others compete for CPU time.
    Adding more threads beyond what the CPU can effectively handle can actually make performance worse because of scheduling/context-switching overhead.
16. I/O-bound request
    Now consider:
    Request
    ↓
    Thread
    ↓
    Database query
    ↓
    WAIT
    ↓
    Database responds
    ↓
    continue
    ↓
    Response
    During the database wait, the thread may not be actively consuming CPU.
    Other threads can use the CPU.
    For example:
    Thread 1 → waiting for DB
    Thread 2 → CPU work
    Thread 3 → waiting for HTTP API
    Thread 4 → CPU work
    Thread 5 → waiting for DB
    This is one major reason web applications can have more threads than CPU cores.
17. Shared memory creates concurrency problems
    Threads inside the same process can access shared objects.
    For example:
    class Counter {
    int count = 0;
    }
    Two threads:
    Thread A → counter.count
    Thread B → counter.count
    Both can access the same object.
    That's powerful, but dangerous.
18. Race condition
    Suppose:
    count++;
    Conceptually:
    read count
    ↓
    add 1
    ↓
    write count
    Suppose count is 10.
    Two threads execute it:
    Thread A          Thread B

read 10           read 10
add 1             add 1
write 11          write 11
Final:
11
Expected:
12
That's a race condition.
The result depends on timing.
19. Synchronization
    You can protect the operation:
    synchronized void increment() {
    count++;
    }
    Now only one thread can execute that synchronized section for the same monitor at a time.
    Conceptually:
    Thread A → enters
    Thread B → waits
    Thread C → waits

Thread A → exits

Thread B → enters
This provides mutual exclusion.
20. Lock
    ReentrantLock provides another way to protect shared resources.
    lock.lock();

try {
count++;
} finally {
lock.unlock();
}
Conceptually:
Lock
↓
Thread A owns it
↓
Thread B waits
↓
Thread A releases
↓
Thread B gets it
21. Atomic operation
    Java provides atomic classes:
    AtomicInteger
    AtomicLong
    AtomicBoolean
    AtomicReference
    Example:
    AtomicInteger counter = new AtomicInteger();

counter.incrementAndGet();
The increment is performed atomically.
This is useful when you need a simple atomic state update without protecting a larger block of logic with a lock.
22. volatile
    Another important concept is visibility.
    Suppose:
    volatile boolean running = true;
    Thread A:
    while (running) {
    ...
    }
    Thread B:
    running = false;
    volatile provides the necessary visibility semantics so that updates to the variable are not treated like an ordinary thread-local/stale cached value.
    But:
    volatile int count;
    count++;
    is still not atomic.
    So:
    volatile
    ↓
    visibility

AtomicInteger
↓
atomic operations

synchronized / Lock
↓
mutual exclusion + visibility
23. Deadlock
    Now imagine:
    Thread A
    ├── holds Lock 1
    └── waiting for Lock 2

Thread B
├── holds Lock 2
└── waiting for Lock 1
Neither can proceed.
That's a deadlock.
The application may appear stuck even though the threads haven't crashed.
24. Starvation
    Suppose Thread A keeps getting access to a resource while Thread B continuously waits.
    Thread A → resource
    Thread A → resource
    Thread A → resource
    Thread A → resource

Thread B → waiting...
Thread B is experiencing starvation.
The system isn't necessarily deadlocked. Other work continues.
25. Livelock
    Two threads continuously react to each other but don't make progress.
    Thread A → changes state
    Thread B → reacts
    Thread A → reacts
    Thread B → reacts
    Thread A → changes again
    ...
    They're active, but useful progress isn't being made.
    That's livelock.
26. Thread pool
    Instead of creating threads for every task:
    Request → create thread
    Request → create thread
    Request → create thread
    the application maintains a pool:
    Thread Pool

Thread 1
Thread 2
Thread 3
Thread 4
Thread 5
Tasks are submitted to the pool:
Task A ──┐
Task B ──┤
Task C ──┼──► Thread Pool
Task D ──┤
Task E ──┘
Available threads execute them.
This is what ExecutorService is designed to help manage.
27. Queue
    If all threads are busy:
    Thread 1 → busy
    Thread 2 → busy
    Thread 3 → busy
    Thread 4 → busy
    new tasks may wait in a queue:
    Queue:

Task E
Task F
Task G
Task H
When a thread becomes available:
Thread 2 finishes
↓
takes Task E
This is a fundamental part of many thread-pool designs.
28. The entire web request story
    Now put everything together.
    Imagine an SAP Commerce/Spring application running on:
    Machine
    8 CPU cores
    The JVM starts:
    JVM Process
    │
    ├── JVM threads
    ├── Application threads
    └── Web server thread pool
    ├── Thread 1
    ├── Thread 2
    ├── ...
    └── Thread 50
    Requests arrive:
    Request A → Thread 1
    Request B → Thread 2
    Request C → Thread 3
    ...
    The OS schedules runnable threads onto the CPU cores:
    Core 1 → Thread 1
    Core 2 → Thread 2
    Core 3 → Thread 3
    ...
    Core 8 → Thread 8
    Some threads might then block waiting for:
    Database
    External API
    File
    Network
    Lock
    The CPU can schedule other runnable threads.
    When the I/O completes, the waiting thread becomes runnable again and eventually gets CPU time.
29. The complete hierarchy
    This is the mental model I recommend remembering:
    MACHINE
    │
    ▼
    CPU
    │
    ┌───────┴───────┐
    │               │
    Core 1          Core 2 ... Core 8
    ▲               ▲
    │               │
    └───────┬───────┘
    │
    OS SCHEDULER
    │
    ▼
    JVM PROCESS
    │
    ┌──────────────┼──────────────┐
    ▼              ▼              ▼
    Thread 1       Thread 2       Thread 3
    │              │              │
    ▼              ▼              ▼
    Request A      Request B      Request C
    And the key relationships are:
    1 Machine
    ↓
    multiple CPU cores

1 JVM
↓
1 process

1 Process
↓
many threads

1 Thread
↓
executes application code

1 Request
↓
usually handled by one worker thread at a time
↓
but can involve multiple threads if async work is introduced

Many Requests
↓
can execute concurrently using multiple worker threads

Many Threads
↓
are scheduled onto available CPU cores

Multiple Cores
↓
allow true parallel execution
The 5 things to remember most
Process  = running application + its isolated memory/resources

Thread   = execution path inside a process

Core     = hardware execution unit that runs threads

Concurrency = multiple tasks making progress in overlapping time

Parallelism = multiple tasks actually executing simultaneously
Once these are clear, thread pools, synchronized, locks, volatile, atomics, CompletableFuture, deadlocks, and race conditions all become extensions of the same model.