package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.databinding.FragmentStartBinding
import java.util.jar.Manifest

class StartFragment : Fragment() {
    private var binding: FragmentStartBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater)
        return binding!!.root
        //Manifest
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBtnClick()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setOnBtnClick(){
        binding!!.startBtn.setOnClickListener{
            var size = 0

            if(binding!!.gridSize.text.toString().isEmpty())
                return@setOnClickListener

            when(val num = binding!!.gridSize.text.toString().toInt()){
                in 5..Int.MAX_VALUE -> size = 5
                in 3..5 -> size = num
                in 0..3 -> size = 3
            }

            findNavController().navigate(StartFragmentDirections.actionStartFragmentToGameFragment(size))
        }
    }
}