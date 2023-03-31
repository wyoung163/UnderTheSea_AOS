##How to run
####1. git clone https://github.com/UnderTheSea-E/UnderTheSea_AOS/ using git bash.
####2. Open in android studio.
####3. Download key files in shared google drive folder.(We wrote google drive link 
in answer to the question 'Share your GitHub Project Link' in Google Developer Student Clubs Solution Challenge 2023 - Submission Form)
####4. Put 'google-services.json' file in app/.
####5. Put 'local.properties' file in in project level folder(UnderTheSea_AOS/).
####6. Put 'kakaoApi.xml' file in app/src/main/res/values.
####7. If you using gcp server, open app/src/main/java/com/example/underthesea_aos/retrofit/RetrofitBuilder.kt and change baseUrl as http://34.22.70.141:3000/.
If you using local server, keep baseUrl as http://10.0.2.2:3000/.
