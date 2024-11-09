package id.ac.polbeng.riskiafendi.sharedpreferencesexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.ac.polbeng.riskiafendi.sharedpreferencesexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
       // const val RPL = "RPL"
       const val RPL = "TESTFILE" // Nama file shared preferences konsisten
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // val filename = "$packageName TESTFILE"
        val filename = "$packageName-${RPL}" // Sesuaikan nama file agar konsisten


        val  pref = getSharedPreferences(filename,Context.MODE_PRIVATE)


        binding.btnSave.setOnClickListener{
            val editor = pref.edit()
            editor.putString("firstName", binding.etFirstName.text.toString())
            editor.putString("lastName", binding.etLastName.text.toString())
            editor.apply()
            Toast.makeText(this, "Saved Data!", Toast.LENGTH_LONG).show()
        }

        binding.btnLoad.setOnClickListener {
            val firstName = pref.getString("firstName", "")
            val lastName = pref.getString("lastName", "")
            val output = "$firstName $lastName"
            binding.etFirstName.setText(firstName)
            binding.etLastName.setText(lastName)
            binding.tvOutput.text = output
        }

        binding.btnSecondActivity.setOnClickListener{
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        binding.etFirstName.setText("")
        binding.etLastName.setText("")
        binding.tvOutput.text = ""
    }
}