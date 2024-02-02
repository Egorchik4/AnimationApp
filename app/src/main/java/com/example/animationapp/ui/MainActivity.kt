package com.example.animationapp.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.animationapp.databinding.ActivityMainBinding
import com.example.animationapp.presentation.MainViewModel
import com.example.animationapp.presentation.state.AnimationState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding
	private val viewModel: MainViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setListeners()
		setObserver()
	}

	private fun setListeners() {
		with(binding) {
			baraban.setOnClickListener {
				viewModel.spinBaraban()
			}
			btnReset.setOnClickListener {
				viewModel.resetView()
			}
		}
	}

	private fun setObserver() {
		viewModel.animLive.observe(this) {
			when (it) {
				is AnimationState.Initial        -> {
					handleInitial()
				}

				is AnimationState.ContentSpin    -> {
					handleContentSpin()
				}

				is AnimationState.ContentPicture -> {
					handleContentPicture(it)
				}

				is AnimationState.ContentText    -> {
					handleContentText(it)
				}

				is AnimationState.Error          -> {
					handleError(it)
				}
			}
		}
	}

	private fun handleInitial() {
		binding.baraban.reset()
	}

	private fun handleContentSpin() {
		val randomAngle: Int = (100..10000).random()
		val animator = ObjectAnimator.ofInt(0, randomAngle)
		animator.addUpdateListener {
			val actualRotation = it.animatedValue as Int
			binding.baraban.rotateBaraban(actualRotation)
			if (randomAngle == actualRotation) {
				viewModel.clickColor(binding.baraban.getColorOfCursor())
			}
		}
		animator.start()
	}

	private fun handleContentPicture(state: AnimationState.ContentPicture) {
		binding.baraban.showPicture(state.bitmap)
	}

	private fun handleContentText(state: AnimationState.ContentText) {
		binding.baraban.showText(state.text)
	}

	private fun handleError(state: AnimationState.Error) {
		showMessage(state.errorMessage)
	}

	private fun showMessage(message: String) {
		Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
	}
}