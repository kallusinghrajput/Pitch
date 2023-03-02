package com.pitch.utils


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.util.*
import java.util.regex.Pattern


object ImageUtils {
    lateinit var currentPhotoPath: String
    lateinit var imageName: String

    /**
     *
     * This for read camera image name
     *
     */
    @Throws(IOException::class)
    fun createImageFile(context: Context): File? {
        // Create an image file name
      //  val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "IMG-", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath


            imageName = name
        }
    }

    /**
     *
     * get Gallery image name
     *
     */
    @SuppressLint("Recycle")
    fun Activity.getRealPathFromURI(selectedImage: Uri?): String? {
        val returnCursor = contentResolver.query(
            selectedImage!!,
            null, null, null, null
        )
        val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        return returnCursor.getString(nameIndex)

    }


    fun Activity.hideSystemUI() {
//                val w = window
//            w.setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//            )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false) //also tried with true
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

            window.navigationBarColor = Color.BLACK
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        //    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE
          //  window.statusBarColor = Color.BLACK
        }
    }


    /**
     *
     * password Visibility and Invisibility
     *
     */




    /**
     * this is for email validation
     *
     */

    fun isValidEmail(s: String): Boolean {
        //            val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        //val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        val EMAIL_PATTERN = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(s)

        return !matcher.matches()
    }

    /**
     * this is for password validation
     *
     */
    fun isPasswordValidMethod(password: String): Boolean {
        val expression = ("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,15}$")

        val pattern = Pattern.compile(expression)
        val matcher = pattern.matcher(password)
        return !matcher.matches()
    }


    /**
     * this is for password validation
     *
     */
    fun isUserNameMethod(userName: String): Boolean {
        // val expression = "^@(?=.*\\w)[\\w]{7,15}\$"
        val expression = ("^@(?=.*[0-9])" +
                "(?=.*[a-z])" + "(?=\\S+$).{7,14}$")

        val pattern = Pattern.compile(expression)
        val matcher = pattern.matcher(userName)
        return !matcher.matches()
    }

    fun hideKeyboard(context: Context, view: View?) {
        try {
            if (view != null) {
                val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    /**
     * Method use to hide keyboard.
     *
     * @param ctx context of current activity.
     */

    fun Activity.hideKeyboardData() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    /**
     *
     *
     * method for scrolling
     *
     */
    @SuppressLint("ClickableViewAccessibility")
    fun Context.editTextScrollListener(editText: EditText) {
        editText.setOnTouchListener { view, event ->
            view.parent.requestDisallowInterceptTouchEvent(true)
            if ((event.action and MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP || (event.action and MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN) {
                //editText.setSelection(editText.text.length)
                view.parent.requestDisallowInterceptTouchEvent(false)
            }
            return@setOnTouchListener false
        }

    }




    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "Title" + Date().time,
            null
        )
        return Uri.parse(path)
    }
}