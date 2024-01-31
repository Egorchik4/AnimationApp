package com.example.animationapp.domain.convertors

import com.example.animationapp.presentation.state.ColorState

fun Int.toColorState(): ColorState {
	when (this) {
		-65536    -> {
			return ColorState.RED
		}

		-39424    -> {
			return ColorState.ORANGE
		}

		-11008    -> {
			return ColorState.YELLOW
		}

		-8323328  -> {
			return ColorState.GREEN
		}

		-16711711 -> {
			return ColorState.CYAN
		}

		-16766209 -> {
			return ColorState.BLUE
		}

		-4521729  -> {
			return ColorState.VIOLET
		}

		else      -> {
			return ColorState.UNUSED
		}
	}
}