## How to run
### 0️⃣ git clone https://github.com/UnderTheSea-E/UnderTheSea_AOS/ using git bash.
### 1️⃣ Open in android studio.
### 2️⃣ Download key files in shared google drive folderr, UnderTheSea(.gitignore files) - Google Solution Challenge 2023. (We wrote google drive link in answer to the question 'Share your GitHub Project Link' in Google Developer Student Clubs Solution Challenge 2023 - Submission Form)
### 3️⃣ Put 'google-services.json' file in app/.
### 4️⃣ Put 'local.properties' file in in project level folder(UnderTheSea_AOS/).
### 5️⃣ Put 'kakaoApi.xml' file in app/src/main/res/values.
### 6️⃣ If you want to connect to our gcp server, open app/src/main/java/com/example/underthesea_aos/retrofit/RetrofitBuilder.kt and change baseUrl as http://34.22.70.141:3000/. If you try to connect to your local server, change baseUrl as http://10.0.2.2:port/ and run.
