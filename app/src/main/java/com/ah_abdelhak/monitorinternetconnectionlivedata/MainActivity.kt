package com.ah_abdelhak.monitorinternetconnectionlivedata

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat.animate
import androidx.lifecycle.Observer
import com.ah_abdelhak.monitorinternetconnectionlivedata.Extensions.NetworkAvailability
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val ANIMATION_DURATION = 1000.toLong()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleNetworkChanges()
    }

    /*
    * detect availability or unavailability of Internet connection
    * */
    private fun handleNetworkChanges() {
        NetworkAvailability.getNetworkLiveData(this).observe(this, Observer { isConnected ->
            if (!isConnected) {
                textViewNetworkStatus.text = getString(R.string.text_no_connectivity)
                networkStatusLayout.apply {
                    setBackgroundColor(resources.getColor(R.color.colorStatusNotConnected))
                }
            } else {
                textViewNetworkStatus.text = getString(R.string.text_connectivity)
                networkStatusLayout.apply {
                    setBackgroundColor(resources.getColor(R.color.colorStatusConnected))

                    animate()
                        .alpha(1f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                /*
                                * Execute Any Function After Connection Work
                                * */
                            }
                        })
                }
            }
        })
    }

}