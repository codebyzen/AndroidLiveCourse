package ru.iteye.androidlivecourseapp.utils.gson_classes

class DeserializationFirebaseInternalError {

	data class Response(
		val error: Error? = null
	)

	data class Error(
		val code: Int? = null,
		val message: String? = null,
		val errors: List<ErrorsItem?>? = null
	)

	data class ErrorsItem(
		val reason: String? = null,
		val domain: String? = null,
		val message: String? = null
	)

}


