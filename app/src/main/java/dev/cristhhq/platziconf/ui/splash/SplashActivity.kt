package dev.cristhhq.platziconf.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import dev.cristhhq.platziconf.R
import dev.cristhhq.platziconf.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val anim = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        ivLogo.startAnimation(anim)

        val mainIntent = Intent(this, MainActivity::class.java)
        anim.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(mainIntent)
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
    }
}