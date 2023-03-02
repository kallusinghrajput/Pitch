package com.pitch.utils.customviews

import android.content.Context
import android.widget.LinearLayout
import android.view.View.OnFocusChangeListener
import android.text.TextWatcher
import android.widget.EditText
import android.view.LayoutInflater
import com.pitch.R
import android.text.Editable
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.StringBuilder

/**
 * @author yuana <andhikayuana></andhikayuana>@gmail.com>
 * @since 9/7/17
 */
class EditTextOtp : LinearLayout, OnFocusChangeListener, TextWatcher, View.OnKeyListener {
    private var etDigit1: EditText? = null
    private var etDigit2: EditText? = null
    private var etDigit3: EditText? = null
    private var etDigit4: EditText? = null
    var currentFocused: EditText? = null
        private set

    constructor(context: Context?) : super(context) {
        initialize()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initialize()
    }

    private fun initialize() {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.otp_view_layout, this)
        etDigit1 = findViewById<View>(R.id.etDigit1) as EditText
        etDigit2 = findViewById<View>(R.id.etDigit2) as EditText
        etDigit3 = findViewById<View>(R.id.etDigit3) as EditText
        etDigit4 = findViewById<View>(R.id.etDigit4) as EditText
        initFocusListener()
        initTextChangeListener()
        initKeyListener()
    }

    private fun initFocusListener() {
        etDigit1!!.onFocusChangeListener = this
        etDigit2!!.onFocusChangeListener = this
        etDigit3!!.onFocusChangeListener = this
        etDigit4!!.onFocusChangeListener = this
    }

    private fun initTextChangeListener() {
        etDigit1!!.addTextChangedListener(this)
        etDigit2!!.addTextChangedListener(this)
        etDigit3!!.addTextChangedListener(this)
        etDigit4!!.addTextChangedListener(this)
    }

    private fun initKeyListener() {
        etDigit1!!.setOnKeyListener(this)
        etDigit2!!.setOnKeyListener(this)
        etDigit3!!.setOnKeyListener(this)
        etDigit4!!.setOnKeyListener(this)
    }

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        currentFocused = v as EditText
        currentFocused?.let {
            it.setSelection(it.text.length)
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        currentFocused?.let {
            if (it.text.isNotEmpty() && it !== etDigit4) {
                it.focusSearch(FOCUS_RIGHT).requestFocus()
            } else if (it.text.isNotEmpty() && it === etDigit4) {
                val imm = context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(windowToken, 0)
            } else {
                val curretValue = it.text.toString()
                if (curretValue.isEmpty() && it.selectionStart <= 0) {
                    it.focusSearch(FOCUS_LEFT).requestFocus()
                }else{}
            }
        }

    }

    override fun afterTextChanged(s: Editable) {}
    var otp: String
        get() = makeOtp()
        set(otp) {
            if (otp.length != 4) return
            etDigit1?.setText(otp[0].toString())
            etDigit2?.setText(otp[1].toString())
            etDigit3?.setText(otp[2].toString())
            etDigit4?.setText(otp[3].toString())
        }

    private fun makeOtp(): String {
        val sb = StringBuilder()
        sb.append(etDigit1!!.text.toString())
        sb.append(etDigit2!!.text.toString())
        sb.append(etDigit3!!.text.toString())
        sb.append(etDigit4!!.text.toString())
        return sb.toString()
    }

    val isValid: Boolean
        get() = makeOtp().length == 4

    override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            val id = v.id
            return when (id) {
                R.id.etDigit1 -> isKeyDel(etDigit1, keyCode)
                R.id.etDigit2 -> isKeyDel(etDigit2, keyCode)
                R.id.etDigit3 -> isKeyDel(etDigit3, keyCode)
                R.id.etDigit4 -> isKeyDel(etDigit4, keyCode)
                else -> false
            }
        }
        return false
    }

    private fun isKeyDel(etDigit: EditText?, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            etDigit?.text = null
            return true
        }
        return false
    }
}