package lesson1

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import kotlin.test.Test

class TaskTestsJava : AbstractTaskTests() {

    @Test
    @Tag("3")
    fun testSortTimesJava() {
        sortTimes { inputName, outputName -> JavaTasks.sortTimes(inputName, outputName) }
        assertThrows<IllegalArgumentException> { JavaTasks.sortTimes("input/myTestTime_in1.txt", "output.txt") }
        assertThrows<IllegalArgumentException> { JavaTasks.sortTimes("input/myTestTime_in2.txt", "output.txt") }
        assertThrows<IllegalArgumentException> { JavaTasks.sortTimes("input/myTestTime_in3.txt", "output.txt") }
    }


    @Test
    @Tag("4")
    fun testSortAddressesJava() {
        sortAddresses { inputName, outputName -> JavaTasks.sortAddresses(inputName, outputName) }
        assertThrows<IllegalArgumentException> { JavaTasks.sortTimes("input/myTestAddr_in1.txt", "output.txt") }
        assertThrows<IllegalArgumentException> { JavaTasks.sortTimes("input/MyTestAddr_in2.txt", "output.txt") }
        assertThrows<IllegalArgumentException> { JavaTasks.sortTimes("input/MyTestAddr_in3.txt", "output.txt") }
    }

    @Test
    @Tag("4")
    fun testSortTemperaturesJava() {
        sortTemperatures { inputName, outputName -> JavaTasks.sortTemperatures(inputName, outputName) }
        assertThrows<IllegalArgumentException> { JavaTasks.sortTimes("input/myTestTemp_in1.txt", "output.txt") }
        assertThrows<IllegalArgumentException> { JavaTasks.sortTimes("input/myTestTemp_in2.txt", "output.txt") }
        assertThrows<IllegalArgumentException> { JavaTasks.sortTimes("input/myTestTemp3.txt", "output.txt") }
    }

    @Test
    @Tag("4")
    fun testSortSequenceJava() {
        sortSequence { inputName, outputName -> JavaTasks.sortSequence(inputName, outputName) }
    }

    @Test
    @Tag("2")
    fun testMergeArraysJava() {
        mergeArrays { first, second -> JavaTasks.mergeArrays(first, second) }
    }
}
