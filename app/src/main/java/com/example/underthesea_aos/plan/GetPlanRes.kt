package com.example.underthesea_aos.plan

import java.time.LocalDate
import java.util.*

class GetPlanRes {
    var planId: Long? = null
        get() { return field }
        set(value) { field = value }
    var userId: Long? = null
        get() { return field }
        set(value) { field = value }
    var freindId: Long? = null
        get() { return field }
        set(value) { field = value }
    var content: String? = null
        get() { return field }
        set(value) { field = value }
    var date: LocalDate? = null
        get() { return field }
        set(value) { field = value }
    var status: String? = null
        get() { return field }
        set(value) { field = value }
    var created_at: Date? = null
        get() { return field }
        set(value) { field = value }
    var updated_at: Date? = null
        get() { return field }
        set(value) { field = value }
}