package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.bignerdranch.android.criminalintent.databinding.FragmentCrimeDetailBinding
import java.util.Date
import java.util.UUID

class CrimeDetailFragment : Fragment() {

    //creating a nullable backing property _binding
    private var _binding: FragmentCrimeDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    private lateinit var crime: Crime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //accessing the instance of the Crime which has these properties and assigning values to them
        crime = Crime(
            id = UUID.randomUUID(),
            title = "",
            date = Date(),
            isSolved = false,
            requiresPolice = false
        )
    }

    //this is the function where you bind and inflate the layouts view unlike in activity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //container-> views parent
        //false -> third parameter tells layouit inflate whether to immediately add the inflated view to views parent
        _binding = FragmentCrimeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    //to wire up the views of the fragment always in onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //adding listener on a view, lambda is invoked with four parameters, only care about first one
            //text  --> current text in the EditText in layout
            //creates the copy of the crime object and updates its property title with the text entered by user
            //we are copying but not directly assigning title because it is immutable as declared by val
            //so we are creating a new instance of the crime
            //doOnTextChanged is a listener for EditText class
            crimeTitle.doOnTextChanged { text, _, _, _ ->
                //since we have used val crime in ListViewModel to create instance of Crime so to update the data we use this copy approach
                crime = crime.copy(title = text.toString())
            }

            //disabling button, apply is used to configure an object, inside apply you can access
            //objects property directly, text is a property in crimeDate button
            crimeDate.apply {
                text = crime.date.toString()
                isEnabled = false
            }

            //listening for checkboxes, similarly listener on checkbox
            crimeSolved.setOnCheckedChangeListener { _, isChecked ->
                crime = crime.copy(isSolved = isChecked)
            }
        }

    }

    //onDestroyView() is called when the fragments view is about to be destroyed, in this case we are setting the backing property of _binding to null
    //which releases the references of the binding object,
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}