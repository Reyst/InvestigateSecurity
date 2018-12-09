package gsihome.reyst.fp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class App : Application() {



    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(ActivityLifecycleListener)
    }

    companion object {
        const val PIN: String = "pin"

        internal var activityReferences = 0
        internal var isActivityChangingConfigurations = false
        var isPinNeeded: Boolean = false
            internal set
    }
}

object ActivityLifecycleListener : Application.ActivityLifecycleCallbacks {


    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
        App.isPinNeeded = false
    }

    override fun onActivityStarted(activity: Activity?) {
        if (activity != null) {
            if (++App.activityReferences == 1 && !App.isActivityChangingConfigurations) {
                Log.d("LifecycleListener", "back from background")
                if (activity !is MainActivity) App.isPinNeeded = true
            }
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
        if (activity != null) {
            App.isActivityChangingConfigurations = activity.isChangingConfigurations
            if (--App.activityReferences == 0 && !App.isActivityChangingConfigurations) {
                Log.d("LifecycleListener", "go to background")
            }
        }
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }

}