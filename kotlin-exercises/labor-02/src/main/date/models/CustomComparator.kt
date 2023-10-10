package main.date.models

class CustomComparator : Comparator<Date> {
    override fun compare(date1: Date, date2: Date): Int {
        if (date1.year != date2.year) {
            return date1.year - date2.year
        }
        if (date1.month != date2.month) {
            return date1.month - date2.month
        }
        return date1.day - date2.day
    }
}
