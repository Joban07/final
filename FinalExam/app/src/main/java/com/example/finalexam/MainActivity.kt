import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        // Check if user is already signed in
        val currentUser: FirebaseUser? = auth.currentUser
        if (currentUser != null) {
            // User is signed in, redirect to main page
            redirectToMainPage()
        } else {
            // User is not signed in, perform sign-in or sign-up
            signIn("user@example.com", "password123")
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, redirect to main page
                    redirectToMainPage()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun redirectToMainPage() {
        val intent = Intent(this, MainPageActivity:class.java)
        startActivity(intent)
        finish() // Close this activity to prevent user from going back
    }
}
