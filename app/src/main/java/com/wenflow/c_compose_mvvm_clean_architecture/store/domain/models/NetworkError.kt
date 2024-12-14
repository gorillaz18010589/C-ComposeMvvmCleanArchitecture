package com.wenflow.c_compose_mvvm_clean_architecture.store.domain.models

//2.Either,準備left -> 錯誤失敗的訊息class
data class NetworkError(
    val error: ApiError,
    val t: Throwable? = null //Throwable t = null;
)

enum class ApiError(val message: String) {
    NetWorkError("Network Error"),
    UnKnownResponse("Unknown Response"),
    UnKnownError("Known Error")
}

//java版本參考
//public class NetworkError {
//    private ApiError error;
//    private Throwable t;
//
//    public NetworkError(ApiError error, Throwable t) {
//        this.error = error;
//        this.t = t;
//    }
//
//    public ApiError getError() {
//        return error;
//    }
//
//    public void setError(ApiError error) {
//        this.error = error;
//    }
//
//    public Throwable getT() {
//        return t;
//    }
//
//    public void setT(Throwable t) {
//        this.t = t;
//    }
//}
//
//public enum ApiError {
//    NetWorkError("Network Error"),
//    UnKnownResponse("Unknown Response"),
//    UnKnownError("Known Error");
//
//    private String message;
//
//    ApiError(String message) {
//        this.message = message;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//}