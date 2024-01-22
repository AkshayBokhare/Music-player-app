# Music player :  <br>

1- create splash screen layout  <br>
2- Create Login layout   <br>
3- create signup layout  <br>
4- create signin layout




# 5 - connect firebase to project and intigrate firebase_Authentication 
# 6 - write code for signUp user
	
	//create firebase auth object
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
	
	   val signupbtn = findViewById<Button>(R.id.buttonSignUpp)


        signupbtn.setOnClickListener {

            val userEmail =
                findViewById<TextInputEditText>(R.id.editTextSignUpEmail).text.toString()
            val userPassword =
                findViewById<TextInputEditText>(R.id.editTextSignUpPassword).text.toString()

            signupWithFireBase(userEmail, userPassword)
        }
    }
	
    fun signupWithFireBase(userEmail: String, userPassword: String) {

        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                Toast.makeText(this, "Account Has been Created", Toast.LENGTH_SHORT).show()
                finish()

            } else {
                Toast.makeText(this, task.exception?.toString(), Toast.LENGTH_SHORT).show()
            }
			}
    }
	
# 7 - write code for signIN user

		//create firebase auth object
		val auth: FirebaseAuth = FirebaseAuth.getInstance()
		
		val signInbtn = findViewById<Button>(R.id.buttonSignIn)
		
		   signInbtn.setOnClickListener {

            val userEmail = editTextLoginEmail.text.toString()
            val userPassword = editTextLoginPassword.text.toString()

            signInWithFirebase(userEmail, userPassword)
        }
    }

    fun signInWithFirebase(userEmail: String, userPassword: String) {

        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {

                Toast.makeText(this, task.exception?.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
	
# 8-  get API -> Generate Kotlin Data Class

# 9-  Create Api Interface

# 10- create retrofit instance (ctr+Shift+Space)

# 11- Create Adapter and set view

# 12 - Add ForgetPassword Functionality with Email 
		
			forgetbtn.setOnClickListene{
			val email = editTextForgetPasswordEmail.text.toString()
            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(
                        this,
                        "We sent a password reset mail to your mail address",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } else {

                    Toast.makeText(this, task.exception?.toString(), Toast.LENGTH_SHORT).show()
               }
           }
        }
		
# 13 - Add User Remembering Functionality 

   //User Remembering Functionality
    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null){
            val intent = Intent(this, SongsListActivity::class.java)
            startActivity(intent)
        }
    }
# 14 - Add User SignOut Functionality

	    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item ,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.signOut){
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this ,LoginActivity::class.java)
            startActivity(intent)
            finish()

            Toast.makeText(this, "User Sign Out", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }


15 - ScreenShots : <br>

![1](https://github.com/NTS-AkshayBokhare/Music-player-app/assets/138112705/82e13eea-41dc-4a9d-962f-5bf00cea3f0c)  <br>

![2](https://github.com/NTS-AkshayBokhare/Music-player-app/assets/138112705/935747f2-a8ca-48e8-a226-942a0af090e3)  <br>

![3](https://github.com/NTS-AkshayBokhare/Music-player-app/assets/138112705/63261e4e-8ac8-4eda-935d-d367242b062f)  <br>

![4](https://github.com/NTS-AkshayBokhare/Music-player-app/assets/138112705/ad962055-7c7f-4baa-bdcb-2826a17a5770)  <br>

![5](https://github.com/NTS-AkshayBokhare/Music-player-app/assets/138112705/108d5957-04ac-4fd4-aff2-b35dc26e3c8c)

	
