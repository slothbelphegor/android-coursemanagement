# Course Management Android App


### Prerequisites
- Ensure you have Android Studio installed on your machine. 
- Please note that you've had the API of this app in repository: [android-coursemanagement-api](https://github.com/manle423/course-management-android).

### Clone Repository
First, clone the android repository by running the following command in your terminal:

```bash
git clone https://github.com/slothbelphegor/android-coursemanagement.git
```

### Install Dependencies
Install the required packages by clicking _Sync Now_ in _build.gradle(Module :app)_ to implement

### Run the Android App
Open this project on Android Studio, change the IP in following places into the IP of your local machine (which is hosting the server)
- app/res/values/strings.xml: the **URL_API** value
- app/java/com.example.qlkhoahoc/api/ApiClient: the **BASE_URL** value
  
Now you can run the app by pressing **Run** or **Shift + F10** on Android Studio.
