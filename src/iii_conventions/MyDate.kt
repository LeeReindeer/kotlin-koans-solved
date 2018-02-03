package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int):Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        /*
        val diffYear=this.year-other.year
        val diffMonth=this.month-other.month
        val diffDay=this.dayOfMonth-other.dayOfMonth
        if(diffYear == 0) {
            if(diffMonth == 0) {
                return diffDay
            }
            return diffMonth
        }
        return diffYear*/
        return when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

class RepeatedTime(val time: TimeInterval, val number: Int)

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = this.addTimeIntervals(timeInterval, 1)

operator fun MyDate.plus(timeInterval: RepeatedTime): MyDate = this.addTimeIntervals(timeInterval.time, timeInterval.number)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(time: Int) = RepeatedTime(this, time)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate>{
    override fun iterator(): Iterator<MyDate> {
        return DateIterator(this)
    }

    //operator fun contains(value: MyDate): Boolean =((endInclusive>=start)&&(start<=value)&&(endInclusive>=value))
    override fun contains(value: MyDate): Boolean =((endInclusive>=start)&&(start<=value)&&(endInclusive>=value))

}

class DateIterator(private val dateRange: DateRange) : Iterator<MyDate> {

    var current: MyDate = dateRange.start

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }

    override fun hasNext(): Boolean = current <= dateRange.endInclusive

}
