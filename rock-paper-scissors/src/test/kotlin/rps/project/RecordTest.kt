package rps.project

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import rps.project.Outcome
import rps.project.Record
import rps.project.outcomeToRecord

class RecordTest {
    @Test
    fun testEquals() {
        val record1 = Record(1,2,3)
        val record2 = Record(1,4,5)
        val record3 = Record (1,2,3)
        Assertions.assertTrue(record1.equals(record3), "Records are equal if all components are")
        Assertions.assertFalse(record1.equals(record2), "Records are unequal if any components are")
    }

    @Test
    fun testIncrement() {
        val oldRecord = Record(3,2,5)
        val increment = Record(1,0,0)
        val expected = Record(4,2,5)
        val actual = oldRecord.increment(increment)
        Assertions.assertTrue(expected.equals(actual), "Incrementation works properly")
    }

    @Test
    fun testOutcomeToRecord() {
        val winRecord = Record(1,0,0)
        val lossRecord = Record(0,1,0)
        val tieRecord = Record(0,0,1)
        val otherRecord = Record(0, 0, 0)
        Assertions.assertTrue(outcomeToRecord(Outcome.WIN).equals(winRecord), "A win is coded (1,0,0)")
        Assertions.assertTrue(outcomeToRecord(Outcome.LOSS).equals(lossRecord), "A loss is coded (0,1,0)")
        Assertions.assertTrue(outcomeToRecord(Outcome.TIE).equals(tieRecord), "A tie is coded (0,0,1)")
        Assertions.assertTrue(outcomeToRecord(Outcome.UNDEFINED).equals(otherRecord), "Undefined is coded (0,0,0)")
        Assertions.assertTrue(outcomeToRecord(Outcome.WAITING).equals(otherRecord), "Waiting is coded (0,0,1)")
    }

    @Test
    fun testStringOutput() {
        val record = Record(4,5,3)
        val expected = "Wins: 4, Losses: 5, Ties: 3"
        val actual = record.toString()
        Assertions.assertTrue(actual.equals(expected), "Record is stringified properly")
    }

}