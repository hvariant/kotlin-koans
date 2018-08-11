package iii_conventions

import java.util.Date

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return Date(year, month, dayOfMonth).compareTo(Date(other.year, other.month, other.dayOfMonth))
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

operator fun MyDate.plus(interval: TimeInterval) : MyDate {
    return this.addTimeIntervals(interval, 1)
}

operator fun MyDate.plus(interval: RepeatedTimeInterval) : MyDate {
    return this.addTimeIntervals(interval.t, interval.n)
}

data class RepeatedTimeInterval(val t: TimeInterval, val n: Int)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(n: Int) : RepeatedTimeInterval {
    return RepeatedTimeInterval(this, n)
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var current = start

            override fun next(): MyDate {
                val prev = current
                current = current.nextDay()
                return prev
            }

            override fun hasNext(): Boolean {
                return current <= endInclusive
            }
        }
    }
}

operator fun DateRange.contains(date : MyDate): Boolean {
    return start <= date && date <= endInclusive
}
