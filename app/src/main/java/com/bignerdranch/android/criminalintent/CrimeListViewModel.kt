package com.bignerdranch.android.criminalintent
import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID

//Storing list of Crime objects in this CrimelistViewModel, for the new screen,
//since this data will be displayed on a new screen you will create and new Fragment called CrimelistFragment
//MainActivity will host and instance of CrimeListFragment, which in turn will daiplay the list of crimes on the screen
class CrimeListViewModel : ViewModel() {


    val crimes = mutableListOf<Crime>()

        init {
            for (i in 0 until 100) {
                val crime = Crime(
                    id = UUID.randomUUID(),
                    title ="Crime #$i",
                    date = Date(),
                    isSolved = i % 2 == 0,
                    requiresPolice = i % 5 == 0
                )

                crimes += crime
            }
    }
}