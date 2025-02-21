package com.upzi.upzi.util.tracking

import android.content.Context
import com.posthog.PersonProfiles
import com.posthog.android.PostHogAndroid
import com.posthog.android.PostHogAndroidConfig
import com.posthog.android.replay.PostHogSessionReplayConfig

object PostHogConfig {
    private const val POSTHOG_API_KEY = ""
    private const val POSTHOG_HOST = "https://us.i.posthog.com"

    private val config = PostHogAndroidConfig(apiKey = POSTHOG_API_KEY, host = POSTHOG_HOST).apply {
        // Capture certain application events automatically. (on/true by default)
        captureApplicationLifecycleEvents = true

        // Capture screen views automatically. (on/true by default)
        captureScreenViews = false // (on/true by default)

        // Capture deep links as part of the screen call. (on/true by default)
        captureDeepLinks = true

        // Maximum number of events to keep in queue before flushing (20 by default)
        flushAt = 20

        // Number of maximum events in memory and disk, when the maximum is exceed, the oldest event is deleted and the new one takes place. (1000 by default)
        maxQueueSize = 1000

        // Number of maximum events in a batch call. (50 by default)
        maxBatchSize = 50

        // Maximum delay before flushing the queue (30 seconds)
        flushIntervalSeconds = 30

        // Logs the SDK messages into Logcat. (off/false by default)
        debug = false

        // Prevents capturing any data if enabled. (off/false by default)
        optOut = false

        // Send a '$feature_flag_called' event when a feature flag is used automatically. (on/true by default)
        sendFeatureFlagEvent = true

        // Preload feature flags automatically. (on/true by default)
        preloadFeatureFlags = true

        // Callback that is called when feature flags are loaded (not set by default)
//        onFeatureFlags = { ... }

        // Callback that allows to sanitize the event properties (not set by default)
//        propertiesSanitizer = { properties -> ... }

        // Hook for encrypt and decrypt events
        // Devices are sandbox already
        // Defaults to no encryption
//        encryption = object : PostHogEncryption { }

        // Hook that allows for modification of the default mechanism for
        // generating anonymous id (which as of now is just random UUID v7)
//        getAnonymousId = {}

        // Determines the behavior for processing user profiles.
        // Defaults to PersonProfiles.IDENTIFIED_ONLY
        personProfiles = PersonProfiles.IDENTIFIED_ONLY

        // Enable Recording of Session Replay. (off/false by default)
        sessionReplay = false

        // Session Replay configuration
        sessionReplayConfig = PostHogSessionReplayConfig()
    }


    fun setup(context: Context) {
        PostHogAndroid.setup(context, config)
    }
}