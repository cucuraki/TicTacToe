package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.marginTop
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tictactoe.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private var binding: FragmentGameBinding? = null
    private lateinit var adapter: Adapter
    private val list = mutableListOf<Square>()
    private var move = 1;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        val args: GameFragmentArgs by navArgs()
        val displayMetrics = requireContext().resources.displayMetrics
        val width = displayMetrics.widthPixels / displayMetrics.density
        val size = args.gridSize
        for (i in 0 until size * size) {
            list.add(Square(R.drawable.square, width / size, false))
        }
        adapter = Adapter(list) {
            if (!list[it].isClicked) {
                if (move == 1) {
                    list[it].image = R.drawable.x

                    move = 0
                } else {
                    list[it].image = R.drawable.o
                    move = 1
                }
                list[it].isClicked = true
                adapter.notifyItemChanged(it)
                for(i in 0 until 5){
                    for(j in 0 until 5){

                    }
                }

            }
        }
        binding!!.recycler.adapter = adapter
        binding!!.recycler.layoutManager = GridLayoutManager(this.context, size)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
