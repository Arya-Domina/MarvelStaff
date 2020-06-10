package com.example.marvelstaff.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvelstaff.R
import com.example.marvelstaff.util.Logger

class CharDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Logger.log("CharDetailsFragment", "onCreateView")
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Logger.log("CharDetailsFragment", "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }
}