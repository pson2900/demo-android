package com.upzi.upzi.util.tracking

import android.util.Log
import com.posthog.PostHog

object Tracking {
    const val TAG = "PostHog"

    //Reset all properties posthog
    fun reset() {
        Log.i(TAG, "Reset PostHog")
        PostHog.reset()
    }

    //Flush the SDK by sending all the pending events right away
    fun flush() {
        Log.i(TAG, "Sending all the pending events")
        PostHog.flush()
    }

    //tracking screen name
    fun screen(screenName: String, properties: Map<String, Any>? = null) {
        PostHog.screen(screenTitle = screenName, properties = properties)
    }

    //tracking event
    fun tracking(event: String, properties: Map<String, Any>? = null) {
        PostHog.capture(event = event, properties = properties)
    }

    //log
    fun log(event: String) {
        Log.i(TAG, "tracking event = $event")
    }
}