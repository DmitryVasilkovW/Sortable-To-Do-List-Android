package com.sortabletodolist.sortabletodolistforandroid.ui.dialog

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sortabletodolist.sortabletodolistforandroid.R

class InputDialogFragment : BottomSheetDialogFragment()
{
    private var savedText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_input_dialog, container, false)

        val input = view.findViewById<EditText>(R.id.input)
        input.inputType = InputType.TYPE_CLASS_TEXT

        val okButton = view.findViewById<Button>(R.id.ok_button)
        okButton.setOnClickListener {
            savedText = input.text.toString()
            Toast.makeText(context, "Текст сохранен", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        val cancelButton = view.findViewById<Button>(R.id.cancel_button)
        cancelButton.setOnClickListener {
            dismiss()
        }

        return view
    }
}
