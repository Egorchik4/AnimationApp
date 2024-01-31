package com.example.animationapp.presentation

import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animationapp.domain.convertors.toColorState
import com.example.animationapp.domain.usecase.GetPictureUseCase
import com.example.animationapp.presentation.state.AnimationState
import com.example.animationapp.presentation.state.ColorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val getPictureUseCase: GetPictureUseCase
) : ViewModel() {

	private val _animMut = MutableLiveData<AnimationState>(AnimationState.Initial)
	val animLive: LiveData<AnimationState> = _animMut

	private val coroutineExceptionHandler = CoroutineExceptionHandler { _, errorMessage ->
		_animMut.value = AnimationState.Error(errorMessage = errorMessage.toString())
	}

	fun clickColor(color: Int) {
		when (color.toColorState()) {
			ColorState.RED    -> {
				downloadPicture()
			}

			ColorState.ORANGE -> {
				setText()
			}

			ColorState.YELLOW -> {
				downloadPicture()
			}

			ColorState.GREEN  -> {
				setText()
			}

			ColorState.CYAN   -> {
				downloadPicture()
			}

			ColorState.BLUE   -> {
				setText()
			}

			ColorState.VIOLET -> {
				downloadPicture()
			}

			ColorState.UNUSED -> {}
		}
	}

	private fun downloadPicture() {
		viewModelScope.launch(coroutineExceptionHandler) {
			val picture = getPictureUseCase()
			val bitmap = BitmapFactory.decodeStream(picture.byteStream())
			_animMut.value = AnimationState.ContentPicture(bitmap = bitmap)
		}
	}

	private fun setText() {
		_animMut.value = AnimationState.ContentText(text = "Random text")
	}

	fun spinBaraban() {
		_animMut.value = AnimationState.ContentSpin
	}

	fun resetView() {
		_animMut.value = AnimationState.Initial
	}
}