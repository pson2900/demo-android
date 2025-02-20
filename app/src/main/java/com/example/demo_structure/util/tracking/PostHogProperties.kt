package com.example.demo_structure.util.tracking


class PostHogProperties() {
    private var userProperties = HashMap<String, Any>()

    fun resetUserProperties() {
        userProperties = HashMap()
    }

    companion object {
        const val DEFAULT_VALUE = "none"
        const val DEFAULT_NUMBER = -99
        private var instance: PostHogProperties? = null

        fun getInstance(): PostHogProperties {
            if (instance == null) {
                instance = PostHogProperties()
            }
            return instance!!
        }

    }
}