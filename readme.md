# Course Management App


### Prerequisites
- Ensure you have Android Studio installed on your machine. 
- Clone the backend repository here: (https://github.com/manle423/course-management-android).

### Clone Repository
First, clone the backend repository by running the following command in your terminal:

```bash
git clone git@github.com:manle423/course-management-android.git
```


### Install Dependencies
Install the required packages by running:
```bash
npm install nodemon
npm install express
npm install mysql2
```

### Run the Server
Finally, to run the backend server, execute the following command:
```bash
npm run dev
```
- The backend project will be running locally on port 3000 at http://localhost:3000/. 

### Run the Android App
Open this project on Android Studio, change the IP in following places into the IP of your local machine (which is hosting the server)
- app/res/values/string.xml: the URL_API value
- app/java/com.example.qlkhoahoc/api/ApiClient: the BASE_URL value
  
Now you can run the app by pressing Run on Android Studio.


