package io.kinguin.harpagon.utest

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.util.*

class ClassNotInjected {
    fun someMethod(param: String): Optional<Double> {
        return Optional.empty()
    }
}

class ClassInjected {
    fun someMethod(param: String): Double {
        return 3.5
    }
}

class MyClass(
    private val myclass: ClassInjected
) {
    fun runK(): String? {
        myclass.someMethod(param = "GBP")
        return null
    }

    fun runM(
    ): String {
        myclass.someMethod(param = "GBP")
        return "fff"
    }
}

@ExtendWith(MockKExtension::class)
@Execution(ExecutionMode.CONCURRENT)
class TempTest {

    @MockK
    lateinit var classNotInjected: ClassNotInjected

    @MockK
    lateinit var classInjected: ClassInjected

    @InjectMockKs
    lateinit var myClass: MyClass

    @BeforeEach
    fun setUp() {
        every { classInjected.someMethod(param = "GBP") } returns 3.5
    }

    @Test
    fun name() {
        myClass.runM()
    }

    @Test
    fun name1() {
        every { classNotInjected.someMethod("kk") } returns Optional.of(100.0)
        myClass.runK()
    }
}