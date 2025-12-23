## How to reproduce the bug
- run the whole test class `TempTest`, you will get `no answer found for ClassInjected(classInjected#1).someMethod(GBP) among the configured answers: ()`

## How to run without error
### first method:
downgrade dependency to `testImplementation("io.mockk:mockk:1.13.3")` and run test class

### second method:
comment out `@Execution(ExecutionMode.CONCURRENT)` tests will be run sequentially

### third method:
comment out the line 66 `every { classNotInjected.someMethod("kk") } returns Optional.of(100.0)` this mock will not be configured and run test class

### fourth method:
change return type of `ClassNotInjected::someMethod` from `Optional`to `Double` and the returned value of mocking method in line 66 and run test class

### fifth method:
run each test method separately
