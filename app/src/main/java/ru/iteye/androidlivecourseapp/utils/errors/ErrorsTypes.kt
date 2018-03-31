package ru.iteye.androidlivecourseapp.utils.errors


enum class ErrorsTypes(val errorType: Int) {
    GOOGLEPLAYSERVICE_OUTDATE (1),
    NO_INTERNET_CONNECTION(2),
    USER_IS_NULL(4),


    ERROR_INVALID_CUSTOM_TOKEN(101),
    ERROR_CUSTOM_TOKEN_MISMATCH(102),
    ERROR_INVALID_CREDENTIAL(103),
    ERROR_INVALID_EMAIL(104),
    ERROR_WRONG_PASSWORD(105),
    ERROR_USER_MISMATCH(106),
    ERROR_REQUIRES_RECENT_LOGIN(107),
    ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL(108),
    ERROR_EMAIL_ALREADY_IN_USE(109),
    ERROR_CREDENTIAL_ALREADY_IN_USE(110),
    ERROR_USER_DISABLED(111),
    ERROR_USER_TOKEN_EXPIRED(112),
    ERROR_USER_NOT_FOUND(113),
    ERROR_INVALID_USER_TOKEN(114),
    ERROR_OPERATION_NOT_ALLOWED(115),
    ERROR_WEAK_PASSWORD(116),

    ALLOK(31337),



}

/*
enum class ErrorCodes(val errorMsg: String) {
    ERROR_INVALID_CUSTOM_TOKEN("The custom token format is incorrect. Please check the documentation."),
    ERROR_CUSTOM_TOKEN_MISMATCH("The custom token corresponds to a different audience."),
    ERROR_INVALID_CREDENTIAL("The supplied auth credential is malformed or has expired."),
    ERROR_INVALID_EMAIL("The email address is badly formatted."),
    ERROR_WRONG_PASSWORD("The password is invalid or the user does not have a password."),
    ERROR_USER_MISMATCH("The supplied credentials do not correspond to the previously signed in user."),
    ERROR_REQUIRES_RECENT_LOGIN("This operation is sensitive and requires recent authentication. Log in again before retrying this request."),
    ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL("An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address."),
    ERROR_EMAIL_ALREADY_IN_USE("The email address is already in use by another account."),
    ERROR_CREDENTIAL_ALREADY_IN_USE("This credential is already associated with a different user account."),
    ERROR_USER_DISABLED("The user account has been disabled by an administrator."),
    ERROR_USER_TOKEN_EXPIRED("The user\'s credential is no longer valid. The user must sign in again."),
    ERROR_USER_NOT_FOUND("There is no user record corresponding to this identifier. The user may have been deleted."),
    ERROR_INVALID_USER_TOKEN("The user\'s credential is no longer valid. The user must sign in again."),
    ERROR_OPERATION_NOT_ALLOWED("This operation is not allowed. You must enable this service in the console."),
    ERROR_WEAK_PASSWORD("The given password is invalid."),
}
*/