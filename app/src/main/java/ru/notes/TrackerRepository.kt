package ru.notes

object TrackerRepository {
    val trackers: List<Tracker> = listOf(
        Tracker(id = 0, name="3 liters of woter"),
        Tracker(id = 1, name="10 squats"),
        Tracker(id = 2, name="1000 steps",),
        Tracker(id = 3, name="10 pages of the book",),
        Tracker(id = 4, name="Dont worry about nothing!")

    )
    fun getTrackerById(id: Int): Tracker {
        return trackers.first {it.id == id}
    }

}

