package uiautomator.zw.com.myapplication.model


data class Student(var userName: String,
                   var password: String,
                   var list: List<Subject>,
                   var map: HashMap<String, String>) {

}